package com.meti.compile.feature.declare;

import com.meti.compile.ResolutionException;
import com.meti.compile.token.attribute.Attribute;
import com.meti.compile.token.attribute.AttributeException;
import com.meti.compile.token.attribute.FieldAttribute;
import com.meti.core.F1;
import com.meti.core.F1E1;
import com.meti.compile.parse.ParseException;
import com.meti.compile.parse.Parser;
import com.meti.compile.parse.State;
import com.meti.compile.token.Field;
import com.meti.compile.token.Token;
import com.meti.compile.token.Tokens;

import java.util.Optional;

import static com.meti.compile.feature.primitive.ImplicitType.ImplicitType_;
import static com.meti.compile.app.MagmaResolver.MagmaResolver_;

public class DeclarationParser implements Parser {
	public static Parser DeclarationParser_ = new DeclarationParser();

	public DeclarationParser() {
	}

	@Override
	public Optional<State> parse(State state) throws ParseException {
		try {
			if (Tokens.is(state.getCurrent(), Token.Type.Declaration)) {
				var stack = state.getStack();
				Field identity = null;
				try {
					identity = state.getCurrent()
							.apply(Attribute.Name.Identity)
							.computeField();
				} catch (AttributeException e) {
					throw new ParseException(e);
				}
				if (identity.applyToNameE1(value -> ((F1E1<String, Boolean, RuntimeException>) stack::isDefined).apply(value.getContent()))) {
					throw identity.applyToName(input -> ((F1<String, ParseException>) s -> new ParseException("'" + s + "' is already defined.")).apply(input.getContent()));
				}
				Field identityToUse;
				if (identity.applyToType(token -> token == ImplicitType_)) {
					var newType = identity.applyToValueE1(token -> {
						var newState = state.withCurrent(token);
						try {
							return MagmaResolver_.resolve(newState);
						} catch (ResolutionException e) {
							throw new ParseException(e);
						}
					}).orElseThrow();
					identityToUse = identity.withType(newType);
				} else {
					identityToUse = identity;
				}

				var newStack = stack.define(null, identityToUse);
				var attribute = new FieldAttribute(identityToUse);
				var newCurrent = state.getCurrent().copy(Attribute.Name.Identity, attribute);
				return Optional.of(state.withStack(newStack).withCurrent(newCurrent));
			} else {
				return Optional.empty();
			}
		} catch (AttributeException e) {
			throw new ParseException(e);
		}
	}
}
