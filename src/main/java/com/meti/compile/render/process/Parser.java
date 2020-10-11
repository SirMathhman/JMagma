package com.meti.compile.render.process;

import com.meti.compile.render.block.function.FunctionLoader;
import com.meti.compile.render.block.function.FunctionParser;
import com.meti.compile.render.block.function.FunctionUnloader;
import com.meti.compile.render.block.invoke.ProcedureParser;
import com.meti.compile.render.scope.Definer;
import com.meti.compile.render.scope.VariableParser;

import java.util.function.Function;
import java.util.stream.Stream;

public class Parser extends CollectiveProcessor {

    public Parser(State state) {
        super(state);
    }

    @Override
    protected CollectiveProcessor copy(State child) {
        return new Parser(child);
    }

    @Override
    protected Stream<Function<State, Processor>> streamPreprocessors() {
        return Stream.of(FunctionLoader::new);
    }

    @Override
    protected Stream<Function<State, Processor>> streamPostprocessors() {
        return Stream.of(Definer::new,
                FunctionUnloader::new);
    }

    @Override
    protected Stream<Function<State, Processor>> streamProcessors() {
        return Stream.of(
                ProcedureParser::new,
                InitializationParser::new,
                FunctionParser::new,
                VariableParser::new
        );
    }
}
