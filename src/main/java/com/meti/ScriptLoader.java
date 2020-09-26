package com.meti;

import java.io.IOException;
import java.util.List;

public interface ScriptLoader {
    String load(List<String> mainPackage) throws IOException;
}
