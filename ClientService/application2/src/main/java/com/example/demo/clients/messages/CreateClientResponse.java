package com.example.demo.clients.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateClientResponse implements Serializable {

    public CreateClientResponse(Integer id) {
        this.id = id;
    }

    private Integer id;
    public Integer getId() {
        return id;
    }
    @Override
    public String toString() {
        return "CreateClientResponse{" +
                "id=" + id +
                '}';
    }



}

