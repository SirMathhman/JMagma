package com.meti.function;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class FunctionFeatureTest extends FeatureTest {
    @Test
    void empty() {
        assertCompile("void empty(){}", "def empty() : Void => {}");
    }

    @Test
    void consume() {
        assertCompile("void consume(int value){}", "def consume(value : I16) : Void => {}");
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
