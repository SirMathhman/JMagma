package com.meti.compile.render.block.structure;

import com.meti.compile.render.type.Type;

import java.util.Objects;

import static com.meti.compile.render.type.Type.Group.Structure;

public abstract class StructureType implements Type {
    protected final String structureName;

    public StructureType(String structureName) {
        this.structureName = structureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !StructureType.class.isAssignableFrom(o.getClass())) return false;
        StructureType that = (StructureType) o;
        return Objects.equals(structureName, that.structureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureName);
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
