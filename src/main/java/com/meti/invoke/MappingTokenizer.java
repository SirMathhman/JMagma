package com.meti.invoke;

import com.meti.tokenize.slice.ImmutableStrategyBuffer;
import com.meti.Node;
import com.meti.tokenize.slice.ParameterStrategy;
import com.meti.tokenize.AbstractTokenizer;
import com.meti.content.ContentNode;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MappingTokenizer extends AbstractTokenizer<Node> {
    public MappingTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.contains("(") && content.endsWith(")")) {
            Mapping.None builder = Mapping.Mapping();
            Mapping.Complete withCaller = attachCaller(builder);
            Mapping.Complete reduce = attachArguments(withCaller);
            return Optional.of(reduce.complete());
        }
        return Optional.empty();
    }

    private Mapping.Complete attachArguments(Mapping.Complete withCaller) {
        int separator = content.indexOf('(');
        int length = content.length();
        String argumentsSlice = content.substring(separator + 1, length - 1);
        String argumentsTrim = argumentsSlice.trim();
        return streamArguments(argumentsTrim)
                .map(ContentNode::new)
                .reduce(withCaller, Mapping.Builder::withArgument, (complete, complete2) -> complete2);
    }

    private Stream<String> streamArguments(String argumentString) {
        return IntStream.range(0, argumentString.length())
                .mapToObj(argumentString::charAt)
                .reduce(ImmutableStrategyBuffer.EmptyBuffer, ParameterStrategy.ParameterStrategy_::process, (buffer0, buffer1) -> buffer1)
                .complete()
                .trim();
    }

    private Mapping.Complete attachCaller(Mapping.None builder) {
        int separator = content.indexOf('(');
        String callerSlice = content.substring(0, separator);
        String callerTrim = callerSlice.trim();
        Node caller = new ContentNode(callerTrim);
        return builder.withCaller(caller);
    }
}
