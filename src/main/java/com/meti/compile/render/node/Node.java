package com.meti.compile.render.node;

import com.meti.compile.render.Renderable;
import com.meti.compile.render.field.Field;

import java.util.function.Function;

public interface Node extends Renderable {
    Node mapByFields(Function<Field, Field> mapper);

    <T> T transformContent(Function<String, T> function);

    boolean is(Group group);

    enum Group {
        Content,
        Declaration,
        IntNumber,
        Variable
    }
}
