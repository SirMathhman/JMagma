package com.meti.exec.compile;

public interface Result {
	String apply(Group group);

	enum Group {
		Target
	}
}
