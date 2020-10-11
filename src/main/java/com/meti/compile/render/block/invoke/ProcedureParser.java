package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.State;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.meti.compile.render.resolve.MagmaResolver.*;

public class ProcedureParser extends AbstractProcessor {
    public ProcedureParser(State state) {
        super(state);
    }

    @Override
    public Optional<State> process() {
        if (state.has(Node.Group.Mapping)) {
            var value = state.value();
            var type = Resolver(state)
                    .resolve()
                    .orElseThrow(() -> invalidateValue(value));
            if (type.equals(Primitive.Void)) {
                List<? extends Node> children = value.streamChildren().collect(Collectors.toList());
                Node caller = children.get(0);
                List<? extends Node> arguments = children.subList(1, children.size());
                return Optional.of(state.with(new Procedure(caller, arguments)));
            } else {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

    private IllegalStateException invalidateValue(Node value) {
        var format = "Unable to resolve value: %s";
        var message = format.formatted(value);
        return new IllegalStateException(message);
    }
}
