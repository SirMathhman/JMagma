package com.meti;

import org.junit.jupiter.api.Test;

import static com.meti.MagmaCompiler.MagmaCompiler_;
import static org.junit.jupiter.api.Assertions.*;

class MagmaCompilerTest {
    @Test
    void testMain(){
        String result = MagmaCompiler_.compile("def main() : Int => {return 0;}");
        assertEquals("int main(){return 0;}", result);
    }
}