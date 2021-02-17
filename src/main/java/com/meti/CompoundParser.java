package com.meti;

import java.util.List;
import java.util.Optional;

public abstract class CompoundParser implements Parser {
	@Override
	public Optional<State> parse(State state) throws ParseException {
		for (Parser listParser : listParsers()) {
			var optional = listParser.parse(state);
			if (optional.isPresent()) {
				return optional;
			}
		}
		return Optional.empty();
	}

	protected abstract List<Parser> listParsers();
}
