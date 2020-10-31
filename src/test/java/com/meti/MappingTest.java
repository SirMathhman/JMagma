package com.meti;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.Mapping.Mapping;
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
    void builder() {
        Node expected = createMapping();
        Node actual = Mapping()
                .withCaller(new Variable("caller"))
                .withArgument(new Variable("arg0"))
                .withArgument(new Variable("arg1"))
                .complete();
        assertEquals(expected, actual);
    }

    @Test
    void testEquals(){
        Node expected = createMapping();
        Node actual = createMapping();
        assertEquals(expected, actual);
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