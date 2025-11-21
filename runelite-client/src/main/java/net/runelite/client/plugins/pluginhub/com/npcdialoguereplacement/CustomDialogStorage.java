package net.runelite.client.plugins.pluginhub.com.npcdialoguereplacement;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CustomDialogStorage
{
	private static final ArrayList<String> customText = new ArrayList<String>();

	public static void load(final String path)
	{
		customText.clear();

		if (path != null && !path.isEmpty()) {
			try {
				final Scanner scanner = new Scanner(new File(path));

				while (scanner.hasNextLine()) {
					final String line = scanner.nextLine();
					if (!line.isEmpty() && !line.startsWith("#") && !line.startsWith("//")) {
						customText.add(line);
					}
				}

				scanner.close();
			} catch (Exception e) {
				System.out.println("File not found.");
			}
		}
	}

	public static String getRandom()
	{
		return customText.get(new Random().nextInt(customText.size()));
	}

	public static boolean hasText()
	{
		return !customText.isEmpty();
	}
}
