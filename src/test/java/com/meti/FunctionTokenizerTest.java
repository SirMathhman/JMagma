package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.Field.Field;
import static com.meti.Field.Flag.DEF;
import static com.meti.FunctionType.FunctionType;
import static com.meti.Implementation.Implementation;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTokenizerTest {

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