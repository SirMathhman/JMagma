package com.meti.compile.render.node;

import com.meti.compile.render.Renderable;
import com.meti.compile.render.field.Field;

import java.util.function.Function;
import java.util.stream.Stream;

public interface Node extends Renderable {
    default Stream<? extends Node> streamChildren() {
        return Stream.empty();
    }

    default Node mapByIdentity(Function<Field, Field> mapper) {
        return this;
    }

    default Node mapByChildren(Function<Node, Node> mapper) {
        return this;
    }

    default Node mapByFields(Function<Field, Field> mapper) {
        return this;
    }

    default String content() {
        var format = "Instances of %s have no content.";
        var message = format.formatted(getClass());
        throw new IllegalStateException(message);
    }

    boolean is(Group group);

    default Field identity() {
        var format = "Instances of %s don't have identities.";
        var message = format.formatted(getClass());
        throw new IllegalStateException(message);
    }

    default <T> T value(Class<T> clazz) {
        var format = "Instances of %s dont' have values.";
        var message = format.formatted(getClass());
        throw new IllegalStateException(message);
    }

    default Node withIdentity(Field identity) {
        return this;
    }

    default Node withValue(Object value) {
        return this;
    }

    enum Group {
        Content,
        Declaration,
        IntNumber,
        Initialization, Block, Function, Return, Variable
    }
}
