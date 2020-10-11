package com.meti.compile.render.field;

import com.meti.compile.render.type.Type;

import java.util.Objects;
import java.util.function.Function;

public final class InlineField implements Field {
    private final String name;
    private final Type type;

    private InlineField(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public static InlineField Field(String name, Type type) {
        return new InlineField(name, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InlineField that = (InlineField) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String render() {
        return type.render(name);
    }

    @Override
    public Field mapByType(Function<Type, Type> function) {
        return Field(name, function.apply(type));
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
