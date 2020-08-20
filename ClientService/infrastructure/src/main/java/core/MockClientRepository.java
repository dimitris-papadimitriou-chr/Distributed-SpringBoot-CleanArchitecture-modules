package core;

import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import main.java.com.example.demo.core.Client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class MockClientRepository implements Repository<Client>, CreateRepository<Client> {
    private Client[] clients;

    public MockClientRepository() {
        this.clients = new Client[]{
                new Client(1, "jim", 1),
                new Client(2, "john", 3)};
    }

    @Override
    public CompletableFuture<Either<String, Client>> getById(int id) {

        CompletableFuture<Either<String, Client>> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(1000);
            var clientOptional = Stream.of(clients)
                    .filter(client -> id == client.getId())
                    .findAny();
            completableFuture.complete(Option
                    .ofOptional(clientOptional)
                    .toEither("no client found"));
            return null;
        });

        return completableFuture;
    }


    @Override
    public Either<Seq<String>, Integer> add(Client item) {

        return Either.right(5);
    }
}
