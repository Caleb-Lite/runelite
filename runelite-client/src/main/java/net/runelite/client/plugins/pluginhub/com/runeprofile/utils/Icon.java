package net.runelite.client.plugins.pluginhub.com.runeprofile.utils;

import net.runelite.client.plugins.pluginhub.com.runeprofile.RuneProfilePlugin;
import net.runelite.client.util.ImageUtil;

import javax.swing.*;
import java.awt.image.BufferedImage;

public enum Icon {
    LOGO("/logo.png"),

    WEB("/web.png"),
    DISCORD("/discord.png"),
    GITHUB("/github.png");

    private final String file;

    Icon(String file) {
        this.file = file;
    }

    public BufferedImage getImage() {
        return ImageUtil.loadImageResource(RuneProfilePlugin.class, file);
    }

    public ImageIcon getIcon(int width, int height) {
        return new ImageIcon(ImageUtil.resizeImage(getImage(), width, height));
    }
}