package com.meti.compile.resolve;

import com.meti.compile.Node;
import com.meti.compile.Type;

import java.util.Optional;
import java.util.function.Function;

public interface Resolver<T> {
    Optional<Type> resolve(Function<T, Resolver<T>> parent);
}
