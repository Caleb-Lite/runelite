package net.runelite.client.plugins.pluginhub.com.github.zakru.advancednotifications.condition;

import net.runelite.client.plugins.pluginhub.com.github.zakru.advancednotifications.notification.EmptyNotification;
import net.runelite.client.plugins.pluginhub.com.github.zakru.advancednotifications.notification.ItemNotification;
import net.runelite.client.plugins.pluginhub.com.github.zakru.advancednotifications.notification.NotificationGroup;
import net.runelite.client.plugins.pluginhub.com.github.zakru.advancednotifications.notification.NotificationType;

import java.util.HashMap;
import java.util.Map;

public class ConditionTypes
{
	public static final Map<String, ConditionType<?>> REGISTRY = new HashMap<>();

	public static final ConditionType<ItemCondition> ITEM = new ConditionType<>(ItemCondition::new, ItemCondition.class);
	public static final ConditionType<EmptyCondition> EMPTY = new ConditionType<>(EmptyCondition::new, EmptyCondition.class);

	public static void registerAll()
	{
		REGISTRY.put("item", ITEM);
		REGISTRY.put("empty", EMPTY);
	}
}
