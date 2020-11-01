package com.meti.compile.call.invoke;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

public class MappingFeatureTest extends FeatureTest {
    @Test
    void arity0(){
        assertCompile("caller()", "caller()");
    }

    @Test
    void arity1(){
        assertCompile("caller(arg0)", "caller(arg0)");
    }

    @Test
    void arity2(){
        assertCompile("caller(arg0,arg1)", "caller(arg0, arg1)");
    }
}
