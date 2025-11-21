package net.runelite.client.plugins.pluginhub.com.NativeMacNotifications;

import net.runelite.client.plugins.pluginhub.com.NativeMacNotifications.macJna.Foundation;
import static com.NativeMacNotifications.macJna.Foundation.invoke;
import static com.NativeMacNotifications.macJna.Foundation.nsString;
import net.runelite.client.plugins.pluginhub.com.NativeMacNotifications.macJna.ID;


public class Notification
{

	/**
	 * This code is from Intellij-community Github repo. Some has been edited or removed. But all credit goes to those contributors
	 * https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/ui/MountainLionNotifications.java
	 */
	public static void notify(String title, String description)
	{
		final ID notification = invoke(Foundation.getObjcClass("NSUserNotification"), "new");
		invoke(notification, "setTitle:", nsString(title));
		invoke(notification, "setInformativeText:", nsString(description));
		final ID center = invoke(Foundation.getObjcClass("NSUserNotificationCenter"), "defaultUserNotificationCenter");
		invoke(center, "deliverNotification:", notification);
	}
}


