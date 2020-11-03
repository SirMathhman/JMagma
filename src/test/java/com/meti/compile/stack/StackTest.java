package com.meti.compile.stack;

import com.meti.compile.primitive.Primitive;
import com.meti.compile.scope.field.Field;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

import static com.meti.compile.primitive.Primitive.I16;
import static com.meti.compile.primitive.Primitive.U8;
import static com.meti.compile.scope.field.Field.Field;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void exit() {
        Stack stack = new Stack()
                .enter()
                .define(createField("test", I16))
                .exit();
        assertFalse(stack.isDefined("test"));
    }

    @Test
    void enter() {
        Stack stack = new Stack()
                .enter()
                .define(createField("test", I16));
        assertTrue(stack.isDefined("test"));
    }

    @Test
    void defineAll() {
        Stack stack = new Stack().defineAll(Set.of(createField("first", U8), createField("second", U8)));
        assertTrue(stack.isDefined("first"));
        assertTrue(stack.isDefined("second"));
    }

    @Test
    void define() {
        assertTrue(new Stack().define(createField("value", Primitive.I16)).isDefined("value"));
    }

    @Test
    void isDefined() {
        Field field = createField("value", Primitive.I16);
        Stack.Frame frame = new Stack.Frame(Collections.singleton(field));
        Stack stack = new Stack(new LinkedList<>(Collections.singletonList(frame)));
        assertTrue(stack.isDefined("value"));
    }

    private Field createField(String name, Primitive value) {
        return Field()
                .withName(name)
                .withType(value)
                .complete();
    }
}