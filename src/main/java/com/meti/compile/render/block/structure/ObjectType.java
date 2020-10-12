package com.meti.compile.render.block.structure;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.type.Type;

import java.util.List;
import java.util.stream.Stream;

public class ObjectType extends StructureType {
    private final List<Field> fields;

    private ObjectType(String structureName, List<Field> fields) {
        super(structureName);
        this.fields = fields;
    }

    public static Type StructureType(String structureName, Field... fields) {
        return StructureType(structureName, List.of(fields));
    }

    public static Type StructureType(String structureName, List<Field> fields) {
        return new ObjectType(structureName, fields);
    }

    @Override
    public Stream<Field> streamFields() {
        return fields.stream();
    }
}
