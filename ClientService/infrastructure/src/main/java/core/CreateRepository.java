package core;

import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface   CreateRepository<T> {
      Either<Seq<String>, Integer> add(T item);
}
