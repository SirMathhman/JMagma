package com.meti;

import com.meti.field.Field;
import com.meti.field.FieldTokenizer;

import java.util.Optional;
import java.util.stream.IntStream;

import static com.meti.ImmutableStrategyBuffer.EmptyBuffer;
import static com.meti.ParameterStrategy.ParameterStrategy_;
import static com.meti.Structure.Structure;

public class StructureTokenizer extends StringTokenizer<Node> {
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
        Structure.Complete withName = attachName(builder, bodyTrim);
        Structure.Complete reduce = attachMembersFromBody(withName, bodyTrim);
        return reduce.complete();
    }

    private Structure.Complete attachMembersFromBody(Structure.Complete withName, String bodyTrim) {
        int separator = bodyTrim.indexOf('{');
        int length = bodyTrim.length();
        String membersSlice = bodyTrim.substring(separator + 1, length - 1);
        String membersTrim = membersSlice.trim();
        return attachMembersFromSlice(withName, membersTrim);
    }

    private Structure.Complete attachMembersFromSlice(Structure.Complete withName, String membersTrim) {
        return IntStream.range(0, membersTrim.length())
                .mapToObj(membersTrim::charAt)
                .reduce(EmptyBuffer, ParameterStrategy_::process, (buffer0, buffer1) -> buffer1)
                .complete()
                .trim()
                .map(this::tokenizeField)
                .reduce(withName, Structure.Builder::withField, (complete, complete2) -> complete2);
    }

    private Structure.Complete attachName(Structure.None builder, String bodyTrim) {
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
