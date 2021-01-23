package com.meti.compile.feature.directive;

import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

import java.util.Locale;

public enum Directive {
	IfNDef,
	Define,
	Include,
	EndIf;

	public static final String EmptyFormat = "#%s";
	public static final String ContentFormat = "#%s %s\n";

	public Token apply() {
		var asLowerCase = lowerCaseName();
		var message = EmptyFormat.formatted(asLowerCase);
		return new Content(message);
	}

	private String lowerCaseName() {
		var name = name();
		return name.toLowerCase(Locale.ROOT);
	}

	public Token apply(String content) {
		var lowerCaseName = lowerCaseName();
		var message = ContentFormat.formatted(lowerCaseName, content);
		return new Content(message);
	}
}
