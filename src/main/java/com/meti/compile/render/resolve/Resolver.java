package com.meti.compile.render.resolve;

import com.meti.compile.render.type.Type;

import java.util.Optional;

public interface Resolver {
    Optional<Type> resolve();
}
