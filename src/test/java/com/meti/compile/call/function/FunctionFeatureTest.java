package com.meti.compile.call.function;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class FunctionFeatureTest extends FeatureTest {
    @Test
    void empty() {
        assertCompile("""
                void asEmpty(){
                }""", "def asEmpty() : Void => {}");
    }

    @Test
    void consume() {
        assertCompile("""
                void consume(int value){
                }""", "def consume(value : I16) : Void => {}");
    }

    @Test
    void supply() {
        assertCompile("""
                int supply(){
                    return 0;
                }""".replace("    ", "\t"), "def supply() : I16 => {return 0;}");
    }

    @Test
    void apply() {
        assertCompile("""
                int apply(int value){
                    return value;
                }""".replace("    ", "\t"), "def apply(value : I16) : I16 => {return value;}");
    }
}
