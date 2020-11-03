package com.meti.compile.primitive;

import com.meti.compile.Type;
import com.meti.compile.tokenize.AbstractTokenizer;

import java.util.Optional;

public class VoidTokenizer extends AbstractTokenizer<Type> {
    public VoidTokenizer(String content) {
        super(content);
    }

    @Override
    public Optional<Type> tokenize() {
        if (content.equals("Void")) {
            return Optional.of(Void.Void_);
        } else {
            return Optional.empty();
        }
    }
}
