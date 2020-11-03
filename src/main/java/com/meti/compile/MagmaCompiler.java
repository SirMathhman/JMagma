package com.meti.compile;

import com.meti.compile.path.ScriptPath;
import com.meti.compile.scope.field.Field;
import com.meti.compile.tokenize.NodeTokenizer;
import com.meti.compile.tokenize.TypeTokenizer;
import com.meti.compile.tokenize.slice.BracketSplitter;

import java.util.stream.Collectors;

import static com.meti.compile.Node.Group.Content;

public class MagmaCompiler implements Compiler {
    private final ScriptPath scriptPath;

    public MagmaCompiler(ScriptPath scriptPath) {
        this.scriptPath = scriptPath;
    }

    public static Compiler MagmaCompiler(ScriptPath scriptPath) {
        return new MagmaCompiler(scriptPath);
    }

    @Override
    public String compile(String value) {
        return new BracketSplitter(value)
                .split()
                .map(this::tokenizeStringAsNode)
                .map(Node::render)
                .collect(Collectors.joining());
    }

    private Node tokenizeStringAsNode(String content) {
        return new NodeTokenizer(content, scriptPath)
                .tokenize()
                .orElseThrow(() -> invalidateToken(content))
                .mapByChild(this::tokenizeNode)
                .mapByIdentity(this::tokenizeField)
                .mapByMembers(this::tokenizeField);
    }

    private Field tokenizeField(Field field) {
        return field.mapByType(MagmaCompiler.this::tokenizeType);
    }

    private Type tokenizeType(Type type) {
        return tokenizeTypeContent(type).mapByChild(this::tokenizeType);
    }

    private Type tokenizeTypeContent(Type type) {
        return type.is(Type.Group.Content) ?
                type.mapContent(String.class, MagmaCompiler.this::tokenizeStringAsType) :
                type;
    }

    private Type tokenizeStringAsType(String s) {
        return new TypeTokenizer(s)
                .tokenize()
                .orElseThrow(() -> invalidateType(s));
    }

    private IllegalArgumentException invalidateType(String s) {
        String format = "Unable to tokenize type: %s";
        String message = format.formatted(s);
        return new IllegalArgumentException(message);
    }

    private Node tokenizeNode(Node node) {
        return node.is(Content) ? node.mapValue(String.class, this::tokenizeStringAsNode) : node;
    }

    private IllegalArgumentException invalidateToken(String value) {
        String format = "Cannot tokenize '%s'.";
        String message = format.formatted(value);
        return new IllegalArgumentException(message);
    }

}