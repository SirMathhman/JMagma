package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.Field.Field;
import static com.meti.Field.Flag.DEF;
import static com.meti.FunctionType.FunctionType;
import static com.meti.Implementation.Implementation;
import static com.meti.Primitive.I16;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTokenizerTest {
    @Test
    void tokenizeWithParameter() {
        Node expected = Implementation()
                .withIdentity(Field()
                        .withName("pass")
                        .withFlag(DEF)
                        .withType(FunctionType()
                                .withReturnType(new ContentType("I16"))
                                .withParameter(new ContentType("I16"))
                                .complete())
                        .complete())
                .withParameter(Field()
                        .withName("value")
                        .withType(new ContentType("I16"))
                        .complete())
                .withValue(new ContentNode("{return value;}"))
                .complete();
        Node actual = new FunctionTokenizer("def pass(value : I16) : I16 => {return value;}")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    void tokenize() {
        Node expected = Implementation()
                .withIdentity(Field()
                        .withName("main")
                        .withFlag(DEF)
                        .withType(FunctionType()
                                .withReturnType(new ContentType("Int"))
                                .complete())
                        .complete())
                .withValue(new ContentNode("{return 0;}"))
                .complete();
        Node actual = new FunctionTokenizer("def main() : Int => {return 0;}")
                .tokenize()
                .orElseThrow();
        assertEquals(expected, actual);
    }
}