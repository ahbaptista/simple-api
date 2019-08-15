package com.example.loadtesteraggregator.controller;

import com.example.loadtesteraggregator.service.MetricsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MetricsController {

    private MetricsService metricsService;

    public MetricsController(MetricsService metricsService){
        this.metricsService = metricsService;
    }

    @PostMapping("/load-tests")
    public String loadTestsMetrics(@RequestParam("file") MultipartFile file) throws IOException {
        metricsService.recordResults(file.getInputStream());
        return "Metrics pushed";
    }
}

