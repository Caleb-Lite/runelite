package net.runelite.client.plugins.pluginhub.abex.os.debug;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.nio.channels.FileChannel;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLiteProperties;

@Slf4j
public class VMConfigPatch
{
	@Nullable
	private static final String unsupported = unsupported();

	@Nullable
	private static File packrConfigFile;

	private static String unsupported()
	{
		String launcherVer = RuneLiteProperties.getLauncherVersion();
		if (launcherVer == null)
		{
			return "Not using launcher";
		}
		if (!launcherVer.startsWith("2"))
		{
			return "Launcher out of date";
		}

		var proc = ProcessHandle.current().info();
		String cmdline = proc.command().get().toLowerCase(Locale.ROOT);

		if (!cmdline.endsWith("runelite.exe"))
		{
			return "Not using EXE launcher";
		}

		packrConfigFile = new File(new File(cmdline).getParentFile().getAbsoluteFile(), "config.json");
		if (!packrConfigFile.exists())
		{
			return "Cannot find config";
		}

		return null;
	}

	public static boolean isSupported()
	{
		return unsupported == null;
	}

	private final Gson gson;
	private final String name;
	private final String[] args;

	public VMConfigPatch(Gson gson, String name, String... args)
	{
		this.gson = gson.newBuilder()
			.setPrettyPrinting()
			.create();;
		this.name = name;
		this.args = args;
	}

	public String status()
	{
		var args = ManagementFactory.getRuntimeMXBean().getInputArguments();
		if (Arrays.stream(this.args).allMatch(args::contains))
		{
			return name + " enabled";
		}

		if (unsupported != null)
		{
			return unsupported;
		}

		return name + " disabled" + (isInConfig() ? " (Restart to enable)" : "");
	}

	public boolean isInConfig()
	{
		JsonObject config = loadConfig();
		return config != null && Arrays.stream(this.args)
			.allMatch(v -> config.get("vmArgs").getAsJsonArray().contains(new JsonPrimitive(v)));
	}

	private JsonObject loadConfig()
	{
		try (FileInputStream fin = new FileInputStream(packrConfigFile))
		{
			return gson.fromJson(new InputStreamReader(fin), JsonObject.class);
		}
		catch (IOException | JsonIOException | JsonSyntaxException e)
		{
			log.warn("error deserializing packr vm args!", e);
			return null;
		}
	}

	public boolean set(boolean enabled)
	{
		JsonObject config = loadConfig();
		if (config == null)
		{
			return false;
		}

		JsonArray vmArgs = config.get("vmArgs").getAsJsonArray();
		setHasArg(vmArgs, "-Drunelite.launcher.reflect=true", enabled);
		for (var arg : this.args)
		{
			setHasArg(vmArgs, arg, enabled);
		}

		try
		{
			File tmpFile = File.createTempFile("runelite", null);

			try (FileOutputStream fout = new FileOutputStream(tmpFile);
				FileChannel channel = fout.getChannel();
				PrintWriter writer = new PrintWriter(fout))
			{
				channel.lock();
				writer.write(gson.toJson(config));
				channel.force(true);
				// FileChannel.close() frees the lock
			}

			try
			{
				Files.move(tmpFile.toPath(), packrConfigFile.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
			}
			catch (AtomicMoveNotSupportedException ex)
			{
				log.debug("atomic move not supported", ex);
				Files.move(tmpFile.toPath(), packrConfigFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}

			log.info("patched packr vm args");
		}
		catch (IOException e)
		{
			log.warn("error updating packr vm args!", e);
		}

		return true;
	}

	private void setHasArg(JsonArray args, String argStr, boolean enabled)
	{
		String prefix = null;
		int index = argStr.indexOf("=");
		if (index > 0)
		{
			prefix = argStr.substring(0, index + 1);
		}

		for (var it = args.iterator(); it.hasNext(); )
		{
			var val = it.next();
			if ((prefix != null && enabled)
				? val.getAsString().startsWith(prefix)
				: val.getAsString().endsWith(argStr))
			{
				it.remove();
			}
		}

		if (enabled)
		{
			args.add(new JsonPrimitive(argStr));
		}
	}
}
