package com.example.demo.viewmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SearchViewModel {

    private String type;
    private String result;

    public SearchViewModel(String result) {
        this.result=result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String value) {
        this.result = value;
    }

    @Override
    public String toString() {
        return "Search{" +
                "type='" + type + '\'' +
                ", result=" + result +
                '}';
    }
}
