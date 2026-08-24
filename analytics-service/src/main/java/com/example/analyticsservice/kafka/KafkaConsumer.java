package com.example.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service  // putting this annotation so that it's managed by spring
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumedEvent(byte[] event) { // from the patient-service when we're
        // sending kafkaEvent throught the producer, we're sending in the form of
        // byteArray & here we're accepting the argument in the form of byte array only.

        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // ... perform any business logic related to analytics here
            log.info("Received Patient Event: [PatientId = {}, PatientName = {}, PatientEmail = {}] ",
                    patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }


    }
}
