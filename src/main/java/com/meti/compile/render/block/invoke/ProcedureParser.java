package com.meti.compile.render.block.invoke;

import com.meti.compile.render.node.Node;
import com.meti.compile.render.primitive.Primitive;
import com.meti.compile.render.process.AbstractProcessor;
import com.meti.compile.render.process.State;
import com.meti.compile.render.type.Type;

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
            var children = value.streamChildren().collect(Collectors.toList());
            var caller = children.get(0);
            var arguments = children.subList(1, children.size());
            var type = Resolver(state)
                    .resolve()
                    .orElseThrow(() -> invalidateValue(value));
            if(!type.is(Type.Group.Function)) {
                var format = "Caller of %s, '%s', isn't a functional type.";
                var message = format.formatted(value, caller);
                throw new IllegalStateException(message);
            }
            if (type.start().equals(Primitive.Void)) {
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
