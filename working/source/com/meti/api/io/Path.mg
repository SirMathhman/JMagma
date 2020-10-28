struct Path {
    in const resolveName : (String) => Path;
    in const resolvePath : (Path) => Path;
    in const exists : () => Boolean;
    in const createAsFile : () => Void ? IOException;
}

//TODO: insert StandardPath implementation