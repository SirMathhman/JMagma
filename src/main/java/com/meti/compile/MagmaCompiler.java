package com.meti.compile;

import com.meti.compile.render.block.BlockTokenizer;
import com.meti.compile.render.block.function.FunctionTokenizer;
import com.meti.compile.render.block.function.ReturnTokenizer;
import com.meti.compile.render.block.invoke.InvocationTokenizer;
import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.ContentNode;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.PrimitiveTokenizer;
import com.meti.compile.render.process.Formatter;
import com.meti.compile.render.process.InlineState;
import com.meti.compile.render.process.MappedStack;
import com.meti.compile.render.process.Parser;
import com.meti.compile.render.process.State;
import com.meti.compile.render.scope.DeclarationTokenizer;
import com.meti.compile.render.scope.InitializationTokenizer;
import com.meti.compile.render.scope.VariableTokenizer;
import com.meti.compile.render.tokenize.IntTokenizer;
import com.meti.compile.render.tokenize.Tokenizer;
import com.meti.compile.render.type.Type;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MagmaCompiler implements Compiler {
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

    private Node tokenizeTree(Node root) {
        return tokenizeNode(root)
                .mapByFields(this::tokenizeField)
                .mapByChildren(this::tokenizeTree)
                .mapByIdentity(this::tokenizeField);
    }

    private Node tokenizeNode(Node node) {
        if (node.is(Node.Group.Content)) {
            var content = node.content();
            return tokenizeString(content);
        } else {
            return node;
        }
    }

    public Node tokenizeString(String content) {
        return streamTokenizers()
                .map(factory -> factory.apply(content))
                .map(Tokenizer::evaluate)
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Failed to evaluate: " + content));
    }

    @Override
    public String compile(String content) {
        var trim = content.trim();
        var root = new ContentNode(trim);
        var tree = tokenizeTree(root);
        var stack = new MappedStack();
        var state = InlineState.State(tree, stack);
        var formatted = new Formatter(state).process().orElse(state);
        var parsed = new Parser(formatted).process().orElse(formatted);
        return render(parsed);
    }

    private String render(State parsed) {
        var renderedStructures = parsed.streamStructures().map(Node::render).collect(Collectors.joining(""));
        var renderedFunctions = parsed.streamFunctions().map(Node::render).collect(Collectors.joining(""));
        return renderedStructures + renderedFunctions + parsed.getValue().render();
    }
}
