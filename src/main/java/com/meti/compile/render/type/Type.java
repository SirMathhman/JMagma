package com.meti.compile.render.type;

import com.meti.compile.render.Renderable;
import com.meti.compile.render.field.Field;

import java.util.function.Function;
import java.util.stream.Stream;

public interface Type extends Renderable {
    default Stream<Field> streamFields() {
        return Stream.empty();
    }

    @Deprecated
    default <T> T transformContent(Function<String, T> transformer) {
        var format = "Instances of '%s' have no content.";
        var message = format.formatted(getClass());
        throw new UnsupportedOperationException(message);
    }

    default Type mapByChildren(Function<Type, Type> mapper) {
        return this;
    }

    String render(String name);

    @Override
    default String render() {
        return render("");
    }

    boolean is(Group group);

    default Type secondary() {
        var format = "Instances of %s don't have secondary types.";
        var message = format.formatted(getClass());
        throw new UnsupportedOperationException(message);
    }

    default Type withSecondary(Type secondary) {
        var format = "Instances of %s don't have secondary types.";
        var message = format.formatted(getClass());
        throw new UnsupportedOperationException(message);
    }

    default String getContent() {
        var format = "Instances of '%s' have no content.";
        var message = format.formatted(getClass());
        throw new UnsupportedOperationException(message);
    }

    enum Group {
        Content,
        Primitive, Implicit, Function, Structure,
    }
}
