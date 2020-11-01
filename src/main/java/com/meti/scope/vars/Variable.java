package com.meti.scope.vars;

import com.meti.Node;

import java.util.Objects;

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
