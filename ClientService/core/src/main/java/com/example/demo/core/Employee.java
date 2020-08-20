package main.java.com.example.demo.core;

public class Employee {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
