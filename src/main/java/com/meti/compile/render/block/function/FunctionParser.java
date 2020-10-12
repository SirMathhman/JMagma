package com.meti.compile.render.block.function;

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
import static com.meti.compile.render.block.structure.Construction.Construction;
import static com.meti.compile.render.block.structure.ObjectType.ObjectType;
import static com.meti.compile.render.field.InlineField.Field;
import static com.meti.compile.render.resolve.MagmaResolver.Resolver;
import static com.meti.compile.render.scope.Initialization.Initialization;
import static com.meti.compile.render.scope.Variable.This;
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
            Node formatted;
            if (next.walkChildren().anyMatch(node -> node.equals(This))) {
                var structureType = next.identity().type().secondary();
                if (structureType.is(Structure)) {
                    var content = structureType.getContent();
                    var name = identity.name();
                    var objectType = ObjectType(content);
                    var block = next.streamChildren()
                            .findFirst()
                            .orElseThrow();
                  if(block.is(Node.Group.Block)) {
                      List<? extends Node> lines = block.streamChildren().collect(Collectors.toList());
                      List<Node> newLines = new ArrayList<>();
                      newLines.add(Initialization(Field("this", objectType), Construction()));
                      newLines.addAll(lines);
                      var newBlock = Block(newLines);
                      formatted = Function(name, objectType, newBlock);
                  } else {
                      var format = "%s is not a block.";
                      var message = format.formatted(block);
                      throw new IllegalStateException(message);
                  }
                } else {
                    var format = "%s is not a structure.";
                    var message = format.formatted(structureType);
                    throw new IllegalStateException(message);
                }
            } else {
                formatted = next;
            }
            var nextState = state.with(formatted);
            return Optional.of(nextState);
        }
        return Optional.empty();
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
