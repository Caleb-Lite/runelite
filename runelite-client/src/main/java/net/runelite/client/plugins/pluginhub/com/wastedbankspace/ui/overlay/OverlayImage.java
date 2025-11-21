package net.runelite.client.plugins.pluginhub.com.wastedbankspace.ui.overlay;

import net.runelite.client.plugins.pluginhub.com.wastedbankspace.WastedBankSpacePlugin;
import lombok.Getter;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.util.ImageUtil;

@Getter
public enum OverlayImage {
    DEFAULT("Default", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/000-overlaySmoller.png"))),
    X("X", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/001-close.png"))),
    ARROW("Arrow", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/002-arrow-bottom.png"))),
    PUMPKIN("Spooky", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/003-pumpkin.png"))),
    TRASH_1("Trash 1", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/004-trash.png"))),
    TRASH_2("Trash 2", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/005-trash-bin.png"))),
    MAX("Max", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/006-maximize.png"))),
    W("W",new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/007-letter-w.png"))),
    ONE("Finger", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/008-one.png"))),
    PRETTY_1("Pretty 1", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/009-thai-pattern.png"))),
    PRETTY_2("Pretty 2", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/010-Pretty2.png"))),
    PRETTY_3("Pretty 3", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/011-Pretty3.png"))),
    DOT_BLUE("Blue Dot", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/012-Dot_Blue.png"))),
    DOT_RED("Red Dot", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/013-Dot_Red.png"))),
    DOT_GREEN("Green Dot", new ImageComponent(ImageUtil.loadImageResource(WastedBankSpacePlugin.class, "/014-Dot_green.png")));

    private final String name;
    private final ImageComponent image;

    OverlayImage(String name, ImageComponent image)
    {
        this.name = name;
        this.image = image;
    }

    @Override
    public String toString()
    {
        return getName();
    }

    public ImageComponent getImage()
    {
        return image;
    }
}

/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2021, Riley McGee
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
