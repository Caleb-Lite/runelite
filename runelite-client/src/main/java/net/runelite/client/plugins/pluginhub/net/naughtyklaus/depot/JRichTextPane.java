package net.runelite.client.plugins.pluginhub.net.naughtyklaus.depot;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class JRichTextPane extends JEditorPane {

    public JRichTextPane() {
        super();
        setHighlighter(null);
        setEditable(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        HTMLEditorKit ek = (HTMLEditorKit) getEditorKitForContentType("text/html");
        ek.getStyleSheet().addRule("a {color: #DDDDDD; }");
    }

    public JRichTextPane(String type, String text) {
        this();
        setContentType(type);
        setText(text);
    }
}
