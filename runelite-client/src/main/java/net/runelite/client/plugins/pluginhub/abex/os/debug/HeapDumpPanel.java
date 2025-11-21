package net.runelite.client.plugins.pluginhub.abex.os.debug;

import com.sun.management.HotSpotDiagnosticMXBean;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.management.ObjectName;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.RuneLite;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.DynamicGridLayout;

@Slf4j
@Singleton
public class HeapDumpPanel extends JPanel
{
	private final Client client;
	private final ConfigManager configManager;
	private final Object[] oomer = new Object[1];
	private final JCheckBox heapDumpOnOOM;
	private final File oomDumpFile = new File(RuneLite.LOGS_DIR, "oom_heap_dump.hprof");
	private File lastDump;

	@Inject
	public HeapDumpPanel(Client client, ConfigManager configManager, @Named("developerMode") boolean developerMode)
	{
		this.client = client;
		this.configManager = configManager;

		setLayout(new DynamicGridLayout(0, 1));

		JButton heapDump = new JButton("Heap dump");
		add(heapDump);
		heapDump.addActionListener(ev -> dumpHeap());

		heapDumpOnOOM = new JCheckBox("Heap dump on OOM");
		heapDumpOnOOM.setSelected(configManager.getConfiguration(DebugConfig.GROUP, DebugConfig.CREATE_HEAP_DUMP, boolean.class) == Boolean.TRUE);
		heapDumpOnOOM.addChangeListener(_ev ->
		{
			configManager.setConfiguration(DebugConfig.GROUP, DebugConfig.CREATE_HEAP_DUMP, heapDumpOnOOM.isSelected());
		});
		add(heapDumpOnOOM);
		apply();

		if (oomDumpFile.exists())
		{
			JButton locateDump = new JButton("Open last dump");
			locateDump.addActionListener(_ev -> DebugPlugin.openExplorer(oomDumpFile));
			add(locateDump);
			lastDump = oomDumpFile;
		}

		JButton stripDump = new JButton("Strip heap dump");
		stripDump.addActionListener(_ev ->
		{
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Open heap dump");
			fc.setSelectedFile(lastDump != null ? lastDump : FileSystemView.getFileSystemView().getDefaultDirectory());
			fc.setFileFilter(new FileNameExtensionFilter("Heap dump", "hprof"));
			if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			{
				return;
			}

			File inFile = fc.getSelectedFile();

			JFrame frame = new JFrame("Stripping heap dump");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JLabel l = new JLabel("<html>Stripping heap dump<br>This may take a while...");
			l.setBorder(new EmptyBorder(15, 15, 15, 15));
			frame.add(l);
			frame.setType(Window.Type.POPUP);
			frame.pack();
			frame.setVisible(true);
			frame.toFront();

			new Thread(() ->
			{
				try
				{
					boolean zstd = false;
					try
					{
						ZstdOutputStream.init();
						zstd = true;
					}
					catch (Throwable e)
					{
						log.info("unable to init zstd", e);
					}

					File outFile = new File(inFile.getParentFile(),
						inFile.getName().replaceAll("\\.[^.]+$", "") + "_stripped.hprof." + (zstd ? "zstd" : "gz"));
					new HProfStripper(inFile, outFile, zstd).run();
					DebugPlugin.openExplorer(outFile);
				}
				catch (IOException e)
				{
					log.warn("", e);
				}
				finally
				{
					frame.setVisible(false);
				}
			}).start();
		});
		add(stripDump);

		if (developerMode)
		{
			JButton oom = new JButton("oom the client");
			oom.addActionListener(_ev ->
			{
				Object[] obj = oomer;
				for (; ; )
				{
					Object[] waste = new Object[0xFFFF];
					obj[0] = waste;
					obj = waste;
				}
			});
			add(oom);
		}
	}

	public void apply()
	{
		boolean enabled = configManager.getConfiguration(DebugConfig.GROUP, DebugConfig.CREATE_HEAP_DUMP, boolean.class) == Boolean.TRUE;
		heapDumpOnOOM.setSelected(enabled);

		var hsd = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
		hsd.setVMOption("HeapDumpOnOutOfMemoryError", "" + enabled);
		if (enabled)
		{
			hsd.setVMOption("HeapDumpPath", oomDumpFile.getAbsolutePath());
		}
	}

	private void dumpHeap()
	{
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save heap dump");
		fc.setSelectedFile(new File(FileSystemView.getFileSystemView().getDefaultDirectory(), "dump.hprof"));
		if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		File fi = fc.getSelectedFile();
		fi.delete();
		String filename = fi.getAbsoluteFile().getPath();
		lastDump = fi;

		client.setPassword("");

		JFrame frame = new JFrame("Heap dump");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel l = new JLabel("<html>Taking heap dump<br>This may take a while...");
		l.setBorder(new EmptyBorder(15, 15, 15, 15));
		frame.add(l);
		frame.setType(Window.Type.POPUP);
		frame.pack();
		frame.setVisible(true);
		frame.toFront();

		Timer t = new Timer(300, v ->
		{
			try
			{
				ManagementFactory.getPlatformMBeanServer().invoke(
					new ObjectName("com.sun.management:type=HotSpotDiagnostic"),
					"dumpHeap",
					new Object[]{filename, true},
					new String[]{String.class.getName(), boolean.class.getName()}
				);
			}
			catch (Exception e)
			{
				log.warn("unable to capture heap dump", e);
				JOptionPane.showMessageDialog(this, e.toString(), "Heap dump error", JOptionPane.ERROR_MESSAGE);
			}

			frame.setVisible(false);
		});
		t.setRepeats(false);
		t.start();
	}
}
