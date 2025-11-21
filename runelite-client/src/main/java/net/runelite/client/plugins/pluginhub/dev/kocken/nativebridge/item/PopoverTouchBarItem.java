package net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.item;

import net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.JavaTouchBar;
import net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.JavaTouchBarJNI;
import net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.common.Image;
import net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.item.view.TouchBarView;

public class PopoverTouchBarItem extends TouchBarItem {

	private TouchBarView _collapsedRepresentation;
	private Image _collapsedRepresentationImage;
	private String _collapsedRepresentationLabel;

	private JavaTouchBar _popoverTouchBar;
	private JavaTouchBar _pressAndHoldTouchBar;

	private Boolean _showsCloseButton;

	public PopoverTouchBarItem(String identifier) {
		super(identifier);
	}

	public TouchBarView getCollapsedRepresentation() {
		return _collapsedRepresentation;
	}

	public void setCollapsedRepresentation(TouchBarView collapsedRepresentation) {
		_collapsedRepresentation = collapsedRepresentation;
		update();
	}

	public Image getCollapsedRepresentationImage() {
		return _collapsedRepresentationImage;
	}

	public void setCollapsedRepresentationImage(Image collapsedRepresentationImage) {
		_collapsedRepresentationImage = collapsedRepresentationImage;
		update();
	}

	public String getCollapsedRepresentationLabel() {
		return _collapsedRepresentationLabel;
	}

	public void setCollapsedRepresentationLabel(String collapsedRepresentationLabel) {
		_collapsedRepresentationLabel = collapsedRepresentationLabel;
		update();
	}

	public JavaTouchBar getPopoverTouchBar() {
		return _popoverTouchBar;
	}

	public void setPopoverTouchBar(JavaTouchBar popoverTouchBar) {
		_popoverTouchBar = popoverTouchBar;
		update();
	}

	public JavaTouchBar getPressAndHoldTouchBar() {
		return _pressAndHoldTouchBar;
	}

	public void setPressAndHoldTouchBar(JavaTouchBar pressAndHoldTouchBar) {
		_pressAndHoldTouchBar = pressAndHoldTouchBar;
		update();
	}

	public void showPopover() {
		JavaTouchBarJNI.callObjectSelector(getNativeInstancePointer(), "showPopover", true);
	}

	public void dismissPopover() {
		JavaTouchBarJNI.callObjectSelector(getNativeInstancePointer(), "dismissPopover", true);
	}

	public Boolean isShowsCloseButton() {
		return _showsCloseButton;
	}

	public void setShowsCloseButton(Boolean showsCloseButton) {
		_showsCloseButton = showsCloseButton;
		update();
	}
}

/**
 * JTouchBar
 *
 * Copyright (c) 2018 - 2019 thizzer.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 * @author  	M. ten Veldhuis
 */