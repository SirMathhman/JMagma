package com.meti.compile.render.process;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.type.Type;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CallStack {
    CallStack define(Field field);

    boolean isDefined(String name);

    Field getDefinition(String name);

    CallStack enter();

    CallStack enterWithIdentity(Field identity);

    CallStack defineAll(List<Field> fields);

    CallStack exit();

    Optional<Context> getContext();

    interface Context {
        String getName();

        Type getType();

        Collection<Field> getParameters();

        Collection<Field> getDefinitions();
    }
}
