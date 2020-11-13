package com.meti.compile;

import com.meti.compile.state.State;

import java.util.Optional;

public interface Parser {
    Optional<State> process();
}
