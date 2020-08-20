package com.example.demo.clients.queries;

import core.MockClientRepository;
import core.MockEmployeeRepository;
import core.Repository;
import io.vavr.control.Either;
import main.java.com.example.demo.core.Client;
import main.java.com.example.demo.core.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.demo.EitherExtensions.*;
import static io.vavr.API.Try;

@Component
public class GetClientCommandHandler {

    Repository<Client> clients = new MockClientRepository();
    Repository<Employee> employees = new MockEmployeeRepository();

    @RabbitListener(queues = "QueryClientQueue")
    public Either<String, String> handle(Integer clientId) {

   var searched = clients
                .getById(clientId)
                .thenApplyAsync(mapT(Client::getEmployeeId))
                .thenComposeAsync(bindT(employees::getById));

        return Try(searched::get)
                .map(mapT(Employee::getName))
                .getOrElseGet(throwableMessage());

    }
}
