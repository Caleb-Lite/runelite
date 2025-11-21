package net.runelite.client.plugins.pluginhub.com.github.zakru.advancednotifications.ui;

import net.runelite.client.plugins.pluginhub.com.github.zakru.advancednotifications.DraggableContainer;

public interface DropSpaceSystem<T>
{
	T getDragging();
	void setDragging(T t, DraggableContainer<T> from);
	DraggableContainer<T> getDraggingFrom();
	DropSpace<T> getDragHovering();
	void setDragHovering(DropSpace<T> space);
}
