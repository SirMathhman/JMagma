package com.meti.compile.scope.vars;

import com.meti.compile.Node;
import com.meti.compile.state.Stack;
import com.meti.compile.state.State;

import java.util.Optional;
import java.util.function.Function;

public class VariableParser {
    private final State previous;

    public VariableParser(State previous) {
        this.previous = previous;
    }

    public Optional<State> process() {
        if (previous.has(Node.Group.Variable)) {
            if (!previous.map(this::isDefined)) {
                throw previous.mapCurrent(this::createUndefinedNode);
            }
            return Optional.of(previous);
        } else {
            return Optional.empty();
        }
    }

    private boolean isDefined(Node node, Stack stack) {
        return node.mapValue(String.class, stack::isDefined);
    }

    private IllegalStateException createUndefinedNode(Node node) {
        return node.mapValue(String.class, this::createUndefined);
    }

    private IllegalStateException createUndefined(String value) {
        String format = "'%s' is not defined.";
        String message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
