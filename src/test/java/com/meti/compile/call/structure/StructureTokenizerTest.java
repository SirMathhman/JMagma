package com.meti.compile.call.structure;

import com.meti.compile.Node;
import com.meti.compile.content.ContentType;
import com.meti.compile.generics.GenericType;
import com.meti.compile.scope.field.Field;
import org.junit.jupiter.api.Test;

import static com.meti.compile.scope.field.Field.Field;
import static com.meti.compile.call.structure.Structure.Structure;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StructureTokenizerTest {
    @Test
    void tokenizeGeneric(){
        Node expected = Structure()
                .withName("Wrapper")
                .withField(createGeneric())
                .complete();
        //Make sure when using text blocks to add .trim() at the end for the tokenizer to work properly.
        Node actual = new StructureTokenizer("""
                struct Wrapper[T] {
                    const value : T
                }
                """.trim())
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }

    private Field createGeneric() {
        return Field()
                .withName("value")
                .withFlag(Field.Flag.CONST)
                .withType(new ContentType("T"))
                .complete();
    }

    @Test
    void tokenize() {
        Node expected = Structure()
                .withName("Wrapper")
                .withField(createFirst())
                .withField(createSecond())
                .complete();
        //Make sure when using text blocks to add .trim() at the end for the tokenizer to work properly.
        Node actual = new StructureTokenizer("""
                struct Wrapper {
                    const first : I8,
                    let second : I64
                }
                """.trim())
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }

    private Field createFirst() {
        return Field().withName("first")
                .withType(new ContentType("I8"))
                .withFlag(Field.Flag.CONST)
                .complete();
    }

    private Field createSecond() {
        return Field().withName("second")
                .withType(new ContentType("I64"))
                .withFlag(Field.Flag.LET)
                .complete();
    }
}