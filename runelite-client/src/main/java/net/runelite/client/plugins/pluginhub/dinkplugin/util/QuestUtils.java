package net.runelite.client.plugins.pluginhub.dinkplugin.util;

import com.google.common.collect.ImmutableList;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class QuestUtils {

    private final Pattern QUEST_PATTERN_1 = Pattern.compile(".+?ve\\.*? (?<verb>been|rebuilt|.+?ed)? ?(?:the )?'?(?<quest>.+?)'?(?: [Qq]uest)?[!.]?$");
    private final Pattern QUEST_PATTERN_2 = Pattern.compile("'?(?<quest>.+?)'?(?: [Qq]uest)? (?<verb>[a-z]\\w+?ed)?(?: f.*?)?[!.]?$");
    private final Collection<String> RFD_TAGS = ImmutableList.of("Another Cook", "freed", "defeated", "saved");
    private final Collection<String> WORD_QUEST_IN_NAME_TAGS = ImmutableList.of("Another Cook", "Doric", "Heroes", "Legends", "Observatory", "Olaf", "Waterfall");
    private final Map<String, String> QUEST_REPLACEMENTS = Map.of(
        "Lumbridge Cook... again", "Another Cook's",
        "Skrach 'Bone Crusher' Uglogwee", "Skrach Uglogwee"
    );

    @Nullable
    public String parseQuestWidget(final String text) {
        Matcher matcher = getMatcher(text);
        if (matcher == null) {
            log.warn("Unable to match quest: {}", text);
            return null;
        }

        String quest = matcher.group("quest");
        quest = QUEST_REPLACEMENTS.getOrDefault(quest, quest);

        String verb = StringUtils.defaultString(matcher.group("verb"));

        if (verb.contains("kind of")) {
            log.debug("Skipping partial completion of quest: {}", quest);
            return null;
        } else if (verb.contains("completely")) {
            quest += " II";
        }

        if (RFD_TAGS.stream().anyMatch((quest + verb)::contains)) {
            quest = "Recipe for Disaster - " + quest;
        }

        if (WORD_QUEST_IN_NAME_TAGS.stream().anyMatch(quest::contains)) {
            quest += " Quest";
        }

        return quest;
    }

    @Nullable
    private Matcher getMatcher(String text) {
        if (text == null)
            return null;

        // "You have completed The Corsair Curse!"
        Matcher questMatch1 = QUEST_PATTERN_1.matcher(text);
        if (questMatch1.matches())
            return questMatch1;

        // "'One Small Favour' completed!"
        Matcher questMatch2 = QUEST_PATTERN_2.matcher(text);
        if (questMatch2.matches())
            return questMatch2;

        return null;
    }

}
