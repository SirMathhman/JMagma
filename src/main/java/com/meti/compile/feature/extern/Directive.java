package com.meti.compile.feature.extern;

import com.meti.compile.feature.Node;

public enum Directive {
	Include;

	Node toNode(String value) {
		return () -> "#" + name().toLowerCase()+ " " + value + "\n";
	}
}
