package com.meti.primitive;

import com.meti.Node;

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
