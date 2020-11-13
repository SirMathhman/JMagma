package com.meti.compile;

public class ImplicitType implements Type {
    public static final Type ImplicitType_ = new ImplicitType();

    private ImplicitType() {
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Implicit;
    }
}
