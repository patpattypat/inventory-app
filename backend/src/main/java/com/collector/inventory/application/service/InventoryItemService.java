package com.collector.inventory.application.service;

import com.collector.inventory.application.dto.InventoryItemDTO;
import com.collector.inventory.application.mapper.InventoryItemMapper;
import com.collector.inventory.domain.model.InventoryItem;
import com.collector.inventory.domain.model.ItemCategory;
import com.collector.inventory.domain.model.ItemCondition;
import com.collector.inventory.domain.repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Application Service for managing inventory items.
 * Contains business logic and orchestrates domain operations.
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InventoryItemService {

    private final InventoryItemRepository repository;
    private final InventoryItemMapper mapper;

    /**
     * Retrieves all inventory items.
     */
    @Transactional(readOnly = true)
    public List<InventoryItemDTO> getAllItems() {
        log.info("Fetching all inventory items");
        List<InventoryItem> items = repository.findAll();
        return mapper.toDTOList(items);
    }

    /**
     * Retrieves a single inventory item by ID.
     */
    @Transactional(readOnly = true)
    public InventoryItemDTO getItemById(Long id) {
        log.info("Fetching inventory item with id: {}", id);
        InventoryItem item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with id: " + id));
        return mapper.toDTO(item);
    }

    /**
     * Creates a new inventory item.
     */
    public InventoryItemDTO createItem(InventoryItemDTO itemDTO) {
        log.info("Creating new inventory item: {}", itemDTO.getName());
        InventoryItem item = mapper.toEntity(itemDTO);
        InventoryItem savedItem = repository.save(item);
        log.info("Successfully created inventory item with id: {}", savedItem.getId());
        return mapper.toDTO(savedItem);
    }

    /**
     * Updates an existing inventory item.
     */
    public InventoryItemDTO updateItem(Long id, InventoryItemDTO itemDTO) {
        log.info("Updating inventory item with id: {}", id);
        InventoryItem existingItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with id: " + id));

        // Update fields
        existingItem.setName(itemDTO.getName());
        existingItem.setDescription(itemDTO.getDescription());
        existingItem.setQuantity(itemDTO.getQuantity());
        existingItem.setCategory(itemDTO.getCategory());
        existingItem.setEstimatedValue(itemDTO.getEstimatedValue());
        existingItem.setPurchasePrice(itemDTO.getPurchasePrice());
        existingItem.setPurchaseDate(itemDTO.getPurchaseDate());
        existingItem.setManufacturer(itemDTO.getManufacturer());
        existingItem.setPartNumber(itemDTO.getPartNumber());
        existingItem.setSerialNumber(itemDTO.getSerialNumber());
        existingItem.setModel(itemDTO.getModel());
        existingItem.setYearOfManufacture(itemDTO.getYearOfManufacture());
        existingItem.setCondition(itemDTO.getCondition());
        existingItem.setStorageLocation(itemDTO.getStorageLocation());
        existingItem.setProvenance(itemDTO.getProvenance());
        existingItem.setCompositionMaterials(itemDTO.getCompositionMaterials());
        existingItem.setCertificationNumber(itemDTO.getCertificationNumber());
        existingItem.setHasCertificate(itemDTO.getHasCertificate());
        existingItem.setDocumentationNotes(itemDTO.getDocumentationNotes());
        existingItem.setNotes(itemDTO.getNotes());

        InventoryItem updatedItem = repository.save(existingItem);
        log.info("Successfully updated inventory item with id: {}", id);
        return mapper.toDTO(updatedItem);
    }

    /**
     * Deletes an inventory item.
     */
    public void deleteItem(Long id) {
        log.info("Deleting inventory item with id: {}", id);
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Inventory item not found with id: " + id);
        }
        repository.deleteById(id);
        log.info("Successfully deleted inventory item with id: {}", id);
    }

    /**
     * Searches items by search term (name, manufacturer, part number, serial number).
     */
    @Transactional(readOnly = true)
    public List<InventoryItemDTO> searchItems(String searchTerm) {
        log.info("Searching inventory items with term: {}", searchTerm);
        List<InventoryItem> items = repository.searchByTerm(searchTerm);
        return mapper.toDTOList(items);
    }

    /**
     * Retrieves items by category.
     */
    @Transactional(readOnly = true)
    public List<InventoryItemDTO> getItemsByCategory(ItemCategory category) {
        log.info("Fetching inventory items by category: {}", category);
        List<InventoryItem> items = repository.findByCategory(category);
        return mapper.toDTOList(items);
    }

    /**
     * Retrieves items by condition.
     */
    @Transactional(readOnly = true)
    public List<InventoryItemDTO> getItemsByCondition(ItemCondition condition) {
        log.info("Fetching inventory items by condition: {}", condition);
        List<InventoryItem> items = repository.findByCondition(condition);
        return mapper.toDTOList(items);
    }

    /**
     * Retrieves low stock items (quantity <= 1).
     */
    @Transactional(readOnly = true)
    public List<InventoryItemDTO> getLowStockItems() {
        log.info("Fetching low stock inventory items");
        List<InventoryItem> items = repository.findLowStockItems();
        return mapper.toDTOList(items);
    }
}
