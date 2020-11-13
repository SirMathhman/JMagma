package com.meti.compile.call.function;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.content.ContentType;
import org.junit.jupiter.api.Test;

import static com.meti.compile.scope.field.Field.Field;
import static com.meti.compile.scope.field.Field.Flag.DEF;
import static com.meti.compile.call.function.FunctionType.FunctionType;
import static com.meti.compile.call.function.Implementation.Implementation;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTokenizerTest {
    @Test
    void tokenizeWithParameter() {
        Node expected = Implementation()
                .withIdentity(Field()
                        .withName("pass")
                        .withFlag(DEF)
                        .withType(FunctionType()
                                .withReturn(new ContentType("I16"))
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
                                .withReturn(new ContentType("Int"))
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