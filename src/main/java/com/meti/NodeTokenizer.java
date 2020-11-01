package com.meti;

import com.meti.assign.AssignmentTokenizer;
import com.meti.block.BlockTokenizer;
import com.meti.chars.CharTokenizer;
import com.meti.declare.DeclarationTokenizer;
import com.meti.function.FunctionTokenizer;

import java.util.function.Function;
import java.util.stream.Stream;

public class NodeTokenizer extends CompoundTokenizer<Node> {
    public NodeTokenizer(String value) {
        super(value);
    }

    @Override
    protected Stream<Function<String, Tokenizer<Node>>> streamFactories() {
        return Stream.of(
                StructureTokenizer::new,
                WhileTokenizer::new,
                IfTokenizer::new,
                MappingTokenizer::new,
                FunctionTokenizer::new,
                BlockTokenizer::new,
                ReturnTokenizer::new,
                InitializationTokenizer::new,
                DeclarationTokenizer::new,
                AssignmentTokenizer::new,
                CharTokenizer::new,
                IntTokenizer::new,
                VariableTokenizer::new);
    }
}
