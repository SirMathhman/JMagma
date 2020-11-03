package com.meti.compile.primitive;

import com.meti.compile.Type;
import org.junit.jupiter.api.Test;

import static com.meti.compile.primitive.Void.Void_;
import static org.junit.jupiter.api.Assertions.*;

class VoidTokenizerTest {

    @Test
    void tokenizeValid() {
        assertEquals(Void_, new VoidTokenizer("Void").tokenize().orElseThrow());
    }

    @Test
    void tokenizeInvalid(){
        assertTrue(new VoidTokenizer("I16").tokenize().isEmpty());
    }

    @Test
    void render() {
        assertEquals("void test", Void_.render("test"));
    }

    @Test
    void is() {
        assertTrue(Void_.is(Type.Group.Void));
    }
}