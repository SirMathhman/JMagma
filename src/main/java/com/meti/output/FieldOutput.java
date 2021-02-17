package com.meti.output;

import com.meti.F1E1;
import com.meti.Field;
import com.meti.RenderException;

import java.util.Objects;

import static com.meti.output.ListOutput.ListOutput;

public class FieldOutput implements Output {
	private final Field field;

	public FieldOutput(Field field) {
		this.field = field;
	}

	@Override
	public Output append(Output output) {
		return ListOutput()
				.append(this)
				.append(output);
	}

	@Override
	public String compute() throws RenderException {
		var format = "A field still exists in the pipeline: %s";
		var message = format.formatted(field);
		throw new RenderException(message);
	}

	@Override
	public Output prepend(Output output) {
		return ListOutput()
				.append(output)
				.append(this);
	}

	@Override
	public <E extends Exception> Output replaceField(F1E1<Field, String, E> replacer) throws E {
		return new StringOutput(replacer.apply(field));
	}

	@Override
	public int hashCode() {
		return Objects.hash(field);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FieldOutput that = (FieldOutput) o;
		return Objects.equals(field, that.field);
	}

	@Override
	public String toString() {
		return "{\"field\":%s}".formatted(field.toString());
	}
}
