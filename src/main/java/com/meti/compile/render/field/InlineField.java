package com.meti.compile.render.field;

import com.meti.compile.render.type.Type;

import java.util.function.Function;

public final class InlineField implements Field {
    private final String name;
    private final Type type;

    public InlineField(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String render() {
        return type.render(name);
    }

    @Override
    public Field mapByType(Function<Type, Type> function) {
        return new InlineField(name, function.apply(type));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Type type() {
        return type;
    }
}
