package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.prayer;

public enum POHAltar
{
    NONE(POHAltarType.builder().name("No altar").multiplierNoBurners(1).build()),
    OAK(POHAltarType.builder().name("Oak altar").multiplierNoBurners(1).build()),
    TEAK(POHAltarType.builder().name("Teak altar").multiplierNoBurners(1.1).build()),
    CLOTH(POHAltarType.builder().name("Cloth altar").multiplierNoBurners(1.25).build()),
    MAHOGANY(POHAltarType.builder().name("Mahogany altar").multiplierNoBurners(1.5).build()),
    LIMESTONE(POHAltarType.builder().name("Limestone altar").multiplierNoBurners(1.75).build()),
    MARBLE(POHAltarType.builder().name("Marble altar").multiplierNoBurners(2).build()),
    GILDED(POHAltarType.builder().name("Gilded altar").multiplierNoBurners(2.5).build()),
    ;

    public final POHAltarType altar;
    POHAltar(POHAltarType altar) { this.altar = altar; }

    @Override
    public String toString()
    {
        return this.altar.getName();
    }
}
