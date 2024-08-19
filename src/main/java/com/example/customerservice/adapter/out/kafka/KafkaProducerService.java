package com.example.customerservice.adapter.out.kafka;

import com.example.customerservice.adapter.out.kafka.config.TopicConfig;
import com.example.customerservice.adapter.out.kafka.serde.ObjectSerializer;
import com.example.customerservice.core.model.Address;
import com.example.customerservice.core.model.Bank;
import com.example.customerservice.core.model.Customer;
import com.example.customerservice.core.port.output.IAddressEventOutputPort;
import com.example.customerservice.core.port.output.IBankEventOutputPort;
import com.example.customerservice.core.port.output.ICustomerEventOutputPort;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducerService implements ICustomerEventOutputPort, IAddressEventOutputPort, IBankEventOutputPort {

    private final KafkaTemplate<String, CloudEvent> kafkaTemplate;
    private final TopicConfig topicConfig;

    public void sendCustomerCreatedEvent(Customer customer) {
        sendMessage(topicConfig.getCustomerCreated(), customer);
    }

    public void sendCustomerUpdatedEvent(Customer customer) {
        sendMessage(topicConfig.getCustomerUpdated(), customer);
    }

    public void sendCustomerDeletedEvent(Customer customer) {
        sendMessage(topicConfig.getCustomerDeleted(), customer);
    }

    public void sendAddressCreatedEvent(Address address) {
        sendMessage(topicConfig.getAddressCreated(), address);
    }

    public void sendAddressUpdatedEvent(Address address) {
        sendMessage(topicConfig.getAddressUpdated(), address);
    }

    public void sendAddressDeletedEvent(Address address) {
        sendMessage(topicConfig.getAddressDeleted(), address);
    }

    public void sendBankCreatedEvent(Bank bank) {
        sendMessage(topicConfig.getBankCreated(), bank);
    }

    public void sendBankUpdatedEvent(Bank bank) {
        sendMessage(topicConfig.getBankUpdated(), bank);
    }

    public void sendBankDeletedEvent(Bank bank) {
        sendMessage(topicConfig.getBankDeleted(), bank);
    }



    private void sendMessage(String topic, Object obj) {
        CloudEvent event = CloudEventBuilder.v1()
                .withId(UUID.randomUUID().toString())
                .withSource(URI.create("http://localhost:8080"))
                .withType("com.example.customer.created")
                .withTime(OffsetDateTime.now())
                .withDataContentType("application/json; charset=UTF-8")
                .withData(ObjectSerializer.getByte(obj))
                .build();

        kafkaTemplate.send(topic, event)
                .thenRun(() -> log.info("Message sent. Id: {}; Data: {}", event.getId(), obj));
    }
}