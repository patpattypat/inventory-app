package com.collector.inventory.domain.model;

/**
 * Enum representing the physical condition of an inventory item.
 * Important for valuation and restoration planning.
 */
public enum ItemCondition {
    MINT("Mint - New or like new condition"),
    EXCELLENT("Excellent - Minor wear, fully functional"),
    GOOD("Good - Some wear, fully functional"),
    FAIR("Fair - Significant wear, functional with issues"),
    POOR("Poor - Heavy wear, needs restoration"),
    FOR_RESTORATION("For Restoration - Not functional, restoration project"),
    PARTS_ONLY("Parts Only - Not functional, for parts harvest");

    private final String description;

    ItemCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
