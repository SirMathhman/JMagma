package com.meti.compile.stage;

import com.meti.compile.Class;

public enum CClass implements Class {
	Source("%s.c"),
	Header("%s.h");

	public final String format;

	CClass(String format) {
		this.format = format;
	}

	@Override
	public String format(String name) {
		return format.formatted(name);
	}
}
