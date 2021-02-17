package com.meti;

import java.util.Objects;

import static com.meti.ListOutput.ListOutput;

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
	public Output prepend(Output output) {
		return ListOutput()
				.append(output)
				.append(this);
	}

	@Override
	public Output replaceField(F1<Field, String> replacer) {
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
