package com.meti.compile.render.block.structure;

import org.junit.jupiter.api.Test;

import static com.meti.compile.render.block.structure.NamedStructureType.StructureType;
import static com.meti.compile.render.type.Type.Group.Structure;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NamedStructureTypeTest {

    @Test
    void render() {
        var actual = StructureType("Dummy").render("value");
        assertEquals("struct Dummy value", actual);
    }

    @Test
    void is() {
        assertTrue(StructureType("Dummy").is(Structure));
    }

    @Test
    void hasEmptyChildren() {
        assertTrue(StructureType("Dummy").streamFields().findAny().isEmpty());
    }
}