package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.type.Type;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Stack {
    Stack define(Field field);

    boolean isDefined(String name);

    Field getDefinition(String name);

    Stack enter();

    Stack enterWithIdentity(Field identity);

    Stack defineAll(List<Field> fields);

    Stack exit();

    Optional<Context> getContext();

    interface Context {
        String getName();

        Type getType();

        Collection<Field> getParameters();

        Collection<Field> getDefinitions();
    }
}
