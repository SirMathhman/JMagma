package com.meti.compile.render.block.function;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class ThisFeatureTest extends FeatureTest {
    @Test
    void validateThisKeyword(){
        assertCompile("""
                struct Dummy{}
                struct Dummy Dummy(){
                    struct Dummy this={};
                    return this;
                }
                """, """
                def Dummy() => this;
                """);
    }
}
