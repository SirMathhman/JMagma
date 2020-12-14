package com.meti.exec.compile.render.field;

import com.meti.api.core.Equatable;
import com.meti.exec.compile.render.Renderable;

public interface Field<F> extends Renderable, Equatable<F> {
	enum Flag {
		Const
	}
}
