package com.meti.compile.render.function;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class FunctionTest extends FeatureTest  {
    @Test
    void simple(){
        assertCompile("void procedure(){}", "def procedure() : Void => {}");
    }
}
