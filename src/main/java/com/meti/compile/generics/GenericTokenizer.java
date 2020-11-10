package com.meti.compile.generics;

import com.meti.compile.Type;
import com.meti.compile.tokenize.AbstractTokenizer;

import java.util.Optional;

public class GenericTokenizer extends AbstractTokenizer<Type> {
    public GenericTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Type> tokenize() {
        return Optional.of(new GenericType());
    }
}
