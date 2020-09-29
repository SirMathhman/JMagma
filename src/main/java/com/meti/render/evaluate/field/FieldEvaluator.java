package com.meti.render.evaluate.field;

import com.meti.render.evaluate.type.ImplicitType;
import com.meti.render.evaluate.type.Type;
import com.meti.render.feature.content.ContentType;
import com.meti.render.evaluate.Evaluatable;
import com.meti.render.evaluate.Evaluator;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldEvaluator implements Evaluator<Field> {
    public static final Evaluator<Field> FieldEvaluator = new FieldEvaluator();

    private FieldEvaluator() {
    }

    @Override
    public Optional<Evaluatable<Field>> evaluate(String content) {
        int separator = content.indexOf(':');
        Type type = extractType(content, separator);
        String keyString = extractKeyString(content, separator);
        String name = extractName(keyString);
        Set<Field.Flag> flags = extractKeys(keyString);

        return Optional.of(() -> new EvaluatedField(flags, name, type));
    }

    private Type extractType(String content, int separator) {
        Type type;
        if (separator == -1) {
            type = ImplicitType.ImplicitType;
        } else {
            String value = content.substring(separator + 1);
            String trimmed = value.trim();
            type = new ContentType(trimmed);
        }
        return type;
    }

    private String extractName(String keyString) {
        String name;
        int space = keyString.lastIndexOf(' ');
        if (space == -1) {
            name = keyString;
        } else {
            String nameValue = keyString.substring(space + 1);
            name = nameValue.trim();
        }
        return name;
    }

    private Set<Field.Flag> extractKeys(String keyString) {
        Set<Field.Flag> flags;
        int space1 = keyString.lastIndexOf(' ');
        if (space1 == -1) {
            flags = Collections.emptySet();
        } else {
            String flagStrings = keyString.substring(0, space1);
            flags = Stream.of(flagStrings.split(" "))
                    .filter(s -> !s.isBlank())
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .map(Field.Flag::valueOf)
                    .collect(Collectors.toSet());
        }
        return flags;
    }

    private String extractKeyString(String content, int separator) {
        String value;
        if (separator == -1) {
            value = content;
        } else {
            value = content.substring(0, separator);
        }
        return value.trim();
    }

    private static class EvaluatedField implements Field {
        private final Set<Flag> flags;
        private final Type type;
        private final String name;

        public EvaluatedField(Set<Flag> flags, String name, Type type) {
            this.flags = flags;
            this.type = type;
            this.name = name;
        }

        @Override
        public Field mapByType(Function<Type, Type> mapping) {
            return new EvaluatedField(flags, name, mapping.apply(type));
        }

        @Override
        public boolean isFlagged(Flag flag) {
            return flags.contains(flag);
        }

        @Override
        public String render() {
            return type.render(name);
        }
    }
}
