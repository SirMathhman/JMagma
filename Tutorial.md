# Tutorial

## Primitives

Magma supports several primitive types, most of which are commonalities amongst processors.

### Numbers

Magma supports both integer and floating-point numbers. If a numbers sign is not specified, it is assumed to be a signed
16-bit integer, ***I16***.

#### Integers

Integers may be either unsigned or signed, as well as ranging from 8 to 64 bits.

`[bits]` indicates the number of bits belonging to the type.

- Signed integers have value from `-(2 ^[bits - 1] - 1)` to `2 ^ [bits - 1] - 1`
- Unsigned integers have values from `0` to `2 ^ [bits] - 1`

| Bits | Signed | Unsigned |
|:----:|:------:|:--------:|
|   8  |   I8   |    U8    |
|  16  |   I16  |    U16   |
|  32  |   I32  |    U32   |
|  64  |   I64  |    U64   |

Values are written as a series of digits, with an optional series of alphanumeric characters to explicitly set the
integer type.

- `420` has a type of ***I16***
- `420u` has a type of ***U16***
- `420u64` has a type of ***U64***

Additionally, the base of the value can be specified as `[base]x[value]`. For instance,

- `16xFF` is `255` in decimal
- `8x10` is `8` in decimal

...and so on and so forth.

Items that are ambiguously variables must be annotated with a base in order for the compiler to recognize them as
numbers, e.g. `CAB` could be the legitimate name of a constant value or a variable, and ***is assumed to be a
variable***, which may be interpreted as hexadecimal by prefixing the value with `16x` to create `16xCAB`.

#### Floating-Points

Magma supports traditional floats and doubles, where both types require 32 and 64 bits, respectively. A value is
indicated to be a floating-point value if a decimal point `.` is present and that there are trailing digits. The value
does not require a trailing letter to indicate whether the number is a float `f` or double `d`. If there is no trailing
letter, then the value is assumed to be a double.

- `10.0` is a double
- `8.4f` is a float
- `321.5d` is a double

### Booleans

Unlike C, Magma supports an explicit `Bool` type to encourage implementations to only utilize one bit, as opposed to C
which represents Booleans as a signed integer. Moreover, `true` and `false` are constants in the language.

### Characters

Characters may be represented using a leading and trailing quote `'`, e.g. `'x'`. Characters automatically have the type
of `I8`. Because Magma not only compiles to, but is completely interoptable with the C language, Magma requires that
characters have this type because characters in C are designed using the `char` type, which is represented in Magma
as `I8`, despite there existing more efficient representations of characters, e.g. does it make sense for characters to
be signed?

## Declarations

Declarations instruct the computer to allocate space in memory ***on the stack*** for variables. A declaration requires
a type, name, and flag indicating the mutability of the variable. A declaration ***may not*** omit the mutability flag
for the statement would be recognized as an assignment instead. Declarations may also have an additional, optional,
value to set the value of the variable when it is created. The syntax for declaration is as follows:

`[flags] [name] : [type] = [optional value];`

- To indicate a mutable variable, use the flag `let`
- To indiciate an immutable variable, use the flag `const`

Note that there are also more flags available to declarations that will be discussed lower in the document. Variables
may be named any combination of characters as long as the following criteria is satisfied:

- Does not start with a number (because otherwise it would be recognized as one)
- Does not contain a question mark `?` (this is used in special notation in lambda statements)
- Does not contain an equals sign `=`
- Does not equal any of the keywords (see Appendix 1)

A basic declaration may be as follows:

`const x : I16 = 420`

Here, a variable with the name of `x` is created with an initial value of `420` and a type of `I16`. Because of
the `const` flag, the variable is immutable and cannot be reassigned. Note the trailing semicolon. However, the default
type of numbers lacking either indicates or decimal points is an integer with 16 bits, `I16`. Consequently, it is
permissible to omit `I16` and rather implicitly realize thet type from the default value.

`const x = 420;`

However, declarations lacking both types and default values are not allowed for one depends on the other. Consequently,
a statement such as `const x;` is not permissible because the type cannot be determined from the indicators given.

## Assignments

Using the previous section, variable may be reassigned values if permitted to, through the inclusion of the `let` flag.
Let us assume that there is a declaration available to us that has that criterion.

`let x = 2021;`

Remember that the lack of indicates on the number causes the number to be interpreted as a type of `I16`. Other than the
variable being potentially immutable from the `const` keyword, in Magma, the type of variables remains constant and
cannot be changed. To use our variable from before:

```magma
let x = 2021;
x = 'a';
```

Here, the code wouldn't compile because the type of x, `I16`, cannot be reassigned to `I8`.

## Operators

Magma has a wide variety of operators available to manipulate data, as well as the ability to define custom operators
which will be discussed later. Many of these operators carry over from the C language, however some have changed and
others simply don't exist.

### Relational

Relational operators are imported from C.

- `==` equality
- `!=` inequality
- `<` less than
- `<=` less than or equals to
- `>` greater than
- `>=` greater than or equals to

### Logical

Logical operators are imported from C.

- `&&` Boolean AND
- `||` Boolean OR
- `!` Boolean negation

One more operator is present, which doesn't exist in C.

- `->` Boolean implies

### Bitwise Operators

Bitwise operators are imported from C.

- `<<` Binary left shift
- `>>` Binary right shift
- `~` Binary ones complement
- `&` Binary AND
- `^` Binary XOR
- `|` Binary OR

Magma does not support pre-increment and post-increment to promote immutability, however they can be mimicked
using `+= 1`. Operators may have a trailing `=` to indicate that an assignment is also being performed.

## Blocks

Code is arranged in blocks to organize instructions.

```magma
{
    let x = 420;
    x = 2021;
}
{
    let y = 420;
    y = 2021;
}
```

## Comments

Comments may be either single line or multi-line.

Single line comments are prefixed with `//`: `//This is a comment.`

Multi-line comments start with `/*` and end with `*/`. There is no need for a `*` in intermediate lines.

```magma
/*
This is a block of comments.
It has two sentences.
*/
```

## Control Flow

The majority of common control flow features in other languages are present in Magma, in addition to others that may
allow for more complex conditions.

### If

If statements execute a block of code when a condition is met. An if statement is indicated using the `if` keyword, and
the presence of a Boolean condition wrapped by two parameters. For example:

```magma
if(true){
    //instructions occur here always
}
```

If a single instruction is present, the braces may be ommitted. This is a recurrent pattern that will follow to later
features.

```magma
let x = 420;
if(true) x = 2021;
```

If the condition is not true, the `else` keyword is used. The block can also omit braces if there's only one
instruction.

```magma
if(false){
    //this never happens
} else {
    //this always happens
}
```

Instead of ternary statements, Magma allows for if-else statements to be better representation. Standalone if statements
are not sufficient to be evaluated.

```magma
let x = if(true) 2020 else 2021;
```

Magma also posseses the `elif` keyword to simplify cases in which there are multiple conditions.

```magma
let x = true;
if(x == 3){
    //first case
} elif(x == 2){
    //second case
} else {
    //third case
}
```

### While

While statements invokes the body continuously as long as a certain condition is met. Similar to if statements, the body
may omit braces if only one line is present.

```magma
while(true){
    //this runs forever
}
```

Unlike other languages, Magma supports the usage of an else block following the while statement, and is executed if the
condition is never met at the beginning.

```magma
while(false){
    //this never happens
} else {
    //this happens
}
```

### For

Similar to other C family languages, 


### Switch

## Functions

## Structures

## Unions

## Pointers