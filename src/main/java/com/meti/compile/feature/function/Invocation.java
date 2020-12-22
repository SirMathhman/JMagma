package com.meti.compile.feature.function;

import com.meti.api.core.EF1;
import com.meti.compile.feature.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Invocation implements Node {
	private final Node caller;
	private final List<Node> arguments;

	public Invocation(Node caller, List<Node> arguments) {
		this.caller = caller;
		this.arguments = arguments;
	}

	public static Incomplete Invocation() {
		return new Incomplete(Collections.emptyList());
	}

	@Override
	public boolean is(Group group) {
		return group == Group.Invocation;
	}

	@Override
	public String toString() {
		return "Invocation{" +
		       "caller=" + caller +
		       ", arguments=" + arguments +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Invocation that = (Invocation) o;
		return Objects.equals(caller, that.caller) &&
		       Objects.equals(arguments, that.arguments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(caller, arguments);
	}

	@Override
	public <E extends Exception> Node mapByChildrenExceptionally(EF1<Node, Node, E> mapper) throws E {
		var newArguments = new ArrayList<Node>();
		for (Node argument : arguments) {
			newArguments.add(mapper.apply(argument));
		}
		return new Invocation(mapper.apply(caller), newArguments);
	}

	@Override
	public String render() {
		return caller.render() + renderArguments();
	}

	private String renderArguments() {
		return arguments.stream()
				.map(Node::render)
				.collect(Collectors.joining(",", "(", ")"));
	}

	public static record Incomplete(List<Node> arguments) {
		public Incomplete withArgument(Node argument) {
			var copy = new ArrayList<>(arguments);
			copy.add(argument);
			return new Incomplete(copy);
		}

		public Complete withCaller(Node caller) {
			return new Complete(caller, arguments);
		}
	}

	public static record Complete(Node caller, List<Node> arguments) {
		public Node complete() {
			return new Invocation(caller, arguments);
		}

		public Complete withArgument(Node argument) {
			var copy = new ArrayList<>(arguments);
			copy.add(argument);
			return new Complete(caller, copy);
		}
	}
}
