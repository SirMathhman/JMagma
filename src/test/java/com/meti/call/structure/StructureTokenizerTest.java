package com.meti.call.structure;

import com.meti.Node;
import com.meti.content.ContentType;
import com.meti.scope.field.Field;
import org.junit.jupiter.api.Test;

import static com.meti.scope.field.Field.Field;
import static com.meti.call.structure.Structure.Structure;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StructureTokenizerTest {

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