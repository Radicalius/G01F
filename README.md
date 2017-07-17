# G01F
8-bit Stack Based Assembly-Like Language for Code Golf
## Syntax
Each instruction is either a command or an immediate.  If the most significant bit is 1, the instruction is interpreted as an immediate.
Otherwise, it is interpreted as a command.  This leaves room for 127 instructions, and 7-bit two's compliment signed immediates on the interval [-64,63].<br><br>
Immediate values are pushed to the stack.  Commands pop their operands from the stack, and push their results back to the stack.
For Example:
```
2            # immediate value: adds 2 to the stack
2            # stack [2,2]
add          # pops from the stack twice, adds values, and pushes result to stack
echo         # pops 4 from stack and prints it
```
## Sample Programs
### Hello World (helloworld.g)
```
# print Hello World!
0
72
101
108
108
111
032
087
111
114
108
100
033
print

# short hand:
'Hello World!'
print
```
### Fibonacci (fib.g)
```
# fibonacci
'Fibonnacci'         
print            # Print Header
1                # Initial Values
1
ditto            # Copy for printing
echo             # print current fib nu,
ditto2           # copy two previous fibonnacci nums
add              # take the sum to find the next one
ditto            # Copy the next num for comparison
1000
gt               # See if its greater than 1000
3                
if               # if it is, skip ahead three lines to the nop
-10              
jump             # otherwise, jump back 10 lines to the top of the loop
nop              # end program
```
### Hailstone (hailstone.g)
```
# prints hailstone sequence from given starting point
'Input Starting Value'       
print
inp                     # take input for starting value
ditto                   # copy for modulus
2
mod                     # see if its divisible by 2
5
if                      # if it is, jump ahead 5 lines to 3
2
div                     # otherwise, divide the number by two
5
jump                    # and then skip over the else case
3
mul                     # if its not divisble by two, multiply by three
1
add                     # and add 1
ditto                   # copy for printing
echo                    # print current hailstone number
ditto                   # copy for comparison
1                       
neq                     # see if its equal to 1
-19
if                      # if its not, jump back to the top of the loop
```
## Commands
### Arithmetic
All Arithmetic Commands take two arguments.
- Add (Addition)
- Sub (Subtraction)
- Mul (Multiplication)
- Div (Division)
- Mod (Modulus)
### Bitwise
Also take two arguments
- And
- Or
- Not (note: takes 1 argument)
- Xor
### Input/Output
- Inp: takes an integer from stdin and pushes to stack
- Echo: pops top of stack and prints
- Print: pops from stack and prints character values until it reaches a zero
- String Literal: any group of characters inside single quotes (').  Each character is pushed to the stack
### Comparison
Take two arguments and compare them.  Output 1 on true and 0 on false
- Eq (Equal)
- Neq (Not Equal)
- Gt (Greater Than)
- Lt (Less Than)
### Control
Immediates for control commands are counted from the control command itself
- Jump: moves the program counter by the value on top of the stack
- If: moves the program counter by the value on top of the stack if the value second on the stack is 1
### Miscellaneous
- Nop: does nothing; useful as a placeholder
- Ditto: Duplicate the top of the stack
- Ditto2: Duplicate first 2 elements of the stack
- Flop: swaps 1st two elements in the stack

