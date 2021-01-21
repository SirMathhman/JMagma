package com.meti.compile.stage;

import com.meti.compile.stack.Stack;
import com.meti.compile.token.Token;

import java.util.List;

public record StageState(Stack stack, List<Token> nodes) {
}
