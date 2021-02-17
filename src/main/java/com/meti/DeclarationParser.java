package com.meti;

import java.util.Optional;

import static com.meti.ImplicitType.ImplicitType_;
import static com.meti.MagmaResolver.MagmaResolver_;

public class DeclarationParser {
	public Optional<State> parse(State state) throws AttributeException, ParseException, ResolutionException {
		if (Tokens.is(state.getCurrent(), Token.Type.Declaration)) {
			var stack = state.getStack();
			var identity = state.getCurrent()
					.apply(Attribute.Name.Identity)
					.computeField();
			if (identity.applyToNameE1(stack::isDefined)) {
				throw identity.applyToName(s -> new ParseException("'" + s + "' is already defined."));
			}
			Field identityToUse;
			if (identity.applyToType(token -> token == ImplicitType_)) {
				var value = state.getCurrent()
						.apply(Attribute.Name.Value)
						.computeToken();
				var newState = state.withCurrent(value);
				var newType = MagmaResolver_.resolve(newState);
				identityToUse = identity.withType(newType);
			} else {
				identityToUse = identity;
			}

			var newStack = stack.define(identityToUse);
			var attribute = new FieldAttribute(identityToUse);
			var newCurrent = state.getCurrent().copy(Attribute.Name.Identity, attribute);
			return Optional.of(state.withStack(newStack).withCurrent(newCurrent));
		} else {
			return Optional.empty();
		}
	}
}
