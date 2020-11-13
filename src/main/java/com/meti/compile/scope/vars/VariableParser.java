package com.meti.compile.scope.vars;

import com.meti.compile.AbstractParser;
import com.meti.compile.Node;
import com.meti.compile.state.Stack;
import com.meti.compile.state.State;

import java.util.Optional;

public class VariableParser extends AbstractParser {
    public VariableParser(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> process() {
        if (previous.has(Node.Group.Variable)) {
            if (!previous.transformBoth(this::isDefined)) {
                throw previous.transformCurrent(this::createUndefinedNode);
            }
            return Optional.of(previous);
        } else {
            return Optional.empty();
        }
    }

    private boolean isDefined(Node node, Stack stack) {
        return node.transformValue(String.class, stack::isDefined);
    }

    private IllegalStateException createUndefinedNode(Node node) {
        return node.transformValue(String.class, this::createUndefined);
    }

    private IllegalStateException createUndefined(String value) {
        String format = "'%s' is not defined.";
        String message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
