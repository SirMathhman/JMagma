package com.meti.output;

import com.meti.F1E1;
import com.meti.Field;
import com.meti.RenderException;
import com.meti.Token;

public interface Output {
	Output append(Output output);

	String compute() throws RenderException;

	Output prepend(Output output);

	default <E extends Exception> Output replaceField(F1E1<Field, String, E> replacer) throws E {
		return this;
	}

	default <E extends Exception> Output replaceNode(F1E1<Token, String, E> replacer) throws E {
		return this;
	}

	default <E extends Exception> Output replaceType(F1E1<Token, String, E> replacer) throws E {
		return this;
	}
}
