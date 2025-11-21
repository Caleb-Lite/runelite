package net.runelite.client.plugins.pluginhub.com.ericversteeg.frosthprun.view;

import java.util.Map;

public class RSBox extends RSViewGroup
{
    public RSBox(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    @Override
    protected Map<RSLayoutGuide, Integer> layoutSubviews(Map<RSLayoutGuide, Integer> guides)
    {
        w = measureWidth(guides);
        h = measureHeight(guides);

        for (RSView view: subviews)
        {
            view.applyPosition(
                    new RSLayoutGuide.Builder()
                            .maxWidth(w)
                            .maxHeight(h)
                            .start(paddingStart + view.getMarginStart())
                            .top(paddingTop + view.getMarginTop())
                            .end(w - paddingEnd - view.getMarginEnd())
                            .bottom(h - paddingBottom - view.getMarginBottom())
                            .build()
            );
        }

        return new RSLayoutGuide.Builder().build();
    }
}

/*
 * Copyright (c) 2017, Tomas Slusny <slusnucky@gmail.com>
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