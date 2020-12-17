package com.meti.compile.feature.field;

import com.meti.compile.feature.Node;

public interface Field extends Node {
	enum Flag {
		CONST,
		LET
	}
}
