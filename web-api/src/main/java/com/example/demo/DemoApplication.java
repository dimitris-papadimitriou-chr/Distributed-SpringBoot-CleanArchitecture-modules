package com.example.demo;

import com.example.demo.clients.messages.CreateClientRequest;
import com.example.demo.clients.messages.CreateClientResponse;
import com.example.demo.viewmodels.SearchViewModel;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping(path = "/clients")
@EnableRabbit
public class DemoApplication {

    private static final boolean NON_DURABLE = false;
    private static final String QueryClientQueueName = "QueryClientQueue";
    private static final String CommandClientQueueName = "CommandClientQueue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue QueryClientQueue() {
        return new Queue(QueryClientQueueName, NON_DURABLE);
    }
    @Bean
    public Queue CommandClientQueue() {
        return new Queue(CommandClientQueueName, NON_DURABLE);
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/get/{clientId}")
    public ResponseEntity<SearchViewModel> getClient(@PathVariable Integer clientId) {

        var response = ((Either<String, String>)
                rabbitTemplate.convertSendAndReceive(QueryClientQueueName, clientId));

        return response.fold(
                error -> new ResponseEntity<>(new SearchViewModel(error), HttpStatus.BAD_REQUEST),
                name -> new ResponseEntity<>(new SearchViewModel(name), HttpStatus.OK)
        );
    }


    @PostMapping("/create")
    public ResponseEntity<CreateClientResponse> create(@RequestBody CreateClientRequest clientRequest) {

        var response = ((Either<Seq<String>, Integer> )
                rabbitTemplate.convertSendAndReceive(CommandClientQueueName, clientRequest));

        return response.fold(
                errors -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST),
                result -> new ResponseEntity<>(new CreateClientResponse(result), HttpStatus.OK)
        );
    }


}