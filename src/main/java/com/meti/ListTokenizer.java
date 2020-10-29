package com.meti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ListTokenizer extends CompoundTokenizer {
    private final List<Function<String, Tokenizer>> factories;

    private ListTokenizer(String content, List<Function<String, Tokenizer>> factories) {
        super(content);
        this.factories = factories;
    }

    static Builder ListTokenizer() {
        return new Builder();
    }

    @Override
    protected Stream<Function<String, Tokenizer>> streamFactories() {
        return factories.stream();
    }

    static class Builder {
        private final List<Function<String, Tokenizer>> cache;

        Builder() {
            this(Collections.emptyList());
        }

        Builder(List<Function<String, Tokenizer>> cache) {
            this.cache = cache;
        }

        Builder with(Function<String, Tokenizer> factory) {
            List<Function<String, Tokenizer>> newCache = new ArrayList<>(cache);
            newCache.add(factory);
            return new Builder(newCache);
        }

        Tokenizer build(String content) {
            return new ListTokenizer(content, cache);
        }
    }
}
