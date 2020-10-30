package com.meti;

public class Variable implements Node {
    private final String value;

    public Variable(String value) {
        this.value = value;
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
