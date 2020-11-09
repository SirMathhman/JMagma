package com.meti.compile;

import com.meti.compile.path.ScriptPath;
import com.meti.compile.tokenize.slice.BracketSplitter;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagmaCompiler implements Compiler {
    private final Stage<String, Node> stage;

    public MagmaCompiler(ScriptPath scriptPath) {
        stage = new TokenizerStage(scriptPath);
    }

    public static Compiler MagmaCompiler(ScriptPath scriptPath) {
        return new MagmaCompiler(scriptPath);
    }

    static String format(String s) {
        StringBuilder builder = new StringBuilder();
        StringBuilder buffer = new StringBuilder();
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ';') {
                if (!buffer.isEmpty()) {
                    buffer.insert(0, composeTabs(depth));
                    buffer.append(";\n");
                    builder.append(buffer);
                    buffer = new StringBuilder();
                } else {
                    buffer.append(";");
                }
            } else if (c == '{') {
                depth++;
                buffer.append("{\n");
                builder.append(buffer);
                buffer = new StringBuilder();
            } else if (c == '}') {
                buffer.append(c);
                builder.append(buffer);
                buffer = new StringBuilder();
            } else {
                buffer.append(c);
            }
        }
        builder.append(buffer.toString());
        return builder.toString();
    }

    private static String composeTabs(int depth) {
        return IntStream.range(0, depth)
                .mapToObj(value -> "\t")
                .collect(Collectors.joining());
    }

    @Override
    public String compile(String value) {
        return new BracketSplitter(value)
                .split()
                .map(stage::apply)
                .map(Node::render)
                .collect(Collectors.joining())
                .transform(MagmaCompiler::format);
    }
}