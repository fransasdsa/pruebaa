package com.upu.msreportingservice.controller;

import com.upu.msreportingservice.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{reportName}")
    public ResponseEntity<byte[]> getReport(@PathVariable String reportName, @RequestParam Map<String, Object> params) {
        try {
            // Simular obtención de datos desde otros microservicios
            List<Object> dataSource = fetchDataForReport(reportName, params);

            byte[] report = reportService.generateReport(reportName, params, dataSource);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(reportName + ".pdf").build());

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (JRException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Object> fetchDataForReport(String reportName, Map<String, Object> params) {
        // Implementar lógica para obtener datos de otros microservicios
        // Por ejemplo, utilizar WebClient o RestTemplate
        return new ArrayList<>();
    }
}
