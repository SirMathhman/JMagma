package com.meti.call.structure;

import com.meti.FeatureTest;
import org.junit.jupiter.api.Test;

class StructureFeatureTest extends FeatureTest {
    @Test
    void empty(){
        assertCompile("struct Empty{};", """
                struct Empty {
                }
                """);
    }

    @Test
    void single(){
        assertCompile("struct Wrapper{int value;};", """
                struct Wrapper {
                    const value : I16
                }
                """);
    }

    @Test
    void multiple(){
        assertCompile("struct Point{int x;int y;};", """
                struct Point {
                    const x : I16,
                    const y : I16
                }
                """);
    }
}
