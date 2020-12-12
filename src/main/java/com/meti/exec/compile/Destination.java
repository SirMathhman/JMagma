package com.meti.exec.compile;

import com.meti.api.core.Option;

public interface Destination<G> {
	Destination<G> put(Package p, Group group, String value) throws CompileException;

	Option<String> apply(Package p, Group group);
}
