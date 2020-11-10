package com.meti.compile.generics;

import com.meti.compile.Type;

class GenericType implements Type {
    @Override
    public String render(String name) {
        return "void* %s".formatted(name);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Generic;
    }
}
