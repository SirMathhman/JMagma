package com.meti.compile.primitive;

import com.meti.compile.Type;

public class Void implements Type {
    public static final Type Void_ = new Void();

    @Override
    public boolean is(Group group) {
        return group == Group.Void;
    }
}
