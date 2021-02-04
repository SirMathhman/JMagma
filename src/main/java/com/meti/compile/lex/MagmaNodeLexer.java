package com.meti.compile.lex;

import com.meti.compile.token.Token;

import static com.meti.api.magma.collect.stream.Streams.of;
import static com.meti.compile.feature.condition.ElifLexer.ElifLexer_;
import static com.meti.compile.feature.condition.ElseLexer.ElseLexer_;
import static com.meti.compile.feature.condition.IfLexer.IfLexer_;
import static com.meti.compile.feature.condition.WhileLexer.WhileLexer_;
import static com.meti.compile.feature.function.FunctionLexer.FunctionLexer_;
import static com.meti.compile.feature.function.InvocationLexer.InvocationLexer_;
import static com.meti.compile.feature.function.ReturnLexer.ReturnLexer_;
import static com.meti.compile.feature.primitive.BooleanLexer.BooleanLexer_;
import static com.meti.compile.feature.primitive.QuantityLexer.QuantityLexer_;
import static com.meti.compile.feature.reference.ReferenceLexer.ReferenceLexer_;
import static com.meti.compile.feature.scope.AssignmentLexer.AssignmentLexer_;
import static com.meti.compile.feature.scope.BlockLexer.BlockLexer_;
import static com.meti.compile.feature.scope.DeclarationLexer.DeclarationLexer_;
import static com.meti.compile.feature.structure.AcccessorLexer.AccessorLexer_;
import static com.meti.compile.feature.structure.ConstructionLexer.ConstructionLexer_;
import static com.meti.compile.feature.structure.StructureLexer.StructureLexer_;

public class MagmaNodeLexer extends CompoundLexer {
	static final Lexer<Token> MagmaNodeLexer_ = new MagmaNodeLexer();

	private MagmaNodeLexer() {
	}

	@Override
	protected com.meti.api.magma.collect.stream.Stream<Lexer<Token>> streamLexers() {
		return of(BooleanLexer_, BlockLexer_, FunctionLexer_, DeclarationLexer_,
				IfLexer_, WhileLexer_, ElseLexer_, ElifLexer_,
				ReturnLexer_, InvocationLexer_, StructureLexer_, ConstructionLexer_,
				AccessorLexer_, ReferenceLexer_, DeclarationLexer_, QuantityLexer_,
				AssignmentLexer_
		);
	}
}