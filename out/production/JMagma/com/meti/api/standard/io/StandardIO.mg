import native stdio;

native type FILE;

native def fopen(format : Ref[I8], mode : Ref[I8]) : Ref[FILE];
native def fclose(self : Ref[FILE]);

native def fgetc(self : Ref[FILE]) : I16;
native def feof(self : Ref[FILE]) : Bool;