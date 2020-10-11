package com.meti.compile.render.block.function;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.State;

import java.util.Optional;
import java.util.stream.Collectors;

public class FunctionLoader extends AbstractProcessor {
    public FunctionLoader(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Function)) {
            var value = state.value();
            var fields = value.streamFields().collect(Collectors.toList());
            var newScope = state.scope().enter().defineAll(fields);
            return Optional.of(state.with(newScope));
        }
        return Optional.empty();
    }
}
