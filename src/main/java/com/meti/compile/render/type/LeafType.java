package com.meti.compile.render.type;

import java.util.function.Function;

public interface LeafType extends Type {
    @Override
    default Type head(){
        var format = "Instances of %s have no heads.";
        var message = format.formatted(getClass());
        throw new IllegalStateException(message);
    }

    @Override
    default Type mapByChildren(Function<Type, Type> mapper) {
        return this;
    }
}
