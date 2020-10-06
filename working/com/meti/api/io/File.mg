import *.extern.StandardIO;
import *.Input;

struct Path {
    const parent : Path*,
    const name : String,
    const format : String,
    const asAbsolute : () => Path,
    implicit const toString : () => String
}

struct File {
    const read : [T](Input => T) => T;
}

def File(path : Path) => File {
    read = [T](action : Input => T) => {
        const file = fopen(path.asAbsolute.toString, "r");
        const input = Input {
            read = () => fgetc(file);
        }
        const result = action(input);
        fclose(file);
        result
    }
}