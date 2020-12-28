package com.meti.compile.feature.extern;

import com.meti.api.core.Option;
import com.meti.compile.process.Processor;
import com.meti.compile.script.Script;
import com.meti.compile.token.Node;
import com.meti.compile.process.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.meti.api.core.None.None;
import static com.meti.api.core.Some.Some;

public class ImportFormatter implements Processor {
	public static final ImportFormatter ImportFormatter_ = new ImportFormatter();

	public ImportFormatter() {
	}

	@Override
	public Option<Node> processOptionally(Script script, Node node) throws ParseException {
		return node.is(Node.Group.Import) ? Some(process(script, node)) : None();
	}

	public Node process(Script current, Node node) throws ParseException {
		return node.findScript()
				.map(imported -> processImpl(current, imported))
				.orElseThrow(() -> new ParseException("Import did not have a script."));
	}

	private Node processImpl(Script current, Script imported) {
		var importParent = resolve(current.listParent(), imported.listParent());
		var list = new ArrayList<>(importParent);
		list.add(imported.name() + ".h");
		var join = String.join("/", list);
		return Directives.Include.toNode("\"" + join + "\"");
	}

	List<String> resolve(List<String> from, List<String> to) {
		if (from.isEmpty() || to.isEmpty()) return Collections.emptyList();
		var ancestor = findAncestor(from, to);
		var paddingSize = from.size() - ancestor.size();
		var padding = createPadding(paddingSize);
		var target = to.subList(ancestor.size(), to.size());
		var list = new ArrayList<>(padding);
		list.addAll(target);
		return list;
	}

	private ArrayList<String> findAncestor(List<String> from, List<String> to) {
		var ancestor = new ArrayList<String>();
		var minSize = Math.min(from.size(), to.size());
		for (int i = 0; i < minSize; i++) {
			var fromItem = from.get(i);
			var toItem = to.get(i);
			if (fromItem.equals(toItem)) {
				ancestor.add(fromItem);
			}
		}
		return ancestor;
	}

	private ArrayList<String> createPadding(int paddingSize) {
		var padding = new ArrayList<String>();
		for (int i = 0; i < paddingSize; i++) {
			padding.add("..");
		}
		return padding;
	}
}