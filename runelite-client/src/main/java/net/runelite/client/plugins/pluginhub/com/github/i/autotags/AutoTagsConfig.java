package net.runelite.client.plugins.pluginhub.com.github.i.autotags;

import com.google.gson.Gson;
import net.runelite.client.config.*;

import javax.inject.Inject;
import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

@ConfigGroup(AutoTagsConfig.GROUP)
public interface AutoTagsConfig extends Config {
	String GROUP = "AutoTags";

	@ConfigSection(
		name = "Tag display mode",
		description = "How tags are displayed in the inventory",
		position = 0
	)
	String tagStyleSection = "tagStyleSection";

	@ConfigItem(
		position = 0,
		keyName = "showTagOutline",
		name = "Outline",
		description = "Configures whether or not item tags show be outlined",
		section = tagStyleSection
	)
	default boolean showTagOutline()
	{
		return true;
	}

	@ConfigItem(
		position = 1,
		keyName = "tagUnderline",
		name = "Underline",
		description = "Configures whether or not item tags should be underlined",
		section = tagStyleSection
	)
	default boolean showTagUnderline()
	{
		return false;
	}

	@ConfigItem(
		position = 2,
		keyName = "tagFill",
		name = "Fill",
		description = "Configures whether or not item tags should be filled",
		section = tagStyleSection
	)
	default boolean showTagFill()
	{
		return false;
	}

	@Range(
		max = 255
	)
	@ConfigItem(
		position = 3,
		keyName = "fillOpacity",
		name = "Fill opacity",
		description = "Configures the opacity of the tag \"Fill\"",
		section = tagStyleSection
	)
	default int fillOpacity() {
		return 50;
	}

	@ConfigItem(
			keyName = "meleeColor",
			name = "Melee Tag Color",
			description = "Configures the overlay color for melee items",
			section = tagStyleSection
	)
	default Color meleeColor() {
		return Color.PINK;
	}

	@ConfigItem(
			keyName = "magicColor",
			name = "Magic Tag Color",
			description = "Configures the overlay color for magic items",
			section = tagStyleSection
	)
	default Color magicColor() {
		return Color.CYAN;
	}

	@ConfigItem(
			keyName = "rangedColor",
			name = "Ranged Tag Color",
			description = "Configures the overlay color for ranged items",
			section = tagStyleSection
	)
	default Color rangedColor() {
		return Color.GREEN;
	}

	@ConfigItem(
			keyName = "specialColor",
			name = "Special Tag Color",
			description = "Configures the overlay color for special items",
			section = tagStyleSection
	)
	default Color specialColor() {
		return Color.YELLOW;
	}


	@ConfigItem(
			keyName = "overrides",
			name = "overrides",
			description = "holds json for overrides",
			hidden = true
	)
	@Inject
	default String overrides(Gson gson) {
		return gson.toJson(CombatType.CHOICE_LIST.stream()
				.collect(Collectors.toMap(
						combatType -> combatType,
						combatType -> Set.of()
				)));
	}
}
