package com.meti.compile.render.block.function;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.State;

import java.util.Optional;

public class FunctionUnloader extends AbstractProcessor {
    public FunctionUnloader(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Function)) {
            var newScope = state.getScope()
                    .exit()
                    .define(state.getValue().identity());
            return Optional.of(state.with(newScope));
        }
        return Optional.empty();
    }
}
