object BracketSplitter {
	def process(state : SplitterState, c : I8) =>
		if(c == '}' && state.isShallow()) state.reset().append('}').advance();
		elif(c == ';' && state.isLevel()) state.advance();
		elif(c == '{') state.sink().append(c);
		elif(c == '}') state.surface().append(c);
		else state.append(c);

	const stream = (content : String) =>
		Streams.ofIntRange(0, content.length())
			.map(content(_))
			.fold(SplitterStates.empty, process)
			.advance()
			.stream();

	out const Splitter = () => Splitter();
}