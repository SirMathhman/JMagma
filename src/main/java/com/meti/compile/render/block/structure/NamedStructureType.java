package com.meti.compile.render.block.structure;

import com.meti.compile.render.type.Type;

public class NamedStructureType extends StructureType {
    private NamedStructureType(String structureName) {
        super(structureName);
    }

    public static Type StructureType(String structureName) {
        return new NamedStructureType(structureName);
    }
}
