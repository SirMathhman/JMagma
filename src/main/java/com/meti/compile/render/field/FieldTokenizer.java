package com.meti.compile.render.field;

import com.meti.compile.render.evaluate.Tokenizer;
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
        return content.contains(":") ?
                evaluateExplicitly() :
                evaluateImplicitly();
    }

    private Optional<Field> evaluateExplicitly() {
        int typeSeparator = content.indexOf(':');
        var keyString = content.substring(0, typeSeparator).trim();
        var typeString = content.substring(typeSeparator + 1).trim();
        return evaluateWithSeparator(keyString, space -> complete(keyString, typeString, space));
    }

    private Optional<Field> evaluateWithSeparator(String keyString, Function<Integer, Optional<Field>> function) {
        var lastSpace = keyString.lastIndexOf(' ');
        if (lastSpace == -1) {
            return Optional.empty();
        }
        return function.apply(lastSpace);
    }

    private Optional<Field> complete(String keyString, String typeString, int lastSpace) {
        var flagString = keyString.substring(0, lastSpace);
        var formattedFlags = flagString.trim();
        var flags = extractFlags(formattedFlags);
        var nameString = keyString.substring(lastSpace + 1);
        var formattedName = nameString.trim();
        var type = new ContentType(typeString);
        return complete(flags, formattedName, type);
    }

    private Optional<Field> evaluateImplicitly() {
        return evaluateWithSeparator(content, this::complete);
    }

    private Optional<Field> complete(int lastSpace) {
        var flagString = content.substring(0, lastSpace).trim();
        var flags = extractFlags(flagString);
        var name = content.substring(lastSpace + 1).trim();
        return complete(flags, name, ImplicitType.ImplicitType);
    }

    private Optional<Field> complete(List<Field.Flag> flags, String name, Type type) {
        return hasValidFlags(flags) ?
                Optional.of(new EvaluatedField(name, type)) :
                Optional.empty();
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

    private static final class EvaluatedField implements Field {
        private final String name;
        private final Type type;

        private EvaluatedField(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String render() {
            return type.render(name);
        }
    }
}
