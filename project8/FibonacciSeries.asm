// push argument 1
@1
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 1           // that = argument[1]
@SP
A=M-1
D=M
@THAT
M=D
@SP
A=M
M=0
@SP
M=M-1
// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop that 0              // first element in the series = 0
@0
D=A
@SP
A=M
M=D
@THAT
D=M
@SP
A=M
M=D+M
@SP
A=M-1
D=M
@SP
A=M
A=M
M=D
@SP
A=M
M=0
@SP
M=M-1
// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop that 1              // second element in the series = 1
@1
D=A
@SP
A=M
M=D
@THAT
D=M
@SP
A=M
M=D+M
@SP
A=M-1
D=M
@SP
A=M
A=M
M=D
@SP
A=M
M=0
@SP
M=M-1
// push argument 0
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M-D
// pop argument 0          // num_of_elements -= 2 (first 2 elements are set)
@0
D=A
@SP
A=M
M=D
@ARG
D=M
@SP
A=M
M=D+M
@SP
A=M-1
D=M
@SP
A=M
A=M
M=D
@SP
A=M
M=0
@SP
M=M-1
// label MAIN_LOOP_START
(MAIN_LOOP_START)
// push argument 0
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// if-goto COMPUTE_ELEMENT // if num_of_elements > 0, goto COMPUTE_ELEMENT
@SP
M=M-1
A=M
D=M
@COMPUTE_ELEMENT
D;JNE
// goto END_PROGRAM        // otherwise, goto END_PROGRAM
@END_PROGRAM
0;JEQ
// label COMPUTE_ELEMENT
(COMPUTE_ELEMENT)
// push that 0
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push that 1
@1
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// pop that 2              // that[2] = that[0] + that[1]
@2
D=A
@SP
A=M
M=D
@THAT
D=M
@SP
A=M
M=D+M
@SP
A=M-1
D=M
@SP
A=M
A=M
M=D
@SP
A=M
M=0
@SP
M=M-1
// push pointer 1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// pop pointer 1           // that += 1
@SP
A=M-1
D=M
@THAT
M=D
@SP
A=M
M=0
@SP
M=M-1
// push argument 0
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M-D
// pop argument 0          // num_of_elements--
@0
D=A
@SP
A=M
M=D
@ARG
D=M
@SP
A=M
M=D+M
@SP
A=M-1
D=M
@SP
A=M
A=M
M=D
@SP
A=M
M=0
@SP
M=M-1
// goto MAIN_LOOP_START
@MAIN_LOOP_START
0;JEQ
// label END_PROGRAM
(END_PROGRAM)
(END)
@END
0;JMP
