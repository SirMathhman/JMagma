package com.meti.compile.generics;

import com.meti.compile.Type;

import java.util.Objects;

public class GenericType implements Type {
    private final String value;

    public GenericType(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericType that = (GenericType) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String render(String name) {
        String format = "Cannot render generic types of value '%s' and name '%s'.";
        String message = format.formatted(value, name);
        throw new IllegalStateException(message);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Generic;
    }
}
