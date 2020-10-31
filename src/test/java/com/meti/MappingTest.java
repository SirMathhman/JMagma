package com.meti;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MappingTest {
    private Node createMapping() {
        Node caller = new Variable("caller");
        Node argument0 = new Variable("arg0");
        Node argument1 = new Variable("arg1");
        return new Mapping(caller, List.of(argument0, argument1));
    }

    @Test
    void is() {
        assertTrue(createMapping().is(Node.Group.Mapping));
    }

    @Test
    void render() {
        assertEquals("caller(arg0,arg1)", createMapping().render());
    }
}