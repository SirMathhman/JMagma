package com.meti.compile.call.function;

import com.meti.compile.AbstractParser;
import com.meti.compile.Node;
import com.meti.compile.resolve.Resolver;
import com.meti.compile.state.State;

import java.util.Optional;
import java.util.function.Function;

public class ImplicitImplementationParser extends AbstractParser {
    private final Resolver<String> resolver;

    public ImplicitImplementationParser(State previous, Resolver<String> resolver) {
        super(previous);
        this.resolver = resolver;
    }

    @Override
    public Optional<State> process() {
        if(previous.has(Node.Group.Implementation)) {
            previous.mapByCurrent(new Function<Node, Node>() {
                @Override
                public Node apply(Node node) {
                    return node;
                }
            });
        }
        return Optional.empty();
    }
}
