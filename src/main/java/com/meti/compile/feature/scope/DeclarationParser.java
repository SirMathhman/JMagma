package com.meti.compile.feature.scope;

import com.meti.api.core.EF1;
import com.meti.api.core.F1;
import com.meti.compile.feature.field.Field;
import com.meti.compile.process.ConditionalProcessor;
import com.meti.compile.process.ParseException;
import com.meti.compile.process.Processor;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;
import com.meti.compile.token.Type;

import static com.meti.compile.process.MagmaResolver.MagmaResolver_;
import static com.meti.compile.token.Type.Group.Implicit;

public class DeclarationParser implements ConditionalProcessor {
	public static final Processor DeclarationParser_ = new DeclarationParser();

	public DeclarationParser() {
	}

	@Override
	public Node processImpl(Script script, Node node) throws ParseException {
		EF1<Node, Type, ParseException> resolve = defaultValue -> MagmaResolver_.resolve(defaultValue)
				.orElseThrow(() -> new ParseException("Invalid default value: " + defaultValue));
		var types = node.applyToChildrenExceptionally(resolve);
		if (types.isEmpty()) throw new ParseException("Declaration had no child.");
		var replacement = types.get(0);
		F1<Field, Field> fieldFieldF1 = field -> field.replaceType(replacement);
		return node.mapByFields(fieldFieldF1);
	}

	@Override
	public boolean canProcess(Script script, Node node) {
		return node.is(Node.Group.Declaration) && node.findIdentity()
				.flatMap(Field::findType)
				.map(type -> type.is(Implicit))
				.orElse(false);
	}
}
