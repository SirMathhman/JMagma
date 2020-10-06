import native stdio;

native type FILE;

native def fopen(const filename : I8*, const mode : I8*) : FILE*;
native def fclose(fp : FILE*) : I16;

native def fgetc(const fp : FILE*) => I16;