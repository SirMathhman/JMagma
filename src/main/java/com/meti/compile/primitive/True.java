package com.meti.compile.primitive;

import com.meti.compile.Node;

public class True implements Node {
    public static final Node true_ = new True();

    private True() {
    }

    @Override
    public boolean is(Group group) {
        return group == Group.True;
    }

    @Override
    public String render() {
        return "1";
    }
}
