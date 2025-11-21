package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications;

public interface IAudioNotification extends INotification {
    int getGain();
    IAudioNotification setGain(int gain);
}
