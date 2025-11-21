package net.runelite.client.plugins.pluginhub.com.translator;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("translator")
public interface TranslatorConfig extends Config
{
    enum SelectLanguage{
        Finnish
    }
    @ConfigItem(
            keyName = "Select Language",
            name = "Language",
            description = "changes language to translate to Finnish",
            position = 1
    )
    default SelectLanguage selectLanguage()
    {
        return SelectLanguage.Finnish;
    }




}

