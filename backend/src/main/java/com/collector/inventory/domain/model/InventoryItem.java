package com.collector.inventory.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Domain Entity representing an inventory item for aviation and car parts collection.
 * This entity follows clean architecture principles by encapsulating all business logic
 * and maintaining rich domain information for collector purposes.
 */
@Entity
@Table(name = "inventory_items", indexes = {
    @Index(name = "idx_category", columnList = "category"),
    @Index(name = "idx_manufacturer", columnList = "manufacturer"),
    @Index(name = "idx_part_number", columnList = "partNumber")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be zero or positive")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ItemCategory category;

    // Financial Information
    @PositiveOrZero(message = "Value must be zero or positive")
    @Column(precision = 10, scale = 2)
    private BigDecimal estimatedValue;

    @Column(precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    private LocalDate purchaseDate;

    // Manufacturer and Technical Details
    @Column(length = 255)
    private String manufacturer;

    @Column(length = 255)
    private String partNumber;

    @Column(length = 255)
    private String serialNumber;

    @Column(length = 255)
    private String model;

    @Column(length = 100)
    private String yearOfManufacture;

    // Condition and Location
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ItemCondition condition;

    @Column(length = 255)
    private String storageLocation;

    // Provenance Information
    @Column(columnDefinition = "TEXT")
    private String provenance;

    @Column(columnDefinition = "TEXT")
    private String compositionMaterials;

    // Certification and Documentation
    @Column(length = 255)
    private String certificationNumber;

    private Boolean hasCertificate;

    @Column(columnDefinition = "TEXT")
    private String documentationNotes;

    // Metadata
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * Business method to check if item needs attention (low quantity or missing critical info)
     */
    public boolean needsAttention() {
        return quantity <= 1 || serialNumber == null || condition == ItemCondition.POOR;
    }
}
