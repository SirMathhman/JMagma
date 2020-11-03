package com.meti.compile.tokenize;

import com.meti.compile.EmptyNode;
import com.meti.compile.Node;
import com.meti.compile.path.ScriptPath;

import java.util.*;

public class ImportTokenizerFactory {
    private final ScriptPath scriptPath;
    private final Collection<List<String>> cache = new ArrayList<>();

    public ImportTokenizerFactory(ScriptPath scriptPath) {
        this.scriptPath = scriptPath;
    }

    TokenizerImpl createTokenizer(String content) {
        return new TokenizerImpl(content, scriptPath);
    }

    public class TokenizerImpl extends AbstractTokenizer<Node> {
        private final ScriptPath scriptPath;

        TokenizerImpl(String content, ScriptPath scriptPath) {
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
                    List<String> package_ = List.of(packageTrim.split("\\."));
                    List<String> withName = new ArrayList<>(package_);
                    withName.add(name);
                    if (cache.contains(withName)) {
                        return Optional.of(EmptyNode.Empty);
                    } else {
                        cache.add(withName);
                        return Optional.of(scriptPath.read(package_, name));
                    }
                } else {
                    return Optional.of(scriptPath.read(Collections.emptyList(), bodyTrim));
                }
            }
            return Optional.empty();
        }
    }
}
