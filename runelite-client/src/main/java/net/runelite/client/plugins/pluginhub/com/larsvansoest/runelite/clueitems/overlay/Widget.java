package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.overlay;

/**
 * Includes Widget definitions for widgets used by the plugin.
 */
public enum Widget
{
	BANK(12, 10),

	BANK_EQUIPMENT(12, 69),

	BANK_EQUIPMENT_INVENTORY(15, 4),

	BANK_INVENTORY(15, 3),

	EQUIPMENT(387, 0),

	EQUIPMENT_EQUIPMENT(84, 1),

	EQUIPMENT_INVENTORY(85, 0),

	DEPOSIT_BOX(192, 2),

	GUIDE_PRICES(464, 2),

	GUIDE_PRICES_INVENTORY(238, 0),

	INVENTORY(149, 0),

	KEPT_ON_DEATH(4, 5),

	SHOP(300, 16),

	SHOP_INVENTORY(301, 0),

	GROUP_STORAGE(724, 10),

	GROUP_STORAGE_INVENTORY(725, 0),

	WATSON_NOTICE_BOARD(493, 0),
	WATSON_NOTICE_BOARD_BEGINNER(493, 4),
	WATSON_NOTICE_BOARD_EASY(493, 6),
	WATSON_NOTICE_BOARD_MEDIUM(493, 8),
	WATSON_NOTICE_BOARD_HARD(493, 10),
	WATSON_NOTICE_BOARD_ELITE(493, 12),
	WATSON_NOTICE_BOARD_MASTER(493, 14);

	/**
	 * id of the widget
	 */
	public final int id;

	/**
	 * group id of the widget, displayed in RuneLites widget inspector as groupId.childId;
	 */
	public final int groupId;

	/**
	 * child id of the widget, displayed in RuneLites widget inspector as groupId.childId;
	 */
	public final int childId;

	Widget(final int groupId, final int childId)
	{
		this.id = groupId << 16 | childId;
		this.groupId = groupId;
		this.childId = childId;
	}
}
