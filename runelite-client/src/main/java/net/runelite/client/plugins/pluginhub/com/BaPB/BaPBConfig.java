package net.runelite.client.plugins.pluginhub.com.BaPB;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("BaPb")
public interface

BaPBConfig extends Config
{
	@ConfigItem(
			position = 1,
		keyName = "baPB",
		name = "Save role PB different then Overall PB",
		description = "Turning this on will save your round time into a specific role round time as well as into the overall pb"
	)
	default boolean Seperate()
	{
		return true;
	}

	@ConfigItem(
			position = 2,
		keyName = "msg",
		name = "Turn round msg on/off",
		description = "Turning this on will display a nice lil message at the start of the round"
	)
		default boolean Message() {return true;}
	@ConfigItem(
			position = 3,
		keyName = "log",
		name = "logger",
		description = "Allows to log your times to a file in your .runelite file turn off/on to update file"
	)
	default boolean Logging() {return false;}

	@ConfigItem(
			position = 4,
			keyName = "sub_runs",
			name = "Submit Runs",
			warning = "This portion of the plugin submits data to a 3rd party website not controlled or verified by the RuneLite Developers.",
			description = "This will submit runs to osrs-ba.com"
	)
	default boolean SubmitRuns() { return false; }

    @ConfigItem(
            position = 5,
            keyName = "sub_qs",
            name = "Submit QS Stats",
            description = "This will submit QS statistics to osrs-ba.com. Only used when \"Submit Runs\" is enabled."
    )
    default boolean SubmitQS() { return false; }


    @ConfigItem(
			position = 6,
			keyName = "uuid_key",
			name = "UUID Key",
			description = "Key used for linking ALT accounts, you can get one by logging into osrs-ba.com/accounts. Only used when \"Submit Runs\" is enabled."
	)
	default String uuid_key() { return null; }

}
