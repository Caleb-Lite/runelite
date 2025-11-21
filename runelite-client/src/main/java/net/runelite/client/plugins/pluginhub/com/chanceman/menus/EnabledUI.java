package net.runelite.client.plugins.pluginhub.com.chanceman.menus;

import lombok.Getter;

@Getter
public enum EnabledUI
{
	BANK(12),
	DEPOSIT_BOX(192);

	private final int id;

	EnabledUI(int id) {
		this.id = id;
	}
}
