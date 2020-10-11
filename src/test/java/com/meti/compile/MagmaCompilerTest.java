package com.meti.compile;

import org.junit.jupiter.api.Test;

import static com.meti.compile.MagmaCompiler.Compiler_;
import static org.junit.jupiter.api.Assertions.*;

class MagmaCompilerTest {
    @Test
    void implicit(){
        var actual = Compiler_.compile("def supplier() => {return 10;}");
        assertEquals("char supplier(){return 10;}", actual);
    }
}