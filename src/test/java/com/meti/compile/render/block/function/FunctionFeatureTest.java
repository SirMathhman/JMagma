package com.meti.compile.render.block.function;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class FunctionFeatureTest extends FeatureTest {
    @Test
    void inner() {
        assertCompile("""
                struct outer {A
                    int value;
                    int (*inner)(void*);
                }
                int outer_inner(void* ref_){
                    struct outer* this=ref_;
                    return this->value;
                }
                int outer(int value){
                    struct outer this={value,outer_inner};
                    return this.inner(&this);
                }
                """, """
                def outer(value : I16) => {
                    def inner() => value;
                    inner()
                }
                """);
    }

    @Test
    void implicit() {
        assertCompile("char supplier(){return 10;}", "def supplier() => {return 10;}");
    }

    @Test
    void implicitWithoutBlock() {
        assertCompile("char supplier(){return 10;}", "def supplier() => return 10");
    }

    @Test
    void implicitWithoutReturn() {
        assertCompile("char supplier(){return 10;}", "def supplier() => 10");
    }

    @Test
    void withoutBlock() {
        assertCompile("int supplier(){return 10;}", "def supplier() : I16 => return 10");
    }

    @Test
    void withoutReturn() {
        assertCompile("int supplier(){return 10;}", "def supplier() : I16 => 10");
    }

    @Test
    void simple() {
        assertCompile("void procedure(){}", "def procedure() : Void => {}");
    }

    @Test
    void returnType() {
        assertCompile("int supplier(){}", "def supplier() : I16 => {}");
    }

    @Test
    void singleParameter() {
        assertCompile("void consumer(int getValue){}", "def consumer(const getValue : I16) : Void => {}");
    }

    @Test
    void multipleParameters() {
        assertCompile("void consumer(int value0,int value1){}", """
                def consumer(const value0 : I16, const value1 : I16) : Void => {
                }
                """);
    }
}
