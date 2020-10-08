package com.meti.compile.render.node;

import com.meti.compile.render.Renderable;
import com.meti.compile.render.field.Field;

import java.util.function.Function;
import java.util.stream.Stream;

public interface Node extends Renderable {
    default Stream<Node> streamChildren() {
        return Stream.empty();
    }

    Node mapByIdentity(Function<Field, Field> mapper);

    Node mapByChildren(Function<Node, Node> mapper);

    Node mapByFields(Function<Field, Field> mapper);

    @Deprecated
    <T> T transformContent(Function<String, T> function);

    default String content() {
        var format = "Instances of %s have no content.";
        var message = format.formatted(getClass());
        throw new IllegalStateException(message);
    }

    boolean is(Group group);

    Field identity();

    <T> T value(Class<T> clazz);

    Node withIdentity(Field identity);

    Node withValue(Object value);

    enum Group {
        Content,
        Declaration,
        IntNumber,
        Initialization, Block, Function, Return, Variable
    }
}
