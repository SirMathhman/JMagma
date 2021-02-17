package com.meti.output;

import com.meti.core.F1E1;
import com.meti.token.Field;
import com.meti.render.RenderException;
import com.meti.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListOutput implements Output {
	private final List<Output> children = new ArrayList<>();

	private ListOutput() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(children);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ListOutput that = (ListOutput) o;
		return Objects.equals(children, that.children);
	}	public static ListOutput ListOutput() {
		return new ListOutput();
	}

	@Override
	public String toString() {
		var childrenString = children.stream()
				.map(Output::toString)
				.collect(Collectors.joining(",", "[", "]"));
		return "{\"children\":%s}".formatted(childrenString);
	}

	@Override
	public Output append(Output output) {
		children.add(output);
		return this;
	}



	@Override
	public String compute() throws RenderException {
		var buffer = new StringBuilder();
		for (Output child : children) {
			buffer.append(child.compute());
		}
		return buffer.toString();
	}

	@Override
	public Output prepend(Output output) {
		children.add(0, output);
		return this;
	}


	@Override
	public <E extends Exception> Output replaceField(F1E1<Field, String, E> replacer) throws E {
		Output current = ListOutput();
		for (Output child : children) {
			current = current.append(child.replaceField(replacer));
		}
		return current;
	}

	@Override
	public <E extends Exception> Output replaceNode(F1E1<Token, String, E> replacer) throws E {
		Output current = ListOutput();
		for (Output child : children) {
			current = current.append(child.replaceNode(replacer));
		}
		return current;
	}

	@Override
	public <E extends Exception> Output replaceType(F1E1<Token, String, E> replacer) throws E {
		Output current = ListOutput();
		for (Output child : children) {
			current = current.append(child.replaceType(replacer));
		}
		return current;
	}
}
