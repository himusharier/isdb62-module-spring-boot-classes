package com.himusharier.jasperReport.controller;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JasperReportController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/employees")
    public void generateEmployeeReport(HttpServletResponse response) throws Exception {
        InputStream reportStream = getClass().getResourceAsStream("/reports/employees_sb_jdbc.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        try (Connection conn = dataSource.getConnection()) {
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employee_report.pdf");

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        }
    }
}
