package com.meti.compile.render.process;

import java.util.Optional;

public interface Processor {
    Optional<State> process(State state);
}
