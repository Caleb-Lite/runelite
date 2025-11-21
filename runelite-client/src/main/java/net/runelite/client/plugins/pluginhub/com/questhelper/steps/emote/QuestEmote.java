package net.runelite.client.plugins.pluginhub.com.questhelper.steps.emote;

import lombok.Getter;
import net.runelite.api.gameval.SpriteID;

@Getter
public enum QuestEmote
{
	SKILL_CAPE("Skill Cape", SpriteID.Emotes.SKILLCAPE),
	FLEX("Flex", SpriteID.Emotes._51),
	CLAP("Clap", SpriteID.Emotes.CLAP),
	CRY("Cry", SpriteID.Emotes.CRY),
	BOW("Bow", SpriteID.Emotes.BOW),
	DANCE("Dance", SpriteID.Emotes.DANCE),
	WAVE("Wave", SpriteID.Emotes.WAVE),
	THINK("Think", SpriteID.Emotes.THINK),
	GOBLIN_BOW("Goblin bow", SpriteID.Emotes.GOBLIN_BOW),
	BLOW_KISS("Blow Kiss", SpriteID.Emotes.BLOW_KISS),
	IDEA("Idea", SpriteID.Emotes.IDEA),
	STAMP("Stamp", SpriteID.Emotes.STAMP),
	FLAP("Flap", SpriteID.Emotes.FLAP),
	SLAP_HEAD("Slap Head", SpriteID.Emotes.SLAP_HEAD),
	SPIN("Spin", SpriteID.Emotes.SPIN);

	private String name;
	private int spriteId;

	QuestEmote(String name, int spriteId)
	{
		this.name = name;
		this.spriteId = spriteId;
	}
}
