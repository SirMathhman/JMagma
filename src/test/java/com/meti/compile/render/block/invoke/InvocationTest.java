package com.meti.compile.render.block.invoke;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class InvocationTest extends FeatureTest {
    @Test
    void testAction(){
        assertCompile("{void action(){}action();}", """
                {
                    def action() : Void => {
                    }
                    action();
                }
                """);
    }

    @Test
    void testSupplier() {
        assertCompile("{char supplier(){return 10;}char result()=supplier();}", """
                {
                    def supplier() => 10;
                    const result = supplier();
                }
                """);
    }
}
