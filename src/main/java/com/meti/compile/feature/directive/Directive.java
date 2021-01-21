package com.meti.compile.feature.directive;

import com.meti.compile.token.Content;
import com.meti.compile.token.Token;

public enum Directive {
	IFNDEF,
	DEFINE,
	ENDIF;

	public Token apply(String value) {
		return new Content("#%s %s".formatted(name(), value));
	}
}
