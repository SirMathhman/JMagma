package com.meti.compile.render.field;

import com.meti.compile.render.tokenize.Tokenizer;
import com.meti.compile.render.primitive.ImplicitType;
import com.meti.compile.render.type.ContentType;
import com.meti.compile.render.type.Type;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FieldTokenizer implements Tokenizer<Field> {
    private final String content;

    public FieldTokenizer(String content) {
        this.content = content;
    }

    @Override
    public Optional<Field> evaluate() {
        if (content.contains(":") || content.contains(" ")) {
            return Optional.of(content.contains(":") ?
                    evaluateExplicitly() :
                    evaluateImplicitly());
        } else {
            return Optional.empty();
        }
    }

    private Field evaluateExplicitly() {
        int typeSeparator = content.indexOf(':');
        var keyString = content.substring(0, typeSeparator).trim();
        var typeString = content.substring(typeSeparator + 1).trim();
        return evaluateWithSeparator(keyString, space -> complete(keyString, typeString, space));
    }

    private Field evaluateWithSeparator(String keyString, Function<Integer, Field> function) {
        var lastSpace = keyString.lastIndexOf(' ');
        return function.apply(lastSpace);
    }

    private Field complete(String keyString, String typeString, int lastSpace) {
        String flagString;
        if (lastSpace != -1) {
            flagString = keyString.substring(0, lastSpace);
        } else {
            flagString = "const";
        }
        var formattedFlags = flagString.trim();
        var flags = extractFlags(formattedFlags);
        var nameString = keyString.substring(lastSpace + 1);
        var formattedName = nameString.trim();
        var type = new ContentType(typeString);
        return complete(flags, formattedName, type);
    }

    private Field evaluateImplicitly() {
        return evaluateWithSeparator(content, this::complete);
    }

    private Field complete(int lastSpace) {
        String flagString;
        if (lastSpace != -1) {
            flagString = content.substring(0, lastSpace).trim();
        } else {
            flagString = "const";
        }
        var flags = extractFlags(flagString);
        var name = content.substring(lastSpace + 1).trim();
        return complete(flags, name, ImplicitType.ImplicitType_);
    }

    private Field complete(List<Field.Flag> flags, String name, Type type) {
        if (hasValidFlags(flags)) {
            return InlineField.Field(name, type);
        } else {
            var format = "No flags are found for '%s'.";
            var message = format.formatted(name);
            throw new IllegalStateException(message);
        }
    }

    private boolean hasValidFlags(List<Field.Flag> flags) {
        return flags.contains(Field.Flag.CONST) || flags.contains(Field.Flag.LET);
    }

    private List<Field.Flag> extractFlags(String flagString) {
        var flagStrings = flagString.split(" ");
        var flagStringList = List.of(flagStrings);
        return extractFlags(flagStringList);
    }

    private List<Field.Flag> extractFlags(List<String> flagStringList) {
        return flagStringList.stream()
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .map(String::toUpperCase)
                .map(Field.Flag::valueOf)
                .collect(Collectors.toList());
    }

}
