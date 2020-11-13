package com.meti.compile.primitive;

import com.meti.compile.Node;

public class False implements Node {
    public static final Node false_ = new False();

    private False() {
    }

    @Override
    public boolean is(Group group) {
        return group == Group.False;
    }

    @Override
    public String render() {
        return "0";
    }
}
