package com.meti.compile.render.type;

import com.meti.compile.render.Renderable;

import java.util.function.Function;

public interface Type extends Renderable {
    <T> T transformContent(Function<String, T> transformer);

    Type mapByChildren(Function<Type, Type> mapper);

    String render(String name);

    @Override
    default String render() {
        return render("");
    }

    boolean is(Group group);

    Type secondary();

    default Type withSecondary(Type secondary) {
        var format = "Instances of %s don't have secondary types.";
        var message = format.formatted(getClass());
        throw new UnsupportedOperationException(message);
    }

    enum Group {
        Content,
        Primitive, Implicit, Function,
    }
}
