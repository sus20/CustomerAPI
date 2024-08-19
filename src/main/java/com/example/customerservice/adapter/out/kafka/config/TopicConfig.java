package com.example.customerservice.adapter.out.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "topic")
public class TopicConfig{

    private String customerCreated;
    private String customerUpdated;
    private String customerDeleted;

    private String addressCreated;
    private String addressUpdated;
    private String addressDeleted;

    private String bankCreated;
    private String bankUpdated;
    private String bankDeleted;
}
