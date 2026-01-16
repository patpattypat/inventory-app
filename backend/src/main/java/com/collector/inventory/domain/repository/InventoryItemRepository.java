package com.collector.inventory.domain.repository;

import com.collector.inventory.domain.model.InventoryItem;
import com.collector.inventory.domain.model.ItemCategory;
import com.collector.inventory.domain.model.ItemCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for InventoryItem entities.
 * Following clean architecture, this is part of the domain layer.
 */
@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByCategory(ItemCategory category);

    List<InventoryItem> findByCondition(ItemCondition condition);

    List<InventoryItem> findByManufacturerContainingIgnoreCase(String manufacturer);

    List<InventoryItem> findByNameContainingIgnoreCase(String name);

    @Query("SELECT i FROM InventoryItem i WHERE " +
           "LOWER(i.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(i.manufacturer) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(i.partNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(i.serialNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<InventoryItem> searchByTerm(String searchTerm);

    @Query("SELECT i FROM InventoryItem i WHERE i.quantity <= 1")
    List<InventoryItem> findLowStockItems();
}
