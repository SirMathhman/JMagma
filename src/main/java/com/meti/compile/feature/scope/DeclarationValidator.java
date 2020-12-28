package com.meti.compile.feature.scope;

import com.meti.compile.script.Script;
import com.meti.compile.process.Validator;
import com.meti.compile.token.Node;
import com.meti.compile.feature.field.FlagException;
import com.meti.compile.process.ParseException;

import static com.meti.compile.feature.field.Field.Flag.CONST;
import static com.meti.compile.feature.field.Field.Flag.LET;

public class DeclarationValidator implements Validator {
	public static final Validator DeclarationValidator_ = new DeclarationValidator();

	private DeclarationValidator() {
	}

	@Override
	public void validate(Node node) throws ParseException {
		var identity = node.findIdentity().orElseThrow(this::createNoIdentity);
		if (hasBothFlags(identity))
			throw new FlagException("Declarations can't be both mutable and immutable at the same time.");
	}

	private boolean hasBothFlags(com.meti.compile.feature.field.Field identity) {
		return identity.isFlagged(LET) && identity.isFlagged(CONST);
	}

	private ParseException createNoIdentity() {
		return new ParseException("Declaration has no identity.");
	}

	@Override
	public boolean canProcess(Script script, Node node) {
		return node.is(Node.Group.Declaration);
	}
}
