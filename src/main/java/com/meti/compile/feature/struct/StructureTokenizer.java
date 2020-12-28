package com.meti.compile.feature.struct;

import com.meti.compile.token.TokenizationException;
import com.meti.compile.token.Node;
import com.meti.compile.token.Tokenizer;
import com.meti.compile.feature.field.FieldTokenizer;

import java.util.ArrayList;
import java.util.Optional;

import static com.meti.compile.token.TokenizationException.TokenizationException;
import static com.meti.compile.feature.struct.Structure.Structure;

public class StructureTokenizer implements Tokenizer<Node> {
	public static final Tokenizer<Node> StructureTokenizer_ = new StructureTokenizer();

	public StructureTokenizer() {
	}

	@Override
	public Optional<Node> tokenize(String content) throws TokenizationException {
		if (content.startsWith("struct ") && content.endsWith("}")) {
			var separator = content.indexOf('{');
			var nameSlice = content.substring(7, separator);
			var name = nameSlice.trim();
			var fieldsSlice = content.substring(separator + 1, content.length() - 1);
			var fieldsString = fieldsSlice.trim();
			var list = new ArrayList<String>();
			var buffer = new StringBuilder();
			var depth = 0;
			for (int i = 0; i < fieldsString.length(); i++) {
				var c = fieldsString.charAt(i);
				if (c == ',' && depth == 0) {
					list.add(buffer.toString());
					buffer = new StringBuilder();
				} else {
					if (c == '(') depth++;
					if (c == ')') depth--;
					buffer.append(c);
				}
			}
			list.add(buffer.toString());
			list.removeIf(String::isBlank);
			var builder = Structure();
			for (String s : list) {
				var field = FieldTokenizer.FieldTokenizer_.tokenize(s).orElseThrow(() -> TokenizationException("Invalid field: " + s));
				builder = builder.withField(field);
			}
			return Optional.of(builder.withName(name).complete());
		}
		return Optional.empty();
	}
}
