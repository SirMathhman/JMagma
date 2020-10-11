package com.meti.compile.render.type;

import java.util.function.Function;

public interface LeafType extends Type {
    @Override
    default Type secondary() {
        var format = "Instances of %s have no children.";
        var message = format.formatted(getClass());
        throw new IllegalStateException(message);
    }

    @Override
    default Type mapByStart(Function<Type, Type> mapper) {
        return this;
    }

    @Override
    default Type mapByChildren(Function<Type, Type> mapper) {
        return this;
    }
}
