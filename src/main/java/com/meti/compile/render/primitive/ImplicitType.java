package com.meti.compile.render.primitive;

import com.meti.compile.render.node.UnrenderableException;
import com.meti.compile.render.type.Type;

public class ImplicitType implements EmptyType {
    public static final Type ImplicitType = new ImplicitType();

    private ImplicitType() {
    }

    @Override
    public String render(String name) {
        var format = """
                Unable to render implicit types with name '%s', 
                this instance should have been removed in the compilation process 
                and is probably the result of a programming error.
                """;
        var message = String.format(format, name);
        throw new UnrenderableException(message);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Implicit;
    }
}
