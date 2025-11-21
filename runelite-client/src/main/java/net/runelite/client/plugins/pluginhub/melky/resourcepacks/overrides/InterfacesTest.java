package net.runelite.client.plugins.pluginhub.melky.resourcepacks.overrides;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

@Slf4j
public class InterfacesTest
{
	Overrides overrides;

	@Before
	public void beforeEach()
	{
		overrides = new Overrides("/overrides/overrides.toml");
	}

	@Test
	public void buildOverrides() throws IOException
	{
		testOverride("settings");
//		overrides.buildOverrides("");
//		var bank1 = overrides.get(274).get(0);
//
//		overrides.buildOverrides("[bank.separator]\ncolor=0xff0000");
//		var bank2 = overrides.get(274).get(0);
//
//		assertFalse(Objects.equals(bank1, bank2));
	}

	public void testOverride(String key)
	{
		overrides.clear();

		try (var stream = Overrides.class.getResourceAsStream("/overrides/overrides.toml"))
		{
			assert stream != null;

			TomlParseResult toml = Toml.parse(stream);
			toml.errors().forEach(error -> log.error(error.toString()));

			TomlParseResult pack = Toml.parse("");
			pack.errors().forEach(error -> log.error(error.toString()));

			var table = toml.getTableOrEmpty(key);
			overrides.walkChildren(new WidgetOverride().withName(key), table, pack);
		}
		catch (IOException e)
		{
			log.error("error loading overrides", e);
		}
	}
}
