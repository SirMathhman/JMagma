package com.meti;

public interface Output extends Renderable {
	Output concat(Renderable other);

	Output concat(String other);
}
