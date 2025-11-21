package net.runelite.client.plugins.pluginhub.com.code;

public class SkillCheck {
    private static int xp;
    private final static int[] lvls = {
            0, 1154, 4470, 13363, 37224, 101333, 273742, 737627, 1986068, 5346831
    };
    public static String skillTask;

    public static String woodcutting() {
        xp = BoredPanel.xp;

        if (xp > lvls[9]) {
            skillTask = "Redwoods logs";
        } else if (xp > 1_210_421) {
            skillTask = "Magic logs";
        } else if (xp > 22_406) {
            skillTask = "Teak logs";
        } else if (xp > lvls[3]) {
            skillTask = "Willow logs";
        } else if (xp > 2_411) {
            skillTask = "Oak logs";
        } else {
            skillTask = "Normal logs";
        }
        return skillTask;
    }

    public static String firemaking() {
        xp = BoredPanel.xp;

        if (xp > lvls[5]) {
            skillTask = "Wintertodt";
        } else if (xp > lvls[3]) {
            skillTask = "Willow";
        }  else if (xp > 2_411) {
            skillTask = "Oak";
        } else {
            skillTask = "Normal";
        }
        return skillTask;
    }


    public static String agility() {
        xp = BoredPanel.xp;

        if (xp > lvls[9]) {
            skillTask = "Ardougne Rooftop Course";
        } else if (xp > lvls[8]) {
            skillTask = "Rellekka Rooftop Course";
        } else if (xp > lvls[6]) {
            skillTask = "Seers' Village Rooftop Course";
        } else if (xp > lvls[5]) {
            skillTask = "Falador Rooftop Course";
        } else if (xp > lvls[4]) {
            skillTask = "Canifis Rooptop Course";
        } else if (xp > lvls[3]) {
            skillTask = "Varrock Rooftop Course";
        } else if (xp > lvls[2]) {
            skillTask = "Al Kharid Rooftop Course";
        } else if (xp > lvls[1]) {
            skillTask = "Draynor Village Rooftop Course";
        } else {
            skillTask = "Gnome Stronghold Agility Course";
        }
        return skillTask;
    }

    public static String theiving() {
        xp = BoredPanel.xp;
        if (xp > 166_636) {
            skillTask = "Knights of Ardougne";
        } else if (xp > 7_842) {
            skillTask = "Fruit stalls";
        } else if(xp > 2_411) {
            skillTask = "Bakery stalls";
        } else {
            skillTask = "man/women";
        }
        return skillTask;
    }
    public static String cooking() {
        xp = BoredPanel.xp;
        if (xp > 2_951_373 ) {
            skillTask = "Raw Anglers";
        } else if (xp > lvls[8]) {
            skillTask = "Raw Sharks";
        } else if (xp > 22_406) {
            skillTask = "Raw karambwans";
        } else if (xp > 7_842) {
            skillTask = "Raw Salmons";
        } else if (xp > 2_411) {
            skillTask = "Raw Trouts";
        } else {
            skillTask = "Raw Sardines";
        }
        return skillTask;
    }
    public static String crafting() {
        xp = BoredPanel.xp;
        if (xp > 496_254) {
            skillTask = "Air Battlestaves";
        } else if (xp > 368_599) {
            skillTask = "Green Dragonhide Bodies";
        } else if (xp > 150_872) {
            skillTask = "Water Battlestaves";
        } else if (xp > 9_730) {
            skillTask = "Emeralds";
        } else if (xp > lvls[2]) {
            skillTask = "Sapphires";
        } else {
            skillTask = "Opals";
        }
        return skillTask;
    }
    public static String fishing() {
        xp = BoredPanel.xp;
        if (xp > 224_466) {
            skillTask = "Leaping Salmon/Sturgeon";
        } else if (xp > lvls[2]) {
            skillTask = "Trout/Salmon";
        } else {
            skillTask = "Shrimp/Anchovy";
        }
        return skillTask;
    }
    public static String herblore() {
        xp = BoredPanel.xp;
        if (xp > lvls[9]) {
            skillTask = "Super Combat";
        }
        else if (xp > 368_599) {
            skillTask = "Super Restore";
        } else if (xp > 30_408 ) {
            skillTask = "Prayer Potions";
        } else {
            skillTask = "Attack Potions";
        }
        return skillTask;
    }
    public static String fletching() {
        xp = BoredPanel.xp;
        if (xp > 3_258_594) {
            skillTask = "Magic long bows";
        }
        else if (xp > lvls[7]) {
            skillTask = "Yew long bows";
        } else if (xp > 22_406 ) {
            skillTask = "Steel arrows";
        } else {
            skillTask = "Iron darts";
        }
        return skillTask;
    }
    public static String construction() {
        xp = BoredPanel.xp;
        if (xp > 1_096_278) {
            skillTask = "Oak Dungeon Doors";
        } else if (xp > 18_247) {
            skillTask = "Oak Larders";
        } else if (xp > 8_740) {
            skillTask = "Oak armchairs";
        } else if (xp > 3_973) {
            skillTask = "Oak chairs";
        } else if (xp > 801) {
            skillTask = "Wooden chairs";
        } else {
            skillTask = "Crude wooden chairs";
        }
        return skillTask;
    }
    public static String hunter() {
        xp = BoredPanel.xp;
        if(xp > 992_895) {
            skillTask = "Black chinchompa's";
        } else if(xp > 368_599) {
            skillTask = "Red chinchompa's";
        } else if(xp > 203_254) {
            skillTask = "Dark/Spotted Kebbits";
        } else if (xp > 50_339) {
            skillTask = "Spotted Kebbits";
        } else if (xp > 27_473) {
            skillTask = "Prickly Kebbits";
        } else if (xp > 18_247) {
            skillTask = "Crimson Swifts";
        } else if (xp > 2_411) {
            skillTask = "Ruby Butterflies";
        } else if (xp > 650) {
            skillTask = "Feldip Weasels";
        } else {
            skillTask = "Polar Kebbits";
        }
        return skillTask;
    }
    public static String mining() {
        xp = BoredPanel.xp;
        if (xp > 737_627) {
            skillTask = "Volcanic Mine";
        } else if (xp > lvls[3]) {
            skillTask = "Motherlode Mine";
        } else if (xp > 2_411) {
            skillTask = "Iron ore";
        } else {
            skillTask = "Copper/Tin ore";
        }
        return skillTask;
    }
    public static String smithing() {
        xp = BoredPanel.xp;
        if (xp > 37_224) {
            skillTask = "Gold bars (at the blast furnace)";
        } else if (xp > 18_247) {
            skillTask = "Iron Platebodies";
        } else if (xp > 12_031) {
            skillTask = "Iron 2h Swords";
        } else {
            skillTask = "Just do the knights sword quest cmonn!";
        }
        return skillTask;
    }
    public static String prayer() {
        xp = BoredPanel.xp;
            skillTask = "Dragon bones";
        return skillTask;
    }
    public static String runecrafting() {
        xp = BoredPanel.xp;
        if (xp > lvls[9]) {
            skillTask = "Soul runes";
        } else if (xp > 1_475_581) {
            skillTask = "Blood runes";
        } else {
            skillTask = "Ourania Altar";
        }
        return skillTask;
    }



}
