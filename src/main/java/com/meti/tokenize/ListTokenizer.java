package com.meti.tokenize;

import com.meti.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ListTokenizer extends CompoundTokenizer<Node> {
    private final List<Function<String, Tokenizer<Node>>> factories;

    private ListTokenizer(String content, List<Function<String, Tokenizer<Node>>> factories) {
        super(content);
        this.factories = factories;
    }

    static Builder ListTokenizer() {
        return new Builder();
    }

    @Override
    protected Stream<Function<String, Tokenizer<Node>>> streamFactories() {
        return factories.stream();
    }

    static class Builder {
        private final List<Function<String, Tokenizer<Node>>> cache;

        Builder() {
            this(Collections.emptyList());
        }

        Builder(List<Function<String, Tokenizer<Node>>> cache) {
            this.cache = cache;
        }

        Builder with(Function<String, Tokenizer<Node>> factory) {
            List<Function<String, Tokenizer<Node>>> newCache = new ArrayList<>(cache);
            newCache.add(factory);
            return new Builder(newCache);
        }

        Tokenizer<Node> build(String content) {
            return new ListTokenizer(content, cache);
        }
    }
}
