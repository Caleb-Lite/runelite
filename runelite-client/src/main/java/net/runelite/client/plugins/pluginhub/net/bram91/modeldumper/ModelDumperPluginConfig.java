package net.runelite.client.plugins.pluginhub.net.bram91.modeldumper;

import net.runelite.client.config.*;

@ConfigGroup("modeldumper")
public interface ModelDumperPluginConfig extends Config
{
	@ConfigSection(
			name = "Exporter",
			description = "Technical settings for the exporter",
			position = 0
	)
	String exporterSection = "exporterSection";

	@ConfigSection(
			name = "Transmog",
			description = "Technical settings for the transmog",
			position = 1
	)
	String transmogSection = "transmogSection";

	@ConfigSection(
			name = "Experimental",
			description = "This can break stuff",
			position = 2
	)
	String experimentalSection = "experimentalSection";

	@ConfigItem(
		keyName = "material",
		name = "Export Color",
		description = "Also export colors for the models.",
		position = 1,
		section = exporterSection
	)
	default boolean material()
	{
		return false;
	}

	@ConfigItem(
			keyName = "forceRestPose",
			name = "Force Rest Pose (Local Player)",
			description = "Forces local player to perform an animation on export. The resulting model is in a rest pose.",
			position = 2,
			section = exporterSection
	)
	default boolean forceRestPose() { return false; }

	@ConfigItem(
			keyName="exportFormat",
			name = "Export Format",
			description="Select which 3d model file format to export to. OBJ uses averaged face colors while PLY uses vertex colors.",
			position = 3,
			section = exporterSection
	)
	default ExportFormat exportFormat() { return ExportFormat.OBJ; }

	@ConfigItem(
			keyName = "writeMaterialData",
			name = "Store OBJ material data for all frames",
			description = "Store material data for each individual frame on sequence exporting, for sequence importing into eg blender, it is recommended to turn this off",
			position = 4,
			section = exporterSection
	)
	default boolean writeMaterialData() { return false; }

	@ConfigItem(
			keyName = "transmogEnabled",
			name = "",
			description = "",
			hidden = true
	)
	void setTransmog(boolean val);
	@ConfigItem(
			keyName = "transmogEnabled",
			name = "Enabled",
			description = "Possibility to transmog into a NPC to make export easier",
			position = 1,
			section = transmogSection
	)
	default boolean transmogEnabled() {
		return false;
	}

	@ConfigItem(
			keyName = "npcId",
			name = "NPC ID",
			description = "NPC ID to transmog into",
			position = 2,
			section = transmogSection
	)
	@Range(min=-1)
	default int npcId() {
		return 0;
	}

	@ConfigItem(
			keyName = "npcId",
			name = "",
			description = "",
			hidden = true
	)
	void setNpcId(int val);

	@ConfigItem(
			keyName = "animationId",
			name = "Animation ID",
			description = "Animation to perform. Use 0 to disable",
			position = 3,
			section = transmogSection
	)
	default int animationId() {
		return 0;
	}

	@ConfigItem(
			keyName = "animationId",
			name = "",
			description = "",
			hidden = true
	)
	void setAnimationId(int val);

	@ConfigItem(
			keyName = "frame",
			name = "Frame",
			description = "Specific frame for the animation. Note: it will flicker a bit, but still easier to export a certain frame. Use 0 to disable",
			position = 4,
			section = transmogSection
	)
	default int frame() {
		return 0;
	}

	@ConfigItem(
			keyName = "frame",
			name = "",
			description = "",
			hidden = true
	)
	void setFrame(int val);

	@ConfigItem(
			keyName = "sidepanelEnabled",
			name = "Sidepanel",
			description = "Allows you to search for npcs and animations",
			position = 0,
			section = transmogSection
	)
	default boolean sidepanelEnabled() {
		return false;
	}

	@ConfigItem(
			keyName = "maxDistance",
			name = "Color match % (OBJ)",
			description = "Will group up colors that are similar, low is better for color accuracy.",
			position = 0,
			section = experimentalSection
	)
	@Range(max = 100)
	default double getMaxDistance() { return 0.0;}
}
