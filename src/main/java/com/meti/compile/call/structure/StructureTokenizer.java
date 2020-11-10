package com.meti.compile.call.structure;

import com.meti.compile.tokenize.AbstractTokenizer;
import com.meti.compile.Node;
import com.meti.compile.scope.field.Field;
import com.meti.compile.scope.field.FieldTokenizer;

import java.util.Optional;
import java.util.stream.IntStream;

import static com.meti.compile.tokenize.slice.ImmutableStrategyBuffer.EmptyBuffer;
import static com.meti.compile.tokenize.slice.ParameterStrategy.ParameterStrategy_;
import static com.meti.compile.call.structure.Structure.Structure;

public class StructureTokenizer extends AbstractTokenizer<Node> {
    public StructureTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("struct ") && content.endsWith("}")) {
            String bodySlice = content.substring(7);
            String bodyTrim = bodySlice.trim();
            Node node = tokenizeBody(bodyTrim);
            return Optional.of(node);
        }
        return Optional.empty();
    }

    private Node tokenizeBody(String bodyTrim) {
        Structure.None builder = Structure();
        Structure.WithName withName = attachName(builder, bodyTrim);
        Structure.WithName reduce = attachMembersFromBody(withName, bodyTrim);
        return reduce.complete();
    }

    private Structure.WithName attachMembersFromBody(Structure.WithName withName, String bodyTrim) {
        int separator = bodyTrim.indexOf('{');
        int length = bodyTrim.length();
        String membersSlice = bodyTrim.substring(separator + 1, length - 1);
        String membersTrim = membersSlice.trim();
        return attachMembersFromSlice(withName, membersTrim);
    }

    private Structure.WithName attachMembersFromSlice(Structure.WithName withName, String membersTrim) {
        return IntStream.range(0, membersTrim.length())
                .mapToObj(membersTrim::charAt)
                .reduce(EmptyBuffer, ParameterStrategy_::process, (buffer0, buffer1) -> buffer1)
                .complete()
                .trim()
                .map(this::tokenizeField)
                .reduce(withName, Structure.Builder::withField, (complete, withName2) -> withName2);
    }

    private Structure.WithName attachName(Structure.None builder, String bodyTrim) {
        int separator = bodyTrim.indexOf('{');
        String nameSlice = bodyTrim.substring(0, separator);
        String nameTrim = nameSlice.trim();
        return builder.withName(nameTrim);
    }

    private Field tokenizeField(String s) {
        return new FieldTokenizer(s)
                .tokenize()
                .orElseThrow(() -> invalidateField(s));
    }

    private IllegalArgumentException invalidateField(String s) {
        String format = "Invalid field: %s";
        String message = format.formatted(s);
        return new IllegalArgumentException(message);
    }
}
