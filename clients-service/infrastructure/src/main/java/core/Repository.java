package core;

import io.vavr.control.Either;

import java.util.concurrent.CompletableFuture;

public interface Repository<T> {
    CompletableFuture<Either<String, T>> getById(int id);
}
