package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;

public interface Stack {
    Stack define(Field field);

    boolean isDefined(String name);

    Field getDefinition(String name);
}
