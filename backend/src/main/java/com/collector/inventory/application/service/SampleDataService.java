package com.collector.inventory.application.service;

import com.collector.inventory.domain.model.InventoryItem;
import com.collector.inventory.domain.model.ItemCategory;
import com.collector.inventory.domain.model.ItemCondition;
import com.collector.inventory.domain.repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for generating sample/test data for the inventory system.
 * Provides realistic aviation and automotive parts data for testing and demonstration.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SampleDataService {

    private final InventoryItemRepository repository;

    /**
     * Loads sample data into the database.
     * This will create sample aviation and automotive parts.
     */
    @Transactional
    public void loadSampleData() {
        log.info("Loading sample data...");

        List<InventoryItem> sampleItems = new ArrayList<>();

        // Aviation Parts
        sampleItems.add(createAviationEngine());
        sampleItems.add(createAircraftPropeller());
        sampleItems.add(createAircraftInstrument());
        sampleItems.add(createAircraftAvionics());
        sampleItems.add(createAircraftLandingGear());

        // Automotive Parts
        sampleItems.add(createCarEngine());
        sampleItems.add(createCarTransmission());
        sampleItems.add(createVintageCarburator());
        sampleItems.add(createClassicSteering());
        sampleItems.add(createRacingExhaust());

        // Memorabilia
        sampleItems.add(createManual());
        sampleItems.add(createMemorabilia());

        repository.saveAll(sampleItems);
        log.info("Successfully loaded {} sample items", sampleItems.size());
    }

    /**
     * Checks if sample data already exists.
     */
    @Transactional(readOnly = true)
    public boolean hasSampleData() {
        return repository.count() > 0;
    }

    /**
     * Deletes all data from the database.
     */
    @Transactional
    public void deleteAllData() {
        log.info("Deleting all data...");
        repository.deleteAll();
        log.info("All data deleted");
    }

    private InventoryItem createAviationEngine() {
        return InventoryItem.builder()
                .name("Pratt & Whitney R-2800 Double Wasp")
                .description("18-cylinder, two-row radial aircraft engine. One of the most powerful and reliable piston engines ever built. Powered iconic aircraft like the P-47 Thunderbolt and F4U Corsair.")
                .quantity(1)
                .category(ItemCategory.AIRCRAFT_ENGINE)
                .manufacturer("Pratt & Whitney")
                .partNumber("R-2800-59")
                .serialNumber("P&W-428765")
                .model("R-2800 Series C")
                .yearOfManufacture("1944")
                .condition(ItemCondition.GOOD)
                .storageLocation("Hangar A, Section 2")
                .estimatedValue(new BigDecimal("125000.00"))
                .purchasePrice(new BigDecimal("95000.00"))
                .purchaseDate(LocalDate.of(2022, 6, 15))
                .provenance("Originally installed in P-47D s/n 42-26641. Engine overhauled in 1945, stored at Davis-Monthan AFB until 1987. Acquired from private collector in Arizona.")
                .compositionMaterials("Forged aluminum alloy cylinders, steel crankcase, magnesium accessory case")
                .certificationNumber("FAA 8130-3 TAG-2800-441")
                .hasCertificate(true)
                .documentationNotes("Complete logbooks from 1944-1945, overhaul documentation, FAA export certificate")
                .notes("Engine requires restoration. Cylinders need re-boring, crankshaft needs inspection. All major components present.")
                .build();
    }

    private InventoryItem createAircraftPropeller() {
        return InventoryItem.builder()
                .name("Hamilton Standard Hydromatic Propeller")
                .description("3-blade constant-speed propeller with hydraulic pitch control. Used on various military and commercial aircraft of the 1940s-1950s.")
                .quantity(1)
                .category(ItemCategory.AIRCRAFT_PROPELLER)
                .manufacturer("Hamilton Standard")
                .partNumber("23E50-503")
                .serialNumber("HS-15427")
                .model("Hydromatic 23E50")
                .yearOfManufacture("1943")
                .condition(ItemCondition.EXCELLENT)
                .storageLocation("Hangar B, Rack 5")
                .estimatedValue(new BigDecimal("28000.00"))
                .purchasePrice(new BigDecimal("22000.00"))
                .purchaseDate(LocalDate.of(2023, 3, 10))
                .provenance("Removed from F6F-5 Hellcat during post-war overhaul. Properly preserved and stored.")
                .compositionMaterials("Forged aluminum alloy blades, steel hub, bronze pitch mechanism")
                .certificationNumber("Navy BuAer Acceptance Stamp visible")
                .hasCertificate(false)
                .documentationNotes("Original Navy acceptance documentation, removal log from 1946")
                .notes("Propeller is in excellent condition. All three blades are straight with no corrosion. Pitch mechanism operates smoothly.")
                .build();
    }

    private InventoryItem createAircraftInstrument() {
        return InventoryItem.builder()
                .name("Sperry A-12 Autopilot Gyroscope")
                .description("Primary gyroscopic instrument for the Sperry A-12 automatic pilot system. Used in B-17 and B-24 bombers.")
                .quantity(1)
                .category(ItemCategory.AIRCRAFT_INSTRUMENTS)
                .manufacturer("Sperry Gyroscope Company")
                .partNumber("652404")
                .serialNumber("SGC-89234")
                .yearOfManufacture("1943")
                .condition(ItemCondition.MINT)
                .storageLocation("Climate-controlled vault, Shelf 3")
                .estimatedValue(new BigDecimal("8500.00"))
                .purchasePrice(new BigDecimal("6200.00"))
                .purchaseDate(LocalDate.of(2023, 8, 22))
                .provenance("New old stock, never installed. Found in sealed crate at former Sperry facility in New York.")
                .compositionMaterials("Brass housing, steel gyroscope, glass dial face")
                .hasCertificate(false)
                .documentationNotes("Original Sperry technical manual included")
                .notes("Absolutely pristine condition. Still has factory cosmoline preservation coating. Original box and packing materials.")
                .build();
    }

    private InventoryItem createAircraftAvionics() {
        return InventoryItem.builder()
                .name("Collins 51R-3 VHF Radio Receiver")
                .description("Aircraft VHF communications receiver, 118-136 MHz. Standard equipment in post-war civil and military aircraft.")
                .quantity(1)
                .category(ItemCategory.AIRCRAFT_AVIONICS)
                .manufacturer("Collins Radio Company")
                .partNumber("51R-3")
                .serialNumber("CR-33891")
                .model("51R-3")
                .yearOfManufacture("1952")
                .condition(ItemCondition.FAIR)
                .storageLocation("Workshop, Radio Bench")
                .estimatedValue(new BigDecimal("1200.00"))
                .purchasePrice(new BigDecimal("850.00"))
                .purchaseDate(LocalDate.of(2024, 1, 5))
                .compositionMaterials("Aluminum chassis, vacuum tubes, bakelite knobs")
                .hasCertificate(false)
                .notes("Unit powers on but needs alignment. Several capacitors need replacement. Good restoration candidate.")
                .build();
    }

    private InventoryItem createAircraftLandingGear() {
        return InventoryItem.builder()
                .name("North American P-51 Mustang Main Landing Gear Strut")
                .description("Main landing gear oleo strut assembly from P-51D Mustang fighter aircraft.")
                .quantity(2)
                .category(ItemCategory.AIRCRAFT_LANDING_GEAR)
                .manufacturer("North American Aviation")
                .partNumber("106-810020-1")
                .serialNumber("NAA-P51-4456-L")
                .yearOfManufacture("1944")
                .condition(ItemCondition.POOR)
                .storageLocation("Outdoor storage yard, Section D")
                .estimatedValue(new BigDecimal("4500.00"))
                .purchasePrice(new BigDecimal("3200.00"))
                .purchaseDate(LocalDate.of(2021, 11, 8))
                .provenance("Salvaged from crashed P-51D in England, 1945. Brought to US by veteran in 1946.")
                .compositionMaterials("Chrome-molybdenum steel strut, aluminum fairing")
                .notes("Heavy corrosion present. Chrome plating deteriorated. Needs complete restoration or use as pattern for reproduction.")
                .build();
    }

    private InventoryItem createCarEngine() {
        return InventoryItem.builder()
                .name("Chevrolet Small Block 327 V8")
                .description("Classic 327 cubic inch V8 engine from 1967 Corvette Sting Ray. One of Chevy's most legendary small blocks.")
                .quantity(1)
                .category(ItemCategory.CAR_ENGINE)
                .manufacturer("General Motors - Chevrolet Division")
                .partNumber("3896962")
                .serialNumber("T0104HE")
                .model("327 L79")
                .yearOfManufacture("1967")
                .condition(ItemCondition.EXCELLENT)
                .storageLocation("Garage, Engine Stand #2")
                .estimatedValue(new BigDecimal("18500.00"))
                .purchasePrice(new BigDecimal("15000.00"))
                .purchaseDate(LocalDate.of(2023, 5, 20))
                .provenance("Original engine from matching-numbers 1967 Corvette. Professionally rebuilt in 2015 with less than 500 miles since.")
                .compositionMaterials("Cast iron block, forged steel crankshaft, aluminum intake manifold")
                .documentationNotes("Complete rebuild documentation, dyno sheets showing 355hp, original GM engine ID tag")
                .notes("Engine has been properly broken in and runs perfectly. Includes correct Winters aluminum intake, Holley 3367 carburetor, and Corvette exhaust manifolds.")
                .build();
    }

    private InventoryItem createCarTransmission() {
        return InventoryItem.builder()
                .name("Porsche 915 5-Speed Manual Transmission")
                .description("5-speed manual transmission from 1987 Porsche 911 Carrera. The last year of the legendary G50's predecessor.")
                .quantity(1)
                .category(ItemCategory.CAR_TRANSMISSION)
                .manufacturer("Porsche AG")
                .partNumber("915/67")
                .serialNumber("5680384")
                .model("915/67")
                .yearOfManufacture("1987")
                .condition(ItemCondition.GOOD)
                .storageLocation("Garage, Shelf Unit 3")
                .estimatedValue(new BigDecimal("4200.00"))
                .purchasePrice(new BigDecimal("3500.00"))
                .purchaseDate(LocalDate.of(2022, 9, 14))
                .compositionMaterials("Aluminum case, steel gears, brass synchronizers")
                .notes("Shifts smoothly through all gears. 2nd gear synchro is slightly worn but functional. Would benefit from fresh fluid and synchro replacement.")
                .build();
    }

    private InventoryItem createVintageCarburator() {
        return InventoryItem.builder()
                .name("Stromberg 97 Carburetor")
                .description("Classic 2-barrel carburetor popular in hot rod and custom applications. Original Ford flathead V8 equipment.")
                .quantity(3)
                .category(ItemCategory.CAR_FUEL_SYSTEM)
                .manufacturer("Bendix Stromberg")
                .partNumber("9510")
                .yearOfManufacture("1948")
                .condition(ItemCondition.FOR_RESTORATION)
                .storageLocation("Parts cabinet, Drawer 8")
                .estimatedValue(new BigDecimal("750.00"))
                .purchasePrice(new BigDecimal("450.00"))
                .purchaseDate(LocalDate.of(2024, 2, 3))
                .compositionMaterials("Zinc die-cast body, brass jets, steel throttle plates")
                .notes("All three carbs need complete rebuild. Bodies are intact but need cleaning and replating. Good cores for restoration.")
                .build();
    }

    private InventoryItem createClassicSteering() {
        return InventoryItem.builder()
                .name("Vintage Nardi Classic Wood Steering Wheel")
                .description("14-inch wood rim steering wheel with polished aluminum spokes. Classic Italian craftsmanship from the 1960s.")
                .quantity(1)
                .category(ItemCategory.CAR_INTERIOR)
                .manufacturer("Nardi-Torino")
                .model("Classic")
                .yearOfManufacture("1965")
                .condition(ItemCondition.EXCELLENT)
                .storageLocation("Display case, Living room")
                .estimatedValue(new BigDecimal("1850.00"))
                .purchasePrice(new BigDecimal("1400.00"))
                .purchaseDate(LocalDate.of(2023, 7, 18))
                .provenance("Removed from 1966 Ferrari 275 GTB during restoration. Original to the car.")
                .compositionMaterials("Mahogany wood rim with multiple varnish coats, polished aluminum spokes, aluminum center hub")
                .documentationNotes("Certificate of authenticity from Nardi, letter from Ferrari Classiche confirming originality")
                .notes("Wood is in beautiful condition with deep patina. All aluminum polished to mirror finish. Original horn button included.")
                .build();
    }

    private InventoryItem createRacingExhaust() {
        return InventoryItem.builder()
                .name("Akrapovic Titanium Racing Exhaust System")
                .description("Full titanium exhaust system for Porsche 911 GT3 (991.2). Race-spec with no catalysts, maximum performance.")
                .quantity(1)
                .category(ItemCategory.CAR_EXHAUST)
                .manufacturer("Akrapovic")
                .partNumber("EVO-PO/TI/6H")
                .serialNumber("AK-45829")
                .yearOfManufacture("2018")
                .condition(ItemCondition.MINT)
                .storageLocation("Garage, Wall mount")
                .estimatedValue(new BigDecimal("8900.00"))
                .purchasePrice(new BigDecimal("7500.00"))
                .purchaseDate(LocalDate.of(2023, 10, 5))
                .compositionMaterials("Grade 2 titanium throughout, carbon fiber tips")
                .certificationNumber("FIA Approved - FIA-2018-GT3-EXH-002")
                .hasCertificate(true)
                .documentationNotes("FIA homologation certificate, Akrapovic installation manual, dyno chart showing +18hp gain")
                .notes("Essentially new condition, only 200 miles of use. Removed when car was returned to stock for sale. Sounds absolutely incredible.")
                .build();
    }

    private InventoryItem createManual() {
        return InventoryItem.builder()
                .name("B-17 Flying Fortress Pilot's Flight Manual")
                .description("Original USAAF pilot's flight operating manual for the B-17G Flying Fortress heavy bomber.")
                .quantity(1)
                .category(ItemCategory.MANUALS)
                .manufacturer("U.S. Army Air Forces")
                .partNumber("AN 01-20EG-1")
                .yearOfManufacture("1944")
                .condition(ItemCondition.GOOD)
                .storageLocation("Library, Bookshelf 2")
                .estimatedValue(new BigDecimal("650.00"))
                .purchasePrice(new BigDecimal("485.00"))
                .purchaseDate(LocalDate.of(2022, 12, 10))
                .provenance("Estate sale from family of B-17 pilot. Contains pilot's handwritten notes in margins.")
                .compositionMaterials("Wartime paper, cloth cover")
                .notes("Manual is complete with all pages present. Some yellowing and edge wear consistent with age. Handwritten notes add historical value.")
                .build();
    }

    private InventoryItem createMemorabilia() {
        return InventoryItem.builder()
                .name("1955 Le Mans 24 Hours Race Program")
                .description("Official race program from the tragic 1955 24 Hours of Le Mans. Historic significance due to the accident that killed 83 spectators.")
                .quantity(1)
                .category(ItemCategory.MEMORABILIA)
                .yearOfManufacture("1955")
                .condition(ItemCondition.EXCELLENT)
                .storageLocation("Climate-controlled safe, Document box 4")
                .estimatedValue(new BigDecimal("1200.00"))
                .purchasePrice(new BigDecimal("900.00"))
                .purchaseDate(LocalDate.of(2023, 6, 8))
                .provenance("Purchased from French collector. Includes original ticket stub.")
                .compositionMaterials("Newsprint paper, color covers")
                .documentationNotes("Certificate of authenticity from memorabilia authenticator")
                .notes("Program is in remarkable condition for its age. All pages intact, minimal yellowing. Includes entry list, circuit map, and advertising from period sponsors.")
                .build();
    }
}
