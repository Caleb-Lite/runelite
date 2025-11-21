package net.runelite.client.plugins.pluginhub.com.rainbowrave;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public enum Theme
{
    RAINBOW,
    TRANS(new Color(91, 206, 250),
            new Color(245, 169, 184),
            new Color(255, 255, 255),
            new Color(245, 169, 184)
    ),
	ENBY(
		new Color(255, 255, 255),
		new Color(156, 89, 209),
		new Color(44, 44, 44),
		new Color(252, 244, 52)
	),
	LESBIAN(
		new Color(212, 44, 0),
		new Color(253, 152, 85),
		new Color(255, 255, 255),
		new Color(209, 97, 162),
		new Color(162, 1, 97)
	),
	PAN(
		new Color(255, 33, 140),
		new Color(255, 216, 0),
		new Color(255, 33, 140),
		new Color(33, 177, 255)
	),
	ACE(
		new Color(0, 0, 0),
		new Color(163, 163, 163),
		new Color(255, 255, 255),
		new Color(128, 0, 128)
	),
	BI(
		new Color(214, 2, 112),
		new Color(0, 56, 168),
		new Color(155, 79, 150)
	),
	GENDER_QUEER(
		new Color(181, 126, 220),
		new Color(255, 255, 255),
		new Color(74, 129, 35)
	),
	GAY(
		new Color(255, 255, 255),
		new Color(123, 173, 226),
		new Color(80, 73, 204),
		new Color(61, 26, 120),
		new Color(7, 141, 112),
		new Color(38, 206, 170),
		new Color(152, 232, 193)
	);

	private final List<PerceptualGradient> gradients;

	Theme()
	{
		gradients = new ArrayList<>();
	}

	Theme(Color... colors) {
		gradients = new ArrayList<>();
		for (int i=0; i<colors.length; i++) {
			Color startColor = colors[i];
			// When at the last index then wrap back to first color
			Color endColor = i == colors.length-1 ? colors[0] : colors[i+1];
            gradients.add(new PerceptualGradient(startColor, endColor));
        }
    }

    public Color getColor(float ratio)
    {
        // Subtract floor to match Color.getHSBColor() functionality
        ratio = Math.abs((float) (ratio - Math.floor(ratio)));

        if (gradients.size() == 0) { // This applies to the RAINBOW theme only.
            return Color.getHSBColor(ratio, 1.0f, 1.0f);
        }

        float increment = 1.0f/gradients.size(); // Since size isn't 0, increment is always (0-1]
        PerceptualGradient gradient = gradients.get((int) Math.floor(ratio/increment));
        float relativeRatio = (ratio%increment)/increment;
        return gradient.getColorMix(relativeRatio);
    }
}

/*
 * Copyright (c) 2018 kulers
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