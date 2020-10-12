package com.meti.compile.render.block.function;

import com.meti.compile.render.block.structure.Construction;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.Processor;
import com.meti.compile.render.process.State;
import com.meti.compile.render.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.meti.compile.render.block.Block.Block;
import static com.meti.compile.render.block.function.Function.Function;
import static com.meti.compile.render.block.structure.ObjectType.ObjectType;
import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.resolve.MagmaResolver.Resolver;
import static com.meti.compile.render.scope.Initialization.Initialize;
import static com.meti.compile.render.scope.Variable.this_;
import static com.meti.compile.render.type.Type.Group.Implicit;
import static com.meti.compile.render.type.Type.Group.Structure;

public class FunctionParser extends AbstractProcessor {
    public static final Function<State, Processor> FunctionParser_ = FunctionParser::FunctionParser;

    private FunctionParser(State state) {
        super(state);
    }

    public static FunctionParser FunctionParser(State state) {
        return new FunctionParser(state);
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Function)) {
            var current = state.getValue();
            var identity = current.identity();
            var newIdentity = identity.mapByType(this::resolveValue);
            var next = current.withIdentity(newIdentity);
            var formatted = isSuperFunction(next) ? formatSuperFunction(next) : next;
            var nextState = state.with(formatted);
            return Optional.of(nextState);
        }
        return Optional.empty();
    }

    private boolean isSuperFunction(Node next) {
        return next.walkChildren().anyMatch(node -> node.equals(this_));
    }

    private Node formatSuperFunction(Node function) {
        var identity = function.identity();
        var structureType = identity.type().secondary();
        if (structureType.is(Structure)) {
            var content = structureType.getContent();
            var objectType = ObjectType(content);
            var block = findValue(function);
            var newBlock = formatFunctionValue(objectType, block);
            var name = identity.name();
            return Function(name, objectType, newBlock);
        } else {
            var format = "%s is not a structure.";
            var message = format.formatted(structureType);
            throw new IllegalStateException(message);
        }
    }

    private Node formatFunctionValue(Type thisType, Node block) {
        if (block.is(Node.Group.Block)) {
            return formatBlock(thisType, block);
        } else {
            throw invalidateBlock(block);
        }
    }

    private RuntimeException invalidateBlock(Node block) {
        var format = "%s is not a block.";
        var message = format.formatted(block);
        return new IllegalStateException(message);
    }

    private Node formatBlock(Type thisType, Node block) {
        List<? extends Node> lines = block.streamChildren().collect(Collectors.toList());
        List<Node> newLines = new ArrayList<>();
        newLines.add(Initialize(Field("this", thisType), Construction.Construct()));
        newLines.addAll(lines);
        return Block(newLines);
    }

    private Node findValue(Node function) {
        return function.streamChildren()
                .findFirst()
                .orElseThrow();
    }

    private Type resolveValue(Type type) {
        if (type.secondary().is(Implicit)) {
            return type.withSecondary(resolveReturnType());
        } else {
            return type;
        }
    }

    private Type resolveReturnType() {
        var root = state.getValue();
        var value = root.value(Node.class);
        var withValue = state.with(value);
        return Resolver(withValue)
                .resolve()
                .orElseThrow(() -> invalidateValue(value));
    }

    private IllegalStateException invalidateValue(Node value) {
        var format = "Unable to resolve function getValue of: %s";
        var message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
