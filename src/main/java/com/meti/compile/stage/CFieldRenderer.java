package com.meti.compile.stage;

import com.meti.api.magma.core.None;
import com.meti.api.magma.core.Option;
import com.meti.api.magma.core.Some;
import com.meti.compile.token.Content;
import com.meti.compile.token.Field;
import com.meti.compile.token.Pair;
import com.meti.compile.token.Token;

import java.util.Optional;

public class CFieldRenderer implements Renderer<Field> {
	public static final Renderer<Field> CFieldRenderer_ = new CFieldRenderer();

	private CFieldRenderer() {
	}

	@Override
	public Option<Token> render(Field token) {
		return render1(token)
				.map(Some::Some)
				.orElseGet(None::None);
	}

	private Optional<Token> render1(Field token) {
		var type = token.findType();
		var name = token.findName();
		var nameNode = new Content(name);
		return Optional.of(new Pair(type, nameNode));
	}
}
