package com.meti;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.meti.Field.Field;

public class FieldTokenizer extends StringTokenizer<Field> {
    public FieldTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Field> tokenize() {
        //const x : Int
        if (content.contains(":")) {
            Field.Both both = Field()
                    .withName(extractName())
                    .withType(extractType());
            return Optional.of(foldFlags(both));
        } else {
            //TODO: implicit fields
            throw new IllegalStateException("Implicit types in '" + content + "' aren't supported yet.");
        }
    }

    private Field foldFlags(Field.Both both) {
        String header = extractHeader();
        int separator = header.lastIndexOf(' ');
        String slice = header.substring(0, separator);
        String formatted = slice.trim();
        String[] flagArray = formatted.split(" ");
        List<String> flagList = List.of(flagArray);
        return foldFlagsStreamed(both, flagList.stream());
    }

    private String extractName() {
        String header = extractHeader();
        int lastSpace = header.lastIndexOf(' ');
        return header.contains(" ") ?
                header.substring(lastSpace + 1).trim() :
                header;
    }

    private String extractHeader() {
        int separator = content.indexOf(':');
        String slice = content.substring(0, separator);
        return slice.trim();
    }

    private Field foldFlagsStreamed(Field.Both both, Stream<String> stream) {
        return stream.filter(s -> !s.isBlank())
                .map(String::trim)
                .map(String::toUpperCase)
                .map(Field.Flag::valueOf)
                .reduce(both, Field.Builder::withFlag, (both1, both2) -> both2)
                .complete();
    }

    private Type extractType() {
        int separator = content.indexOf(':');
        String slice = content.substring(separator + 1);
        String formatted = slice.trim();
        return new ContentType(formatted);
    }
}
