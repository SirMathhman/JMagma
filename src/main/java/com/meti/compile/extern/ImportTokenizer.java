package com.meti.compile.extern;

import com.meti.compile.Node;
import com.meti.compile.path.ScriptPath;
import com.meti.compile.tokenize.AbstractTokenizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class ImportTokenizer extends AbstractTokenizer<Node> {
    private final ScriptPath scriptPath;

    public ImportTokenizer(String content, ScriptPath scriptPath) {
        super(content);
        this.scriptPath = scriptPath;
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("import ")) {
            String bodySlice = content.substring(7);
            String bodyTrim = bodySlice.trim();

            if (bodyTrim.contains(".")) {
                int lastSeparator = bodyTrim.lastIndexOf('.');
                String packageSlice = bodyTrim.substring(0, lastSeparator);
                String packageTrim = packageSlice.trim();
                String nameSlice = bodyTrim.substring(lastSeparator + 1);
                String name = nameSlice.trim();
                return Optional.of(scriptPath.read(Arrays.asList(packageTrim.split("\\.")), name));
            } else {
                return Optional.of(scriptPath.read(Collections.emptyList(), bodyTrim));
            }
        }
        return Optional.empty();
    }
}
