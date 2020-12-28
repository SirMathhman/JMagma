package com.meti.compile.feature.function;

import com.meti.api.core.Option;
import com.meti.compile.Script;
import com.meti.compile.feature.Node;
import com.meti.compile.Processor;
import com.meti.compile.process.ParseException;

import java.util.function.Supplier;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;
import static com.meti.compile.feature.EmptyNode.EmptyNode_;
import static com.meti.compile.feature.field.Field.Flag.NATIVE;

public class NativeFunctionRemover implements Processor {
	public static final NativeFunctionRemover NativeFunctionRemover_ = new NativeFunctionRemover();

	private NativeFunctionRemover() {
	}

	private Node process(Node node) throws ParseException {
		return isNative(node) ? EmptyNode_ : node;
	}

	@Override
	public Option<Node> processOptionally(Script script, Node node) throws ParseException {
		return node.is(Node.Group.Function) ? Some(process(node)) : None();
	}

	private boolean isNative(Node node) throws ParseException {
		Supplier<ParseException> parseExceptionSupplier = () -> new ParseException(node + " was a function but didn't have an identity.");
		return node.findIdentity()
				.orElseThrow(parseExceptionSupplier)
				.isFlagged(NATIVE);
	}
}