package com.collector.inventory.infrastructure.web;

import com.collector.inventory.application.dto.InventoryItemDTO;
import com.collector.inventory.application.service.ExcelExportService;
import com.collector.inventory.application.service.InventoryItemService;
import com.collector.inventory.domain.model.ItemCategory;
import com.collector.inventory.domain.model.ItemCondition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * REST Controller for inventory item operations.
 * Provides RESTful API endpoints following best practices.
 */
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "${cors.allowed-origins:http://localhost:5173}")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;
    private final ExcelExportService excelExportService;

    @GetMapping
    public ResponseEntity<List<InventoryItemDTO>> getAllItems() {
        log.info("GET /api/inventory - Fetching all items");
        List<InventoryItemDTO> items = inventoryItemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> getItemById(@PathVariable Long id) {
        log.info("GET /api/inventory/{} - Fetching item by id", id);
        InventoryItemDTO item = inventoryItemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<InventoryItemDTO> createItem(@Valid @RequestBody InventoryItemDTO itemDTO) {
        log.info("POST /api/inventory - Creating new item: {}", itemDTO.getName());
        InventoryItemDTO createdItem = inventoryItemService.createItem(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody InventoryItemDTO itemDTO) {
        log.info("PUT /api/inventory/{} - Updating item", id);
        InventoryItemDTO updatedItem = inventoryItemService.updateItem(id, itemDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        log.info("DELETE /api/inventory/{} - Deleting item", id);
        inventoryItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<InventoryItemDTO>> searchItems(@RequestParam String term) {
        log.info("GET /api/inventory/search?term={} - Searching items", term);
        List<InventoryItemDTO> items = inventoryItemService.searchItems(term);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<InventoryItemDTO>> getItemsByCategory(@PathVariable ItemCategory category) {
        log.info("GET /api/inventory/category/{} - Fetching items by category", category);
        List<InventoryItemDTO> items = inventoryItemService.getItemsByCategory(category);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/condition/{condition}")
    public ResponseEntity<List<InventoryItemDTO>> getItemsByCondition(@PathVariable ItemCondition condition) {
        log.info("GET /api/inventory/condition/{} - Fetching items by condition", condition);
        List<InventoryItemDTO> items = inventoryItemService.getItemsByCondition(condition);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryItemDTO>> getLowStockItems() {
        log.info("GET /api/inventory/low-stock - Fetching low stock items");
        List<InventoryItemDTO> items = inventoryItemService.getLowStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        log.info("GET /api/inventory/export/excel - Exporting inventory to Excel");
        List<InventoryItemDTO> items = inventoryItemService.getAllItems();
        byte[] excelData = excelExportService.exportToExcel(items);

        String filename = "inventory_export_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +
                ".xlsx";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentLength(excelData.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }

    @GetMapping("/categories")
    public ResponseEntity<ItemCategory[]> getAllCategories() {
        return ResponseEntity.ok(ItemCategory.values());
    }

    @GetMapping("/conditions")
    public ResponseEntity<ItemCondition[]> getAllConditions() {
        return ResponseEntity.ok(ItemCondition.values());
    }
}
