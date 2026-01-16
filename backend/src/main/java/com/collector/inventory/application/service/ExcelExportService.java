package com.collector.inventory.application.service;

import com.collector.inventory.application.dto.InventoryItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service for exporting inventory data to Excel format.
 * Creates professional, well-formatted Excel reports.
 */
@Service
@Slf4j
public class ExcelExportService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Exports inventory items to Excel format.
     */
    public byte[] exportToExcel(List<InventoryItemDTO> items) throws IOException {
        log.info("Starting Excel export for {} items", items.size());

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Inventory");

            // Create styles
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle currencyStyle = createCurrencyStyle(workbook);
            CellStyle dateStyle = createDateStyle(workbook);

            // Create header row
            createHeaderRow(sheet, headerStyle);

            // Populate data rows
            int rowNum = 1;
            for (InventoryItemDTO item : items) {
                createDataRow(sheet, rowNum++, item, currencyStyle, dateStyle);
            }

            // Auto-size columns
            for (int i = 0; i < 24; i++) {
                sheet.autoSizeColumn(i);
            }

            // Freeze header row
            sheet.createFreezePane(0, 1);

            workbook.write(outputStream);
            log.info("Excel export completed successfully");
            return outputStream.toByteArray();
        }
    }

    private void createHeaderRow(Sheet sheet, CellStyle headerStyle) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {
            "ID", "Name", "Description", "Quantity", "Category",
            "Estimated Value", "Purchase Price", "Purchase Date",
            "Manufacturer", "Part Number", "Serial Number", "Model",
            "Year of Manufacture", "Condition", "Storage Location",
            "Provenance", "Composition/Materials",
            "Certification Number", "Has Certificate", "Documentation Notes",
            "Created At", "Updated At", "Notes"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private void createDataRow(Sheet sheet, int rowNum, InventoryItemDTO item,
                               CellStyle currencyStyle, CellStyle dateStyle) {
        Row row = sheet.createRow(rowNum);

        int colNum = 0;
        createCell(row, colNum++, item.getId());
        createCell(row, colNum++, item.getName());
        createCell(row, colNum++, item.getDescription());
        createCell(row, colNum++, item.getQuantity());
        createCell(row, colNum++, item.getCategory() != null ? item.getCategory().getDisplayName() : "");

        createCurrencyCell(row, colNum++, item.getEstimatedValue(), currencyStyle);
        createCurrencyCell(row, colNum++, item.getPurchasePrice(), currencyStyle);
        createDateCell(row, colNum++, item.getPurchaseDate() != null ? item.getPurchaseDate().format(DATE_FORMATTER) : "", dateStyle);

        createCell(row, colNum++, item.getManufacturer());
        createCell(row, colNum++, item.getPartNumber());
        createCell(row, colNum++, item.getSerialNumber());
        createCell(row, colNum++, item.getModel());
        createCell(row, colNum++, item.getYearOfManufacture());
        createCell(row, colNum++, item.getCondition() != null ? item.getCondition().getDescription() : "");
        createCell(row, colNum++, item.getStorageLocation());

        createCell(row, colNum++, item.getProvenance());
        createCell(row, colNum++, item.getCompositionMaterials());

        createCell(row, colNum++, item.getCertificationNumber());
        createCell(row, colNum++, item.getHasCertificate() != null ? (item.getHasCertificate() ? "Yes" : "No") : "");
        createCell(row, colNum++, item.getDocumentationNotes());

        createDateCell(row, colNum++, item.getCreatedAt() != null ? item.getCreatedAt().format(DATETIME_FORMATTER) : "", dateStyle);
        createDateCell(row, colNum++, item.getUpdatedAt() != null ? item.getUpdatedAt().format(DATETIME_FORMATTER) : "", dateStyle);
        createCell(row, colNum++, item.getNotes());
    }

    private void createCell(Row row, int column, Object value) {
        Cell cell = row.createCell(column);
        if (value != null) {
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Long) {
                cell.setCellValue((Long) value);
            } else {
                cell.setCellValue(value.toString());
            }
        }
    }

    private void createCurrencyCell(Row row, int column, Object value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value != null) {
            cell.setCellValue(Double.parseDouble(value.toString()));
            cell.setCellStyle(style);
        }
    }

    private void createDateCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value != null && !value.isEmpty()) {
            cell.setCellValue(value);
            cell.setCellStyle(style);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createCurrencyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("$#,##0.00"));
        return style;
    }

    private CellStyle createDateStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        return style;
    }
}
