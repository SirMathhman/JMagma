package com.meti.compile.app;

import com.meti.compile.parse.MapStack;
import com.meti.compile.parse.ParseException;
import com.meti.compile.parse.Stack;
import com.meti.compile.parse.State;
import com.meti.compile.token.Token;

import java.util.ArrayList;
import java.util.List;

import static com.meti.compile.app.MagmaParser.MagmaParser_;

public class MagmaParsingStage {
	public static final MagmaParsingStage MagmaParsingStage_ = new MagmaParsingStage();

	public MagmaParsingStage() {
	}

	List<Token> parse(List<Token> tokens) throws ParseException {
		Stack stack = new MapStack();
		var newTokens = new ArrayList<Token>();
		for (Token token : tokens) {
			var state = new State(stack, token);
			var optional = MagmaParser_.parse(state);
			var newState = optional.orElse(state);
			stack = newState.getStack();
			newTokens.add(newState.getCurrent());
		}
		return newTokens;
	}
}
