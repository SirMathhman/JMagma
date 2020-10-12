package com.meti.compile.render.block.structure;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.render.block.structure.ObjectType.StructureType;
import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.primitive.Primitive.I16;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ObjectTypeTest {

    @Test
    void streamFields() {
        var x = Field("x", I16);
        var y = Field("y", I16);
        var type = StructureType("Point", x, y);
        assertIterableEquals(List.of(x, y), type.streamFields().collect(Collectors.toList()));
    }
}