package com.meti.compile.stage;

import com.meti.compile.Stage;
import com.meti.compile.render.block.BlockTokenizer;
import com.meti.compile.render.block.function.FunctionTokenizer;
import com.meti.compile.render.block.function.ReturnTokenizer;
import com.meti.compile.render.block.invoke.InvocationTokenizer;
import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.PrimitiveTokenizer;
import com.meti.compile.render.scope.DeclarationTokenizer;
import com.meti.compile.render.scope.InitializationTokenizer;
import com.meti.compile.render.scope.VariableTokenizer;
import com.meti.compile.render.tokenize.IntTokenizer;
import com.meti.compile.render.tokenize.Tokenizer;
import com.meti.compile.render.type.Type;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class TokenizerStage implements Stage<Node, Node> {
    @Deprecated
    public static final Stage<Node, Node> TokenizerStage_ = new TokenizerStage();

    private TokenizerStage() {
    }

    public static Node Tokenize(Node node) {
        return TokenizerStage_.apply(node);
    }

    private Stream<Function<String, Tokenizer<Node>>> streamTokenizers() {
        return Stream.of(
                BlockTokenizer::new,
                FunctionTokenizer::new,
                InitializationTokenizer::new,
                InvocationTokenizer::new,
                ReturnTokenizer::new,
                DeclarationTokenizer::new,
                IntTokenizer::new,
                VariableTokenizer::new
        );
    }

    private Stream<Function<String, Tokenizer<Type>>> streamTypeEvaluators() {
        return Stream.of(
                PrimitiveTokenizer::new
        );
    }

    private Type tokenizeType(Type type) {
        Type toReturn;
        if (type.is(Type.Group.Content)) {
            toReturn = type.transformContent(this::tokenizeTypeString);
        } else {
            toReturn = type;
        }
        return toReturn.mapByChildren(this::tokenizeType);
    }

    private Type tokenizeTypeString(String s) {
        return streamTypeEvaluators()
                .map(tokenizer -> tokenizer.apply(s))
                .map(Tokenizer::evaluate)
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> createInvalidTypeString(s));
    }

    private IllegalStateException createInvalidTypeString(String content) {
        var format = "Unknown type: %s";
        var message = format.formatted(content);
        return new IllegalStateException(message);
    }

    private Field tokenizeField(Field field) {
        return field.mapByType(this::tokenizeType);
    }

    @Override
    public Node apply(Node value) {
        return tokenizeNode(value)
                .mapByFields(this::tokenizeField)
                .mapByChildren(this::apply)
                .mapByIdentity(this::tokenizeField);
    }

    private Node tokenizeNode(Node node) {
        if (node.is(Node.Group.Content)) {
            String content = node.content();
            return tokenizeString(content);
        } else {
            return node;
        }
    }

    private Node tokenizeString(String content) {
        return streamTokenizers()
                .map(factory -> factory.apply(content))
                .map(Tokenizer::evaluate)
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Failed to evaluate: " + content));
    }
}