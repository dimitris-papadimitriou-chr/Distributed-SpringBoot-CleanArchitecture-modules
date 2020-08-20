package core;

import io.vavr.control.Either;
import io.vavr.control.Option;
import main.java.com.example.demo.core.Employee;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class MockEmployeeRepository implements Repository<Employee> {

    private Employee[] employees;

    public MockEmployeeRepository() {
        this.employees = new Employee[]{
                new Employee(1, "jane"),
                new Employee(2, "jack")};
    }

    @Override
    public CompletableFuture<Either<String, Employee>> getById(int id) {

        var t = Stream.of(employees)
                .filter(employee -> employee.getId() == id)
                .findAny();

        CompletableFuture<Either<String, Employee>> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete(Option.ofOptional(t).toEither("no employee found"));
            return null;
        });

        return completableFuture;
    }

}
