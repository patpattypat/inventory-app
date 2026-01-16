package com.collector.inventory.application.dto;

import com.collector.inventory.domain.model.ItemCategory;
import com.collector.inventory.domain.model.ItemCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * Data Transfer Object for InventoryItem.
 * Used for communication between layers and API responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be zero or positive")
    private Integer quantity;

    @NotNull(message = "Category is required")
    private ItemCategory category;

    @PositiveOrZero(message = "Value must be zero or positive")
    private BigDecimal estimatedValue;

    private BigDecimal purchasePrice;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    private String manufacturer;
    private String partNumber;
    private String serialNumber;
    private String model;
    private String yearOfManufacture;

    private ItemCondition condition;
    private String storageLocation;

    private String provenance;
    private String compositionMaterials;

    private String certificationNumber;
    private Boolean hasCertificate;
    private String documentationNotes;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    private String notes;
}
