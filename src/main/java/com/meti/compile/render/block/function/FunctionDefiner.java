package com.meti.compile.render.block.function;

import com.meti.compile.render.field.Field;
import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.Stack;
import com.meti.compile.render.process.State;

import java.util.Optional;

public class FunctionDefiner extends AbstractProcessor {
    public FunctionDefiner(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if(state.has(Node.Group.Function)) {
            var scope = state.scope();
            var value = state.value();
            var identity = value.identity();
            var newScope = scope.define(identity);
            return Optional.of(state.with(newScope));
        }
        return Optional.empty();
    }
}
