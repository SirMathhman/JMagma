package com.meti.compile.tokenize;

import com.meti.compile.Node;
import com.meti.compile.call.block.BlockTokenizer;
import com.meti.compile.call.function.FunctionTokenizer;
import com.meti.compile.call.ifs.IfTokenizer;
import com.meti.compile.call.invoke.MappingTokenizer;
import com.meti.compile.call.returns.ReturnTokenizer;
import com.meti.compile.call.structure.StructureTokenizer;
import com.meti.compile.call.whiles.WhileTokenizer;
import com.meti.compile.path.ScriptPath;
import com.meti.compile.primitive.chars.CharTokenizer;
import com.meti.compile.primitive.ints.IntTokenizer;
import com.meti.compile.scope.assign.AssignmentTokenizer;
import com.meti.compile.scope.declare.DeclarationTokenizer;
import com.meti.compile.scope.initialize.InitializationTokenizer;
import com.meti.compile.scope.vars.VariableTokenizer;

import java.util.function.Function;
import java.util.stream.Stream;

public class NodeTokenizer extends CompoundTokenizer<Node> {
    private final ImportTokenizerFactory importFactory;

    public NodeTokenizer(String value, ScriptPath scriptPath) {
        super(value);
        importFactory = new ImportTokenizerFactory(scriptPath);
    }

    @Override
    protected Stream<Function<String, Tokenizer<Node>>> streamFactories() {
        return Stream.of(
                importFactory::createTokenizer,
                StructureTokenizer::new,
                WhileTokenizer::new,
                IfTokenizer::new,
                MappingTokenizer::new,
                BlockTokenizer::new,
                FunctionTokenizer::new,
                ReturnTokenizer::new,
                InitializationTokenizer::new,
                DeclarationTokenizer::new,
                AssignmentTokenizer::new,
                CharTokenizer::new,
                IntTokenizer::new,
                VariableTokenizer::new);
    }

}
