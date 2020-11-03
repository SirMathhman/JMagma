package com.meti.compile;

import com.meti.compile.path.ScriptPath;
import com.meti.compile.scope.field.Field;
import com.meti.compile.tokenize.NodeTokenizer;
import com.meti.compile.tokenize.TypeTokenizer;

public class TokenizerStage implements Stage<String, Node> {
    private final ScriptPath scriptPath;

    public TokenizerStage(ScriptPath scriptPath) {
        this.scriptPath = scriptPath;
    }

    @Override
    public Node apply(String content) {
        return new NodeTokenizer(content, scriptPath)
                .tokenize()
                .orElseThrow(() -> invalidateToken(content))
                .mapByChild(this::tokenizeNode)
                .mapByIdentity(this::tokenizeField)
                .mapByMembers(this::tokenizeField);
    }

    private Field tokenizeField(Field field) {
        return field.mapByType(this::tokenizeType);
    }

    private Type tokenizeType(Type type) {
        return tokenizeTypeContent(type).mapByChild(this::tokenizeType);
    }

    private Type tokenizeTypeContent(Type type) {
        return type.is(Type.Group.Content) ?
                type.mapContent(String.class, this::tokenizeStringAsType) :
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

    Node tokenizeNode(Node node) {
        return node.is(Node.Group.Content) ? node.transformValue(String.class, this::apply) : node;
    }

    private IllegalArgumentException invalidateToken(String value) {
        String format = "Cannot tokenize '%s'.";
        String message = format.formatted(value);
        return new IllegalArgumentException(message);
    }
}