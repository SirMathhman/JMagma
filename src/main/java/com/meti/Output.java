package com.meti;

public interface Output {
	Output appendChar(char c);

	Output appendField(Field field);

	Output appendOutput(Output output);

	Output appendString(String s);

	String compute() throws RenderException;

	Output prependChar(char c);

	Output prependString(String s);
}
