package com.meti.compile.feature.extern;

import com.meti.compile.Script;
import com.meti.compile.feature.Node;
import com.meti.compile.process.ProcessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImportProcessor implements ScriptProcessor {
	public static final ImportProcessor ImportProcessor_ = new ImportProcessor();

	public ImportProcessor() {
	}

	@Override
	public Node process(Script current, Node node) throws ProcessException {
		var targetScript = node.findScript();
		return targetScript.map(imported -> processImpl(current, imported))
				.orElseThrow(() -> new ProcessException("Import did not have a script."));
	}

	private Node processImpl(Script current, Script imported) {
		var importParent = resolve(current.listParent(), imported.listParent());
		var list = new ArrayList<>(importParent);
		list.add(imported.name() + ".h");
		var join = String.join("/", list);
		return Directive.Include.toNode("\"" + join + "\"");
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