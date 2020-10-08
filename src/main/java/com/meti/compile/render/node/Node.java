package com.meti.compile.render.node;

import com.meti.compile.render.Renderable;
import com.meti.compile.render.field.Field;

import java.util.function.Function;

public interface Node extends Renderable {
    Node mapByIdentity(Function<Field, Field> mapper);

    Node mapByChildren(Function<Node, Node> mapper);

    Node mapByFields(Function<Field, Field> mapper);

    <T> T transformContent(Function<String, T> function);

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
