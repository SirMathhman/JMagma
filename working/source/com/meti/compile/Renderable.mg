const Renderable = class {
    final in const render : () => String
}

const Node = Renderable >> class {
    final in const ??? : (Group) => Bool,
    final in const mapByMembers = (mapping : Field => Field) => this;
    final in const mapByIdentity = (mapping : Field => Field) => this;
    final in const mapByChild = (mapping : Node => Node) => this;
    final in const mapValue = [T](const function : Any => T) => throw StateException("No value found.");
}, {
    enum Group {
        Implementation,
        Declaration,
        Assignment,
        Structure,
        Variable,
        Mapping,
        Content,
        Return,
        Import,
        Block,
        While,
        False,
        True,
        Char,
        Int,
        If
    }
    out const Renderable = Renderable();
}