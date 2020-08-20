package com.example.demo.clients.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateClientRequest implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    private String name;
    private Integer employeeId;

    public CreateClientRequest() {

    }

    public CreateClientRequest(String name, Integer employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "CreateClientRequest{" +
                "name='" + name + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
