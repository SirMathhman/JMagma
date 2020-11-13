package com.meti.compile.call.function;

import com.meti.compile.Node;
import com.meti.compile.Parser;
import com.meti.compile.ParserTest;
import com.meti.compile.state.State;
import org.junit.jupiter.api.Test;

class ImplicitImplementationParserTest extends ParserTest {
    @Test
    void process() {
        assertParse("int main(){return 0;}", """
                def main() => {
                    return 0;
                }""");
    }

    @Override
    protected Parser createParser(Node node) {
        return new ImplicitImplementationParser(new State(node), null);
    }
}