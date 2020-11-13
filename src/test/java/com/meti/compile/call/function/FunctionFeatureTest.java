package com.meti.compile.call.function;

import com.meti.compile.FeatureTest;
import org.junit.jupiter.api.Test;

class FunctionFeatureTest extends FeatureTest {
    @Test
    void underscoredFunction(){
        assertCompile("""
                int __lambda0__(int (*__und0__)(char)){
                    return __und0__('x');
                }
                int (*apply)(int (*)(char))=__lambda0__;
                """, "const apply : (Char => I16) => I16 = _('x');");
    }

    @Test
    void underscoreSingle(){
        assertCompile("""
                void __lambda0__(int value) {
                    return dummy(value);
                }
                void (*lambda)(int)=__lambda0__;
                """, "const lambda : (I16) => Void = dummy(_)");
    }

    @Test
    void implicitLambda(){
        assertCompile("""
                int main_(){
                    return 0;
                }
                int (*main)()=main_;
                """, """
                const main = () => 0;
                """);
    }

    @Test
    void unnamedLambda(){
        assertCompile("""
                int main_(){
                    return 0;
                }
                int (*main)()=main_;
                """, """
                const main : () => I16 = () : I16 => {
                    return 0;
                }
                """);
    }

    @Test
    void namedLambda(){
        assertCompile("""
                int main_(){
                    return 0;
                }
                int (*main)()=main_;
                """, """
                const main : () => I16 = def main_() : I16 => {
                    return 0;
                }
                """);
    }

    @Test
    void empty() {
        assertCompile("""
                void empty(){
                }""", "def empty() : Void => {}");
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
