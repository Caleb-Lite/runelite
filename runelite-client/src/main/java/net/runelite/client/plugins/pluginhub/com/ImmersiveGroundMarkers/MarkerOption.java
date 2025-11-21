package net.runelite.client.plugins.pluginhub.com.ImmersiveGroundMarkers;

public class MarkerOption{
    public MarkerOption(String name, int modelID){
        this.name = name;
        this.modelID = modelID;
        this.orientationOffset = 0;
        this.animation = -1;
    }
    public MarkerOption(String name, int modelID, int orientationOffset){
        this.name = name;
        this.modelID = modelID;
        this.orientationOffset = orientationOffset;
        this.animation = -1;
    }
    public MarkerOption(String name, int modelID, int orientationOffset, int animation){
        this.name = name;
        this.modelID = modelID;
        this.orientationOffset = orientationOffset;
        this.animation = animation;
    }
    public MarkerOption(String name, int modelID, int orientationOffset, int animation, short[] colorsToFind, short[] colorsToReplace){
        this.name = name;
        this.modelID = modelID;
        this.orientationOffset = orientationOffset;
        this.animation = animation;
        this.colorsToFind = colorsToFind;
        this.colorsToReplace = colorsToReplace;
    }
    public MarkerOption(String name, int modelID, short[] colorsToFind, short[] colorsToReplace){
        this.name = name;
        this.modelID = modelID;
        this.orientationOffset = 0;
        this.animation = -1;
        this.colorsToFind = colorsToFind;
        this.colorsToReplace = colorsToReplace;
    }
    
    public MarkerOption(String name, int modelID, int orientationOffset, short[] colorsToFind, short[] colorsToReplace){
        this.name = name;
        this.modelID = modelID;
        this.orientationOffset = orientationOffset;
        this.animation = -1;
        this.colorsToFind = colorsToFind;
        this.colorsToReplace = colorsToReplace;
    }
    String name;
    int modelID;
    int orientationOffset;
    int animation;
    short[] colorsToFind;
    short[] colorsToReplace;
}

/*
 * Copyright (c) 2018, TheLonelyDev <https://github.com/TheLonelyDev>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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

 //Code from Runelite Ground Markers