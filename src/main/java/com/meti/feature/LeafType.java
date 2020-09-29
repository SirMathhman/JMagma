package com.meti.feature;

import java.util.function.Function;

public interface LeafType extends Type {
    @Override
    default Type mapByChildren(Function<Type, Type> mapping) {
        return this;
    }

    @Override
    default Type mapByFields(Function<Type, Type> fields) {
        return this;
    }
}
