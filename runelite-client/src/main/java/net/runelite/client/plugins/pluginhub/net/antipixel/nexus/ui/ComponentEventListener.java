package net.runelite.client.plugins.pluginhub.net.antipixel.nexus.ui;

/**
 * A listener interface for receiving UI component events
 * @author Antipixel
 */
public interface ComponentEventListener
{
	/**
	 * Invoked upon a component event
	 * @param src the source component responsible for the event
	 */
	void onComponentEvent(UIComponent src);
}
