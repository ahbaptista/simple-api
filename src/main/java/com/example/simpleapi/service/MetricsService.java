package com.example.simpleapi.service;

import com.example.simpleapi.dto.MetricRecordDto;
import com.newrelic.api.agent.NewRelic;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricsService {

    private static final String CUSTOM_CATEGORY = "Custom/api-load-test";
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsService.class);

    public void recordResults(InputStream inputStream) {
        InputStreamReader reader = new InputStreamReader(inputStream, Charset.defaultCharset());
        List<MetricRecordDto> resultRecords = getMetricRecordDtoList(reader);
        recordNewRelicMetrics(resultRecords);
    }

    private List<MetricRecordDto> getMetricRecordDtoList(InputStreamReader reader){
        List<String[]> rawList = getRawList(reader);
        return rawList.stream()
                .map(this::rawToMetricRecordDto)
                .collect(Collectors.toList());
    }

    private List<String[]> getRawList(InputStreamReader reader) {
        CSVReader csvReader = new CSVReader(reader);
        try {
            List<String[]> rawResult =  csvReader.readAll();
            //header
            rawResult.remove(0);
            return rawResult;
        } catch (IOException e) {
            LOGGER.error("Error parsing csv file", e);
            return Collections.EMPTY_LIST;
        }
    }

    public MetricRecordDto rawToMetricRecordDto(String[] raw) {
        return new MetricRecordDto.MetricRecordDtoBuilder()
                .withEndTimeMillis(Double.valueOf(raw[0]))
                .withResponseTime(Long.valueOf(raw[1]))
                .withLatency(Long.valueOf(raw[2]))
                .withSentBytes(Long.valueOf(raw[3]))
                .withReceivedBytes(Long.valueOf(raw[4]))
                .withSuccessful(Boolean.valueOf(raw[5]))
                .withResponseCode(Integer.valueOf(raw[6]))
                .build();
    }

    public void recordNewRelicMetrics(List<MetricRecordDto> resultRecords) {
        LOGGER.info("New relic app name: " + System.getenv("NEW_RELIC_APP_NAME"));
        LOGGER.info("New relic license key: " + System.getenv("NEW_RELIC_LICENSE_KEY"));
        recordResponseTime(resultRecords);
    }

    private void recordResponseTime(List<MetricRecordDto> resultRecords){

        List<Long> responseTimes = resultRecords.stream()
                .map(MetricRecordDto::getResponseTime)
                .collect(Collectors.toList());
            NewRelic.getAgent().getConfig();
            NewRelic.recordMetric(CUSTOM_CATEGORY+"/responseTime",
                    (float)responseTimes.stream()
                            .mapToLong(m->m)
                            .average()
                            .orElse(0));
    }
}
