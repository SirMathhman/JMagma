package com.meti.call.function;

import com.meti.Node;
import com.meti.content.ContentNode;
import com.meti.content.ContentType;
import org.junit.jupiter.api.Test;

import static com.meti.scope.field.Field.Field;
import static com.meti.scope.field.Field.Flag.DEF;
import static com.meti.call.function.FunctionType.FunctionType;
import static com.meti.call.function.Implementation.Implementation;
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