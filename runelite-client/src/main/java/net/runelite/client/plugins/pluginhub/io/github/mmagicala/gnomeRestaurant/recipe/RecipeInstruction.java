package net.runelite.client.plugins.pluginhub.io.github.mmagicala.gnomeRestaurant.recipe;

public enum RecipeInstruction {
    CREATE_RAW("Create the raw batter"),
    CREATE_HALF_BAKED("Bake the raw batter"),
    COMBINE_INGREDIENTS("Combine ingredients"),
    BAKE_HALF_MADE("Bake item again"),
    ADD_TOPPINGS("Add topping ingredients"),

    MIX_COCKTAIL("Mix ingredients in the cocktail shaker"),
    POUR("Pour mix into the cocktail glass"),
    HEAT_COCKTAIL("Heat the cocktail"),

    DELIVER("Deliver the item to recipient");

    private final String overlayDirections;

    RecipeInstruction(String overlayDirections) {
        this.overlayDirections = overlayDirections;
    }

    public String getOverlayDirections() {
        return this.overlayDirections;
    }
}
