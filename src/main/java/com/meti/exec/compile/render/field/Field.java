package com.meti.exec.compile.render.field;

import com.meti.api.core.Comparable;
import com.meti.exec.compile.render.Renderable;

public interface Field<F> extends Renderable, Comparable<F> {
	enum Flag {
		Const
	}
}
