package com.meti.compile.path;

import com.meti.compile.Node;
import com.meti.compile.content.ContentNode;
import com.meti.compile.extern.Import;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.meti.compile.extern.Import.Import;
import static com.meti.compile.path.TreeScriptPath.Package;
import static com.meti.compile.path.TreeScriptPath.ScriptPath;
import static com.meti.compile.path.TreeScriptPath.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreeScriptPathTest {

    @Test
    void readPackage() {
        ScriptPath path = createPath();
        assertThrows(IllegalArgumentException.class, () -> path.read(List.of("com"), "meti"));
    }

    @Test
    void readEmpty() {
        ScriptPath path = createPath();
        assertThrows(IllegalArgumentException.class, () -> path.read(List.of("dummy"), "Main"));
    }

    @Test
    void readSimple() {
        ScriptPath path = createPath();
        Node read = path.read(List.of("com", "meti"), "Main");
        Node expected = Import()
                .with(new ContentNode("def main() : Int => {return 0;}"))
                .complete();
        assertEquals(expected, read);
    }

    private ScriptPath createPath() {
        return ScriptPath(
                Package("com",
                        Package("meti",
                                Script("Main", "def main() : Int => {return 0;}"))));
    }
}