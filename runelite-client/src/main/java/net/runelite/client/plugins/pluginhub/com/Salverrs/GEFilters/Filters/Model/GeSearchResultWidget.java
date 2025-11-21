package net.runelite.client.plugins.pluginhub.com.salverrs.GEFilters.Filters.Model;

import net.runelite.api.widgets.Widget;

public class GeSearchResultWidget {
    private short itemId;
    private Widget container;
    private Widget title;
    private Widget icon;

    public GeSearchResultWidget(Widget container, Widget title, Widget icon, short itemId)
    {
        this.container = container;
        this.title = title;
        this.icon = icon;
        this.itemId = itemId;
    }

    public void setTooltipText(String text)
    {
        container.setName("<col=ff9040>" + text + "</col>");
    }

    public void setTitleText(String text)
    {
        title.setText(text);
    }

    public void setSpriteId(short spriteId)
    {
        icon.setType(5);
        icon.setContentType(0);
        icon.setItemId(-1);
        icon.setModelId(-1);
        icon.setModelType(1);

        icon.setSpriteId(spriteId);
        icon.revalidate();
    }

    public void setSpriteOffset(int xOffset, int yOffset)
    {
        icon.setOriginalX(icon.getOriginalX() + xOffset);
        icon.setOriginalY(icon.getOriginalY() + yOffset);
        icon.revalidate();
    }

    public void setSpriteSize(int width, int height)
    {
        icon.setOriginalWidth(width);
        icon.setWidthMode(0);

        icon.setOriginalHeight(height);
        icon.setWidthMode(0);

        icon.revalidate();
    }

    public void setItemIcon(short itemId)
    {
        icon.setItemId(itemId);
        icon.setSpriteId(itemId);
        icon.revalidate();
    }

    public void setOnOpListener(Object... args)
    {
        container.setOnOpListener(args);
    }
}

// All credit to Inventory Setups maintainers - https://github.com/dillydill123/inventory-setups
/*
 * Copyright (c) 2019, dillydill123 <https://github.com/dillydill123>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
