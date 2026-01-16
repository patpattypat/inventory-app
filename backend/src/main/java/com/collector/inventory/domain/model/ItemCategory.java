package com.collector.inventory.domain.model;

/**
 * Enum representing the category of inventory items.
 * Specialized for aviation and automotive parts collection.
 */
public enum ItemCategory {
    // Aviation Parts
    AIRCRAFT_ENGINE("Aircraft Engine"),
    AIRCRAFT_PROPELLER("Aircraft Propeller"),
    AIRCRAFT_INSTRUMENTS("Aircraft Instruments"),
    AIRCRAFT_AVIONICS("Aircraft Avionics"),
    AIRCRAFT_STRUCTURAL("Aircraft Structural Parts"),
    AIRCRAFT_LANDING_GEAR("Aircraft Landing Gear"),
    AIRCRAFT_CONTROL_SURFACES("Aircraft Control Surfaces"),
    AIRCRAFT_FUEL_SYSTEM("Aircraft Fuel System"),
    AIRCRAFT_ELECTRICAL("Aircraft Electrical System"),
    AIRCRAFT_INTERIOR("Aircraft Interior Components"),

    // Automotive Parts
    CAR_ENGINE("Car Engine"),
    CAR_TRANSMISSION("Car Transmission"),
    CAR_SUSPENSION("Car Suspension"),
    CAR_BRAKES("Car Brakes"),
    CAR_ELECTRICAL("Car Electrical System"),
    CAR_INTERIOR("Car Interior"),
    CAR_EXTERIOR("Car Exterior/Body"),
    CAR_EXHAUST("Car Exhaust System"),
    CAR_FUEL_SYSTEM("Car Fuel System"),
    CAR_STEERING("Car Steering System"),

    // General/Other
    TOOLS("Tools and Equipment"),
    MANUALS("Manuals and Documentation"),
    MEMORABILIA("Memorabilia and Collectibles"),
    OTHER("Other");

    private final String displayName;

    ItemCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
