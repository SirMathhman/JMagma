package com.meti.compile.scope.vars;

import com.meti.compile.Node;

import java.util.Objects;
import java.util.function.Function;

public class Variable implements Node {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(value, variable.value);
    }

    @Override
    public <T, R> R mapValue(Class<T> clazz, Function<T, R> function) {
        return function.apply(clazz.cast(value));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Variable;
    }

    @Override
    public String render() {
        return value;
    }
}
