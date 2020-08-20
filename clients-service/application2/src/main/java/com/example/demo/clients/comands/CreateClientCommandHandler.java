package com.example.demo.clients.comands;

import com.example.demo.clients.validations.ClientValidator;
import com.example.demo.clients.messages.CreateClientRequest;
import core.CreateRepository;
import core.MockClientRepository;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import main.java.com.example.demo.core.Client;
 import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateClientCommandHandler {

    CreateRepository<Client> clients = new MockClientRepository();

    public static Function<CreateClientRequest, Client> toClient =
            createClientRequest -> new Client(
                    createClientRequest.getName(),
                    createClientRequest.getEmployeeId()
            );


    @RabbitListener(queues = "CommandClientQueue")
    public Either<Seq<String>, Integer> handle(CreateClientRequest createClientRequest) {

        return ClientValidator
                .validate(createClientRequest)
                .toEither()
                .map(toClient)
                .flatMap(clients::add);

    }
}
