package com.example.customerservice.adapter.out.kafka.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static byte[] getByte(Object obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
