package com.meti.compile.render.block.invoke;

import com.meti.compile.render.FeatureTest;
import org.junit.jupiter.api.Test;

public class InvocationTest extends FeatureTest {
    @Test
    void testInvocations(){
        assertCompile("{void action(){}action();}", """
                {
                    def action() : Void => {
                    }
                    action();
                }
                """);
    }
}
