package com.viniccius.aws.productservice.model;

import com.viniccius.aws.productservice.enums.EventType;
import lombok.Data;

@Data
public class Envelope {
    private EventType eventType;
    private String data;
}
