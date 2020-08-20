package main.java.com.example.demo.core;

public class Client {
    public Integer id;
    public String name;
    private Integer employeeId;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Client(
                  String name,
                  Integer employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }
    public Client(Integer id,
                  String name,
                  Integer employeeId) {
        this.id = id;
        this.name = name;
        this.employeeId = employeeId;
    }


}
