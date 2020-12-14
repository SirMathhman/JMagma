package com.meti.exec.compile.render.feature.primitive;

import com.meti.api.collect.string.Strings;
import com.meti.api.core.None;
import com.meti.api.core.Option;
import com.meti.api.core.Some;
import com.meti.exec.compile.render.Node;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class Integer implements Node {
	private final int value;

	private Integer(int value) {
		this.value = value;
	}

	public static Integer Integer(int value) {
		return new Integer(value);
	}

	@Override
	public Option<String> render() {
		return Some(Strings.fromInt(value));
	}

	@Override
	public Option<String> findContent(){
		return None();
	}
}
