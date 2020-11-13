package com.meti.compile.tokenize;

import com.meti.compile.EmptyNode;
import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.path.ScriptPath;
import com.meti.compile.path.TreeScriptPath;
import org.junit.jupiter.api.Test;

import static com.meti.compile.extern.Import.Import;
import static com.meti.compile.path.TreeScriptPath.*;
import static com.meti.compile.path.TreeScriptPath.Package;
import static com.meti.compile.path.TreeScriptPath.Script;
import static org.junit.jupiter.api.Assertions.*;

class ImportTokenizerFactoryTest {
    private ScriptPath createPath() {
        return ScriptPath(
                Package("com",
                        Package("meti",
                                Script("Main", "def main() : Int => {return 0;}"))));
    }

    @Test
    void tokenize() {
        ScriptPath path = createPath();
        ImportTokenizerFactory factory = new ImportTokenizerFactory(path);
        Node actual = factory.createTokenizer("import com.meti.Main")
                .tokenize()
                .orElseThrow();
        Node expected = Import()
                .with(new ContentNode("def main() : Int => {return 0;}"))
                .complete();
        assertEquals(expected, actual);
    }

    @Test
    void tokenizeRepetition(){
        ScriptPath path = createPath();
        ImportTokenizerFactory factory = new ImportTokenizerFactory(path);
        factory.createTokenizer("import com.meti.Main").tokenize();
        Node actual = factory.createTokenizer("import com.meti.Main")
                .tokenize()
                .orElseThrow();
        assertEquals(EmptyNode.Empty, actual);
    }
}