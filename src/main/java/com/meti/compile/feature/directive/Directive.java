package com.meti.compile.feature.directive;

import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.Locale;

public enum Directive {
	IfNDef,
	Define,
	Include,
	EndIf;

	public Token apply() {
		return new Content("#%s".formatted(name().toLowerCase(Locale.ROOT)));
	}

	public Token apply(String value) {
		return new Content("#%s %s\n".formatted(name().toLowerCase(Locale.ROOT), value));
	}
}
