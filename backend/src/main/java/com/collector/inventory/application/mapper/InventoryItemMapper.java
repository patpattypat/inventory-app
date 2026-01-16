package com.collector.inventory.application.mapper;

import com.collector.inventory.application.dto.InventoryItemDTO;
import com.collector.inventory.domain.model.InventoryItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between InventoryItem entities and DTOs.
 * Follows clean code principles with explicit mapping logic.
 */
@Component
public class InventoryItemMapper {

    public InventoryItemDTO toDTO(InventoryItem entity) {
        if (entity == null) {
            return null;
        }

        return InventoryItemDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .quantity(entity.getQuantity())
                .category(entity.getCategory())
                .estimatedValue(entity.getEstimatedValue())
                .purchasePrice(entity.getPurchasePrice())
                .purchaseDate(entity.getPurchaseDate())
                .manufacturer(entity.getManufacturer())
                .partNumber(entity.getPartNumber())
                .serialNumber(entity.getSerialNumber())
                .model(entity.getModel())
                .yearOfManufacture(entity.getYearOfManufacture())
                .condition(entity.getCondition())
                .storageLocation(entity.getStorageLocation())
                .provenance(entity.getProvenance())
                .compositionMaterials(entity.getCompositionMaterials())
                .certificationNumber(entity.getCertificationNumber())
                .hasCertificate(entity.getHasCertificate())
                .documentationNotes(entity.getDocumentationNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .notes(entity.getNotes())
                .build();
    }

    public InventoryItem toEntity(InventoryItemDTO dto) {
        if (dto == null) {
            return null;
        }

        return InventoryItem.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .category(dto.getCategory())
                .estimatedValue(dto.getEstimatedValue())
                .purchasePrice(dto.getPurchasePrice())
                .purchaseDate(dto.getPurchaseDate())
                .manufacturer(dto.getManufacturer())
                .partNumber(dto.getPartNumber())
                .serialNumber(dto.getSerialNumber())
                .model(dto.getModel())
                .yearOfManufacture(dto.getYearOfManufacture())
                .condition(dto.getCondition())
                .storageLocation(dto.getStorageLocation())
                .provenance(dto.getProvenance())
                .compositionMaterials(dto.getCompositionMaterials())
                .certificationNumber(dto.getCertificationNumber())
                .hasCertificate(dto.getHasCertificate())
                .documentationNotes(dto.getDocumentationNotes())
                .notes(dto.getNotes())
                .build();
    }

    public List<InventoryItemDTO> toDTOList(List<InventoryItem> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
