package com.meti.compile.render.block.invoke;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class InvocationTest extends FeatureTest {
    @Test
    void action(){
        assertCompile("{void action(){}action();}", """
                {
                    def action() : Void => {
                    }
                    action();
                }
                """);
    }

    @Test
    void supplier() {
        assertCompile("{char supplier(){return 10;}char result=supplier();}", """
                {
                    def supplier() => 10;
                    const result = supplier();
                }
                """);
    }

    @Test
    void consumer(){
        assertCompile("{void consumer(int value){}consumer(10);}", """
                {
                    def consumer(value : I16) => {}
                    consumer(10)
                }
                """);
    }

    @Test
    void mapping(){
        assertCompile("{int mapping(int value){return value;}int result=mapping(420);}", """
                {
                
                    def mapping(value : I16) => value;
                    const result = mapping(420);
                }
                """);
    }
}
