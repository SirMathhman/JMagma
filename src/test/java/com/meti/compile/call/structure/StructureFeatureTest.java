package com.meti.compile.call.structure;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class StructureFeatureTest extends FeatureTest {
    @Test
    void generics(){
        assertCompile("""
                struct Wrapper{
                }""", """
                struct Wrapper[T] {
                }
                """);
    }

    @Test
    void genericField(){
        assertCompile("""
                struct Wrapper{
                    void * value;
                }""", """
                struct Wrapper[T] {
                    const value : T
                }""");
    }

    @Test
    void empty(){
        assertCompile("""
                struct Empty{
                };""", """
                struct Empty {
                }
                """);
    }

    @Test
    void single(){
        assertCompile("""
                struct Wrapper{
                    int value;
                };""", """
                struct Wrapper {
                    const value : I16
                }
                """);
    }

    @Test
    void multiple(){
        assertCompile("""
                struct Point{
                    int x;
                    int y;
                };""".replace("    ", "\t"), """
                struct Point {
                    const x : I16,
                    const y : I16
                }
                """);
    }
}
