package com.collector.inventory.infrastructure.web;

import com.collector.inventory.application.service.SampleDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for sample/test data operations.
 * Allows loading sample data for testing and demonstration purposes.
 */
@RestController
@RequestMapping("/api/sample-data")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "${cors.allowed-origins:http://localhost:5173}")
public class SampleDataController {

    private final SampleDataService sampleDataService;

    @PostMapping("/load")
    public ResponseEntity<Map<String, Object>> loadSampleData() {
        log.info("POST /api/sample-data/load - Loading sample data");

        Map<String, Object> response = new HashMap<>();

        if (sampleDataService.hasSampleData()) {
            response.put("success", false);
            response.put("message", "Database already contains data. Delete existing data first if you want to reload samples.");
            response.put("hasData", true);
            return ResponseEntity.badRequest().body(response);
        }

        sampleDataService.loadSampleData();
        response.put("success", true);
        response.put("message", "Sample data loaded successfully! 12 sample items have been added to your collection.");
        response.put("hasData", true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getSampleDataStatus() {
        log.info("GET /api/sample-data/status - Checking sample data status");

        Map<String, Object> response = new HashMap<>();
        boolean hasData = sampleDataService.hasSampleData();

        response.put("hasData", hasData);
        response.put("message", hasData ? "Database contains data" : "Database is empty");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Map<String, Object>> clearAllData() {
        log.info("DELETE /api/sample-data/clear - Clearing all data");

        sampleDataService.deleteAllData();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "All data has been deleted");
        response.put("hasData", false);

        return ResponseEntity.ok(response);
    }
}
