package com.meti.compile.path;

import com.meti.compile.Node;

import java.util.List;

public class EmptyScriptPath implements ScriptPath {
    public static final ScriptPath EmptyPath = new EmptyScriptPath();

    @Override
    public Node read(List<String> package_, String name) {
        throw new IllegalStateException("Script path is empty.");
    }
}
