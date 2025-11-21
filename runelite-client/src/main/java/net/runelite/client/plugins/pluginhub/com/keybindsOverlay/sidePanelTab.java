package net.runelite.client.plugins.pluginhub.com.keybindsOverlay;

import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum sidePanelTab {

     COMBAT, SKILLS, QUESTS, INVENTORY, EQUIPMENT, PRAYER, SPELLBOOK;

    // Not in use tabs rn.
    // ACCOUNT_MANAGEMENT, CLAN_CHAT, EMOTE,  FRIENDS, LOGOUT, MUSIC, OPTIONS

    public BufferedImage getIcon() {
        try {
            return ImageUtil.resizeImage(ImageUtil.loadImageResource(getClass(), "/sidePanel/tabs/"+ name().toLowerCase() + ".png"), 18, 18);
        } catch (Exception e) {
            System.err.println("errorLoading image");
        }
        throw new RuntimeException("Shouldn't be here.");
    }

    public Method getKeybindingMethod() {
        return getMethod(name(), "Key");
    }

    public Method getLocationMethod() {
        return getMethod(name(), "Location");
    }

    private Method getMethod(String mainSpecifier, String secondSpecifier)
    {
        Method[] methods = KeybindsOverlayConfig.class.getMethods();
        Pattern pattern = Pattern.compile(mainSpecifier.toLowerCase() + secondSpecifier);
        for (Method method : methods) {
            Matcher matcher = pattern.matcher(method.getName());
            if (matcher.lookingAt()) {
                return method;
            }
        }
        throw new RuntimeException("Programming error. \n" + mainSpecifier + secondSpecifier);
    }

}

