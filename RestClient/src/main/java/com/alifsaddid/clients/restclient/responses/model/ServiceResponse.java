package com.alifsaddid.clients.restclient.responses.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public abstract class ServiceResponse implements Serializable {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            System.out.println("Error on serializing");
        }
        return "";
    }

}
