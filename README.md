# G01F
8-bit Stack Based Assembly Language for Code Golf
## Syntax
Each instruction is either a command or an immediate.  If the most significant bit is 1, the instruction is interpreted as an immediate.
Otherwise, it is interpreted as a command.  This leaves room for 127 instructions, and two's compliment signed immediates in the range of [-64,63].<br><br>
Immediate values are pushed to the stack.  Commands pop their operands from the stack, and push their results back to the stack.<br>
Example:
```
2            # immediate value: adds 2 to the stack
2            # stack [2,2]
add          # pops from the stack twice, adds values, and pushes result to stack
echo         # pops 4 from stack and prints it
```
## Commands
### Arithmetic
All Arithmetic Commands take two arguments.
- Add (Addition)
- Sub (Subtraction)
- Mul (Multiplication)
- Div (Division)
- Mod (Modulus)
### Input/Output
- Inp: takes an integer from stdin and pushes to stack
- Echo: pops top of stack and prints
- Print: pops from stack and prints character values until it reaches a zero
- String Literal: any group of characters inside single quotes (').  Each character is pushed to the stack
### Comparison
Take two arguments and compare them.  Output 1 on true and 0 on false
- eq (EqualE
- neq (Not Equal)
- gt (Greater Than)
- lt (Less Than)
### Control
- Jump: moves the program counter by the value on top of the stack
- If: moves the program counter by the value on top of the stack if the value second on the stack is 1
### Miscellaneous
- Ditto: Duplicate the top of the stack
- Ditto2: Duplicate first 2 elements of the stack

