package com.meti.compile.render.scope;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.Stack;
import com.meti.compile.render.process.State;

import java.util.Optional;

public class VariableParser extends AbstractProcessor {
    public VariableParser(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Variable)) {
            return Optional.of(check(state.getValue(), state.getScope()));
        }
        return Optional.empty();
    }

    private State check(Node current, Stack scope) {
        var name = current.value(String.class);
        if (!scope.isDefined(name)) {
            var format = "%s is not defined in %s";
            var message = format.formatted(name, scope);
            throw new UndefinedException(message);
        } else {
            return state;
        }
    }
}
