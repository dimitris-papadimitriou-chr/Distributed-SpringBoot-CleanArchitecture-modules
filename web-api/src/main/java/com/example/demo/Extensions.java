package com.example.demo;


import com.example.demo.clients.messages.CreateClientResponse;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Extensions {

/*    public static <T,R> ResponseEntity<T> toResponseEntity(Either<Seq<String>, T> either) {
        return either.fold(
                errors -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST),
                result -> new ResponseEntity<>(new CreateClientResponse(result), HttpStatus.OK)
        );
    }*/


}
