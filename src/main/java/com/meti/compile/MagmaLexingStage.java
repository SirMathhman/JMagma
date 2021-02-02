package com.meti.compile;

import com.meti.compile.feature.condition.ElifLexer;
import com.meti.compile.feature.condition.ElseLexer;
import com.meti.compile.feature.condition.IfLexer;
import com.meti.compile.feature.condition.WhileLexer;
import com.meti.compile.feature.function.FunctionLexer;
import com.meti.compile.feature.function.InvocationLexer;
import com.meti.compile.feature.function.ReturnLexer;
import com.meti.compile.feature.primitive.BooleanLexer;
import com.meti.compile.feature.primitive.QuantityLexer;
import com.meti.compile.feature.reference.ReferenceLexer;
import com.meti.compile.feature.scope.AssignmentLexer;
import com.meti.compile.feature.scope.BlockLexer;
import com.meti.compile.feature.scope.DeclarationLexer;
import com.meti.compile.feature.scope.Lexer;
import com.meti.compile.feature.structure.AcccessorLexer;
import com.meti.compile.feature.structure.ConstructionLexer;
import com.meti.compile.feature.structure.StructureLexer;
import com.meti.compile.token.*;

import java.util.stream.Stream;

public class MagmaLexingStage implements LexingStage {
	public static final MagmaLexingStage MagmaLexingStage_ = new MagmaLexingStage();

	private MagmaLexingStage() {
	}

	@Override
	public Field lexField(String line) {
		return FieldLexer.FieldLexer.lex(line);
	}

	@Override
	public Token lexNode(String line) {
		return streamLexers()
				.filter(lexer -> lexer.canLex(line))
				.map(lexer -> lexer.lex(line))
				.findFirst()
				.orElse(new Content(line));
	}

	private Stream<Lexer<Token>> streamLexers() {
		return Stream.of(
				BooleanLexer.BooleanLexer_,
				BlockLexer.BlockLexer_,
				FunctionLexer.FunctionLexer_,
				DeclarationLexer.DeclarationLexer_,
				IfLexer.IfLexer_,
				WhileLexer.WhileLexer_,
				ElseLexer.ElseLexer_,
				ElifLexer.ElifLexer_,
				ReturnLexer.ReturnLexer_,
				InvocationLexer.InvocationLexer_,
				StructureLexer.StructureLexer_,
				ConstructionLexer.ConstructionLexer_,
				AcccessorLexer.AccessorLexer_,
				ReferenceLexer.ReferenceLexer_,
				DeclarationLexer.DeclarationLexer_,
				QuantityLexer.QuantityLexer_,
				AssignmentLexer.AssignmentLexer_
		);
	}

	@Override
	public Token lexType(String content) {
		if (content.equals("I16")) return new Content("int");
		return new Content(content);
	}
}