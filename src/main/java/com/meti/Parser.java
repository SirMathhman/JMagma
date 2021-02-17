package com.meti;

import java.util.Optional;

public interface Parser {
	Optional<State> parse(State state) throws ParseException;
}
