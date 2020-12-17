package com.meti.compile;

public interface Type extends Renderable {
	@Override
	default String render() {
		return render("");
	}

	String render(String value);
}
