struct BracketSplitter {
	struct Stream_String (*stream)(void*, struct String);
	struct Splitter (*Splitter)(void*);
};

struct process_BracketSplitter {
	struct BracketSplitter* this;
};

struct SplitterState process_BracketSplitter(void* self, struct SplitterState state, char c) {
	struct BracketSplitter* this;
	struct SplitterState __return__;
	if(c == '}' && state.isShallow(&state)){
		struct SplitterState arg0 = state.reset(&state);
		struct SplitterState arg1 = arg0.append(&arg0, '}');
		__return__ = arg1.advance(&arg1);
	} else if(c == ';' && state.isLevel()) {
		__return__ = state.advance(&state);
	} else if(c == '{') {
		struct SplitterState arg0 = state.sink(&state);
		__return__ = arg0.append(&arg0, c);
	} else if(c == '}') {
		struct SplitterState arg1 = state.surface(&state);
		__return__ = arg1.append(&arg1, c);
	} else {
		__return__ = state.append(&state, c);
	}
	return __return__;
}

struct __lambda0__stream_BracketSplitter {
	struct BracketSplitter* this;
	struct String content;
};

char __lambda0__BracketSplitter(void* self, int arg0) {
	struct __lambda0__stream_BracketSplitter* this = self;
	struct String arg1 = *(this).content;
	return arg1.content.apply(&arg1, arg0);
}

struct Stream_String stream_BracketSplitter(void* self, struct String content) {
	struct BracketSplitter* this = self;
	struct Streams arg0 = Streams_();
	struct Stream_I16 arg1 = arg0.ofIntRange(&arg0, 0, content.length(&content));
	struct __lambda0__stream_BracketSplitter __lambda0__;
	__lambda0__.this = this;
	__lambda0__.content = content;
	struct Stream_I8 arg2 = arg1.map(&arg1, __lambda0__, __lambda0__BracketSplitter);
	struct SplitterStates SplitterStates_INSTANCE = SplitterStates_();
	struct process_BracketSplitter __lambda1__;
    __lambda1__.this = this;
	struct SplitterState arg3 = arg2.fold(&arg2, SplitterStates_INSTANCE.empty(&SplitterStates_INSTANCE), __lambda1__, process_BracketSplitter);
	struct SplitterState arg4 = arg3.advance(&arg3);
	return arg4.stream(&arg4);
}

struct Splitter Splitter_BracketSplitter(void* self){
	struct BracketSplitter* this = self;
	return Splitter_(this.stream);
}

struct BracketSplitter BracketSplitter_(){
	struct BracketSplitter this;
	this.stream = stream_BracketSplitter;
	this.Splitter = Splitter_BracketSplitter;
	return this;
}