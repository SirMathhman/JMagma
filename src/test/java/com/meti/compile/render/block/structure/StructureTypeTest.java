package com.meti.compile.render.block.structure;

import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.structure.NamedStructureType.StructureType;
import static com.meti.compile.render.block.structure.ObjectType.ObjectType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StructureTypeTest {

    @Test
    void equals0() {
        var expected = ObjectType("dummy");
        var actual = StructureType("dummy");
        assertEquals(expected, actual);
    }

    @Test
    void equals1() {
        var expected = StructureType("dummy");
        var actual = ObjectType("dummy");
        assertEquals(expected, actual);
    }
}