package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;

import java.util.List;

public interface Stack {
    Stack define(Field field);

    boolean isDefined(String name);

    Field getDefinition(String name);

    Stack enter();

    Stack enterWithIdentity(Field identity);

    Stack defineAll(List<Field> fields);

    Stack exit();
}
