package com.meti.compile.call.function;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class FunctionFeatureTest extends FeatureTest {
    @Test
    void empty() {
        assertCompile("void empty(){}", "def empty() : Void_ => {}");
    }

    @Test
    void consume() {
        assertCompile("void consume(int value){}", "def consume(value : I16) : Void_ => {}");
    }

    @Test
    void supply() {
        assertCompile("int supply(){return 0;}", "def supply() : I16 => {return 0;}");
    }

    @Test
    void apply() {
        assertCompile("int apply(int value){return value;}", "def apply(value : I16) : I16 => {return value;}");
    }
}
