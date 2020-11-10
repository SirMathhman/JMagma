package com.meti.compile.tokenize;

import com.meti.compile.Type;
import com.meti.compile.generics.GenericTokenizer;
import com.meti.compile.primitive.PrimitiveTokenizer;
import com.meti.compile.primitive.VoidTokenizer;

import java.util.function.Function;
import java.util.stream.Stream;

public class TypeTokenizer extends CompoundTokenizer<Type> {
    public TypeTokenizer(String content) {
        super(content);
    }

    @Override
    protected Stream<Function<String, Tokenizer<Type>>> streamFactories() {
        return Stream.of(PrimitiveTokenizer::new, VoidTokenizer::new, GenericTokenizer::new);
    }
}
