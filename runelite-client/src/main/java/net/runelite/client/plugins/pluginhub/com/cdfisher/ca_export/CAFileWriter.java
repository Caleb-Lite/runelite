package net.runelite.client.plugins.pluginhub.com.cdfisher.ca_export;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import static net.runelite.client.RuneLite.RUNELITE_DIR;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;

@Slf4j
public class CAFileWriter
{
	private String fileName;
	private static final File CA_EXPORT_DIR = new File(RUNELITE_DIR, "ca_exporter");
	private Gson gson;
	private ChatMessageManager chatMessageManager;

	public CAFileWriter(Gson gson, ChatMessageManager chatMessageManager)
	{
		this.gson = gson;
		this.chatMessageManager = chatMessageManager;
	}

	public void writeGSON(String username, List<CAEntry> caEntries, boolean printPath, String profileTypeIdentifier)
	{
		try
		{
			CA_EXPORT_DIR.mkdir();
			//set file name to <username>.json
			fileName = username.toLowerCase().trim() + profileTypeIdentifier + ".json";
			// write gson to CA_EXPORT_DIR/filename
			final BufferedWriter writer = new BufferedWriter(new FileWriter(new File(CA_EXPORT_DIR, fileName),
				false));
			final String caString = this.gson.toJson(caEntries);
			writer.append(caString);
			writer.close();
		}
		catch (IOException e)
		{
			log.warn("CA Exporter: Error writing combat achievements to file: {}", e.getMessage());
		}
		log.info("Wrote Combat Achievement JSON to {}/{}", CA_EXPORT_DIR.getName().replace("\\", "/"),
			fileName);

		if (printPath)
		{
			ChatMessageBuilder message = new ChatMessageBuilder()
				.append("[Combat Achievement Exporter] Wrote Combat Achievements to ")
				.append(CA_EXPORT_DIR.getAbsolutePath().replace("\\", "/"))
				.append("/")
				.append(fileName);

			this.chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.GAMEMESSAGE)
				.runeLiteFormattedMessage(message.build())
				.build());
		}

	}

}
