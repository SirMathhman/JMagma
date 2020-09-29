package com.meti.render.evaluate.type;

import com.meti.render.Renderable;

import java.util.Optional;
import java.util.function.Function;

public interface Type extends Renderable {
    <R> Optional<R> transformContent(Function<String, R> mapping);

    Type mapByChildren(Function<Type, Type> mapping);

    Type mapByFields(Function<Type, Type> fields);

    @Override
    default String render() {
        return render("");
    }

    String render(String name);
}
