package net.runelite.client.plugins.pluginhub.rs117.hd.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VanillaShadowMode {
	SHOW("Show", true),
	SHOW_IN_PVM("Show in PvM", true),
	PREFER_IN_PVM("Prefer in PvM", true),
	HIDE("Hide", false),
	;

	private final String name;

	public final boolean retainInPvm;

	@Override
	public String toString() {
		return name;
	}
}
