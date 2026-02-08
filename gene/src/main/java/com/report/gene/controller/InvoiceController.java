package com.report.gene.controller;

import com.report.gene.model.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.*;

@RestController
public class InvoiceController {

    @GetMapping("/invoice")
    public ResponseEntity<byte[]> generateInvoice() throws Exception {

        // 1. Create dummy data
        List<Item> items = Arrays.asList(
                new Item("Pen", 2, 10.0),
                new Item("Book", 1, 50.0),
                new Item("Bag", 1, 500.0)
        );

        // 2. Load JRXML file
        InputStream reportStream =
                new ClassPathResource("reports/invoice.jrxml").getInputStream();

        JasperReport jasperReport =
                JasperCompileManager.compileReport(reportStream);

        // 3. Parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("InvoiceNo", "1001");
        parameters.put("Date", "08-02-2026");
        parameters.put("CustomerName", "Sairam");

        // 4. Data Source
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(items);

        // 5. Fill Report
        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 6. Export to PDF
        byte[] pdfBytes =
                JasperExportManager.exportReportToPdf(jasperPrint);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
