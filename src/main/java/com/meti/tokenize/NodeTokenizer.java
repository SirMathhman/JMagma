package com.meti.tokenize;

import com.meti.Node;
import com.meti.scope.assign.AssignmentTokenizer;
import com.meti.call.block.BlockTokenizer;
import com.meti.primitive.chars.CharTokenizer;
import com.meti.scope.declare.DeclarationTokenizer;
import com.meti.call.function.FunctionTokenizer;
import com.meti.call.ifs.IfTokenizer;
import com.meti.scope.initialize.InitializationTokenizer;
import com.meti.primitive.ints.IntTokenizer;
import com.meti.call.invoke.MappingTokenizer;
import com.meti.call.returns.ReturnTokenizer;
import com.meti.call.structure.StructureTokenizer;
import com.meti.scope.vars.VariableTokenizer;
import com.meti.call.whiles.WhileTokenizer;

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
