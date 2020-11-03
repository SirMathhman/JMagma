package com.meti.compile;

public class EmptyNode implements Node {
    public static final Node Empty = new EmptyNode();

    @Override
    public boolean is(Group group) {
        return false;
    }

    @Override
    public String render() {
        return "";
    }
}
