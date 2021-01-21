package com.meti.compile;

import com.meti.compile.feature.CompoundTokenizer;
import com.meti.compile.feature.Tokenizer;
import com.meti.compile.feature.Type;

import java.util.List;

import static com.meti.compile.feature.function.VarArgsTokenizer.VarArgsTokenizer_;
import static com.meti.compile.feature.primitive.PrimitiveTokenizer.PrimitiveTokenizer_;
import static com.meti.compile.feature.reference.RefTokenizer.RefTokenizer_;

public class TypeTokenizer extends CompoundTokenizer<Type> {
	static final Tokenizer<Type> TypeTokenizer_ = new TypeTokenizer();

	private TypeTokenizer() {
	}

	@Override
	protected List<Tokenizer<Type>> listChildren() {
		return List.of(VarArgsTokenizer_, RefTokenizer_, PrimitiveTokenizer_);
	}
}
