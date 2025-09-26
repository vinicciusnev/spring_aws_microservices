package com.viniccius.aws.spring_aws_microservices.model;

import com.viniccius.aws.spring_aws_microservices.enums.EventType;
import lombok.Data;

@Data
public class Envelope {
    private EventType eventType;
    private String data;
}
