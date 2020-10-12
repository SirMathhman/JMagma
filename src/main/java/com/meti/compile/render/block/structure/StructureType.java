package com.meti.compile.render.block.structure;

import com.meti.compile.render.type.Type;

import static com.meti.compile.render.type.Type.Group.Structure;

public abstract class StructureType implements Type {
    protected final String structureName;

    public StructureType(String structureName) {
        this.structureName = structureName;
    }

    @Override
    public String getContent() {
        return structureName;
    }

    @Override
    public String render(String name) {
        var format = "struct %s %s";
        return format.formatted(structureName, name);
    }

    @Override
    public boolean is(Group group) {
        return group == Structure;
    }
}
