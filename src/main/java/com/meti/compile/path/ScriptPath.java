package com.meti.compile.path;

import com.meti.compile.Node;

import java.util.List;

public interface ScriptPath {
    Node read(List<String> package_, String name);
}
