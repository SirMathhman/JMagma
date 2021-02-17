package com.meti;

import java.util.Optional;

public class MapStack  implements Stack{
	@Override
	public Optional<Field> apply(String name) {
		return Optional.empty();
	}

	@Override
	public Optional<Script> current() {
		return Optional.empty();
	}

	@Override
	public Stack define(Field field) {
		return null;
	}

	@Override
	public Stack enter() {
		return null;
	}

	@Override
	public Stack exit() {
		return null;
	}

	@Override
	public boolean isDefined(String name) {
		return false;
	}

	@Override
	public Stack pop(Script script) {
		return null;
	}

	@Override
	public Stack push(Script script) {
		return null;
	}
}
