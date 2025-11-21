package net.runelite.client.plugins.pluginhub.com.levelupquestreqs;

import lombok.Getter;
import net.runelite.api.FontID;
import net.runelite.api.Quest;
import net.runelite.api.widgets.*;
import net.runelite.client.game.chatbox.ChatboxInput;
import net.runelite.client.input.KeyListener;
import net.runelite.client.util.ColorUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelUpQuestReqsInterfaceInput extends ChatboxInput implements KeyListener
{
    private static final int X_OFFSET = 13;
    private static final int Y_OFFSET = 16;

    private final LevelUpQuestReqsPlugin plugin;
    private final Quest quest;

    @Getter
    private boolean closeMessage;

    LevelUpQuestReqsInterfaceInput(LevelUpQuestReqsPlugin plugin, Quest quest)
    {
        this.plugin = plugin;
        this.quest = quest;
    }

    @Override
    public void open()
    {
        final Widget chatboxContainer = plugin.getChatboxPanelManager().getContainerWidget();

        final String questName = quest.getName().contains("Recipe for Disaster") ? quest.getName().replace("Recipe for Disaster", "RFD") : quest.getName();

        final Widget questReqLine1 = chatboxContainer.createChild(-1, WidgetType.TEXT);
        final Widget questReqLine2 = chatboxContainer.createChild(-1, WidgetType.TEXT);
        final Widget questReqContinue = chatboxContainer.createChild(-1, WidgetType.TEXT);

        questReqLine1.setText("Congratulations, you just reached the skill");
        questReqLine1.setTextColor(Color.BLACK.getRGB());
        questReqLine1.setFontId(FontID.QUILL_8);
        questReqLine1.setXPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqLine1.setOriginalX(73 + X_OFFSET);
        questReqLine1.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqLine1.setOriginalY(15 + Y_OFFSET);
        questReqLine1.setOriginalWidth(390);
        questReqLine1.setOriginalHeight(30);
        questReqLine1.setXTextAlignment(WidgetTextAlignment.CENTER);
        questReqLine1.setYTextAlignment(WidgetTextAlignment.LEFT);
        questReqLine1.setWidthMode(WidgetSizeMode.ABSOLUTE);
        questReqLine1.revalidate();

        questReqLine2.setText("requirements to complete " + ColorUtil.wrapWithColorTag(questName, Color.RED) + ".");
        questReqLine2.setTextColor(Color.BLACK.getRGB());
        questReqLine2.setFontId(FontID.QUILL_8);
        questReqLine2.setXPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqLine2.setOriginalX(73 + X_OFFSET);
        questReqLine2.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqLine2.setOriginalY(30 + Y_OFFSET);
        questReqLine2.setOriginalWidth(390);
        questReqLine2.setOriginalHeight(30);
        questReqLine2.setXTextAlignment(WidgetTextAlignment.CENTER);
        questReqLine2.setYTextAlignment(WidgetTextAlignment.LEFT);
        questReqLine2.setWidthMode(WidgetSizeMode.ABSOLUTE);
        questReqLine2.revalidate();

        questReqContinue.setText("Click here to continue");
        questReqContinue.setTextColor(Color.BLUE.getRGB());
        questReqContinue.setFontId(FontID.QUILL_8);
        questReqContinue.setXPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqContinue.setOriginalX(73 + X_OFFSET);
        questReqContinue.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqContinue.setOriginalY(74 + Y_OFFSET);
        questReqContinue.setOriginalWidth(390);
        questReqContinue.setOriginalHeight(17);
        questReqContinue.setXTextAlignment(WidgetTextAlignment.CENTER);
        questReqContinue.setYTextAlignment(WidgetTextAlignment.LEFT);
        questReqContinue.setWidthMode(WidgetSizeMode.ABSOLUTE);
        questReqContinue.setAction(0, "Continue");
        questReqContinue.setOnOpListener((JavaScriptCallback) ev -> triggerCloseViaMessage());
        questReqContinue.setOnMouseOverListener((JavaScriptCallback) ev -> questReqContinue.setTextColor(Color.WHITE.getRGB()));
        questReqContinue.setOnMouseLeaveListener((JavaScriptCallback) ev -> questReqContinue.setTextColor(Color.BLUE.getRGB()));
        questReqContinue.setHasListener(true);
        questReqContinue.revalidate();

        buildSpriteWidget(chatboxContainer, true);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if (e.getKeyChar() != ' ')
        {
            return;
        }

        triggerCloseViaMessage();

        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    void closeIfTriggered()
    {
        if (closeMessage && plugin.getChatboxPanelManager().getCurrentInput() == this)
        {
            plugin.getChatboxPanelManager().close();
        }
    }

    void triggerClose()
    {
        closeMessage = true;
    }

    private void triggerCloseViaMessage()
    {
        final Widget questReqContinue = plugin.getClient().getWidget(ComponentID.CHATBOX_CONTAINER).getChild(2);
        questReqContinue.setText("Please wait...");

        closeMessage = true;
    }

    private static void buildSpriteWidget(Widget chatboxContainer, boolean isQuest)
    {
        final Widget questReqSprite = chatboxContainer.createChild(-1, WidgetType.GRAPHIC);

        questReqSprite.setSpriteId(isQuest ? 776 : 1299);
        questReqSprite.setXPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqSprite.setOriginalX(X_OFFSET);
        questReqSprite.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        questReqSprite.setOriginalY(10);
        questReqSprite.setOriginalWidth(99);
        questReqSprite.setOriginalHeight(108);
        questReqSprite.revalidate();
    }
}

