package com.meti.compile.field;

import com.meti.compile.Node;

public interface Field extends Node {
	enum Flag {
		CONST,
		LET
	}
}
