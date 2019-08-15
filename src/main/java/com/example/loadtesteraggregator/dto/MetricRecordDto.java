package com.example.loadtesteraggregator.dto;

public class MetricRecordDto {

    private Double endTimeMillis;
    private Long responseTime;
    private Long latency;
    private Long sentBytes;
    private Long receivedBytes;
    private Boolean isSuccessful;
    private Integer responseCode;

    public MetricRecordDto(){}

    public MetricRecordDto(MetricRecordDtoBuilder builder){
        this.endTimeMillis = builder.endTimeMillis;
        this.responseTime = builder.responseTime;
        this.latency = builder.latency;
        this.sentBytes = builder.sentBytes;
        this.receivedBytes = builder.receivedBytes;
        this.isSuccessful = builder.isSuccessful;
        this.responseCode = builder.responseCode;
    }

    public Double getEndTimeMillis() {
        return endTimeMillis;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public Long getLatency() {
        return latency;
    }

    public Long getSentBytes() {
        return sentBytes;
    }

    public Long getReceivedBytes() {
        return receivedBytes;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public static class MetricRecordDtoBuilder{
        private Double endTimeMillis;
        private Long responseTime;
        private Long latency;
        private Long sentBytes;
        private Long receivedBytes;
        private Boolean isSuccessful;
        private Integer responseCode;

        public MetricRecordDtoBuilder(){}

        public MetricRecordDtoBuilder withEndTimeMillis(Double endTimeMillis) {
            this.endTimeMillis = endTimeMillis;
            return this;
        }

        public MetricRecordDtoBuilder withResponseTime(Long responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public MetricRecordDtoBuilder withLatency(Long latency) {
            this.latency = latency;
            return this;
        }

        public MetricRecordDtoBuilder withSentBytes(Long sentBytes) {
            this.sentBytes = sentBytes;
            return this;
        }

        public MetricRecordDtoBuilder withReceivedBytes(Long receivedBytes) {
            this.receivedBytes = receivedBytes;
            return this;
        }

        public MetricRecordDtoBuilder withSuccessful(Boolean successful) {
            isSuccessful = successful;
            return this;
        }

        public MetricRecordDtoBuilder withResponseCode(Integer responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public MetricRecordDto build() {
            return new MetricRecordDto(this);
        }
    }
}
