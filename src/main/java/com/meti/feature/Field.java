package com.meti.feature;

import java.util.function.Function;

public interface Field extends Renderable {
    Field mapByType(Function<Type, Type> mapping);
}
