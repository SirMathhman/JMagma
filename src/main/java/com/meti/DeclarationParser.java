package com.meti;

import java.util.Optional;

import static com.meti.ImplicitType.ImplicitType_;
import static com.meti.MagmaResolver.MagmaResolver_;

public class DeclarationParser {
	public static DeclarationParser DeclarationParser_ = new DeclarationParser();

	public DeclarationParser() {
	}

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
				if (identity.applyToNameE1(stack::isDefined)) {
					throw identity.applyToName(s -> new ParseException("'" + s + "' is already defined."));
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
