package com.meti.compile.app;

import com.meti.compile.parse.*;
import com.meti.compile.token.Token;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.attribute.TokenAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.meti.compile.app.MagmaParser.MagmaParser_;

public class MagmaParsingStage {
	public static final MagmaParsingStage MagmaParsingStage_ = new MagmaParsingStage();

	public MagmaParsingStage() {
	}

	List<Token> parse(List<Token> tokens) throws ParseException {
		Cache<Integer> cache = new ListCache(new MapStack(), new ArrayList<>());
		for (Token token : tokens) {
			var state = cache.bind(token);
			var newState = parseToken(state);
			cache = cache.apply(newState);
		}
		return cache.listIndicators()
				.stream()
				.map(cache::apply)
				.collect(Collectors.toList());
	}

	private State parseToken(State state) throws ParseException {
		var optional = MagmaParser_.parse(state);
		var toReturn = optional.orElse(state);
		var oldCurrent = toReturn.getCurrent();
		var nodeNames = oldCurrent.stream(Attribute.Type.Node).collect(Collectors.toList());

		Cache<Attribute.Name> cache = new MapCache(toReturn.getStack());
		for (Attribute.Name name : nodeNames) {
			try {
				var child = oldCurrent.apply(name).computeToken();
				var oldState = cache.bind(child);
				var newState = parseToken(oldState);
				cache = cache.apply(name, newState);
			} catch (AttributeException e) {
				throw new ParseException(e);
			}
		}
		var indicators = cache.listIndicators();
		var newCurrent = oldCurrent;
		for (Attribute.Name indicator : indicators) {
			try {
				newCurrent = newCurrent.copy(indicator, new TokenAttribute(cache.apply(indicator)));
			} catch (AttributeException e) {
				throw new ParseException(e);
			}
		}
		return cache.bind(newCurrent);
	}
}
