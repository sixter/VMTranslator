@256
D=A
@SP
M=D
@300
D=A
@LCL
M=D
@400
D=A
@ARG
M=D
@3000
D=A
@THIS
M=D
@3010
D=A
@THAT
M=D
// push constant 3030
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 0
@SP
A=M-1
D=M
@THIS
M=D
@SP
A=M
M=0
@SP
M=M-1
// push constant 3040
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 1
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
// push constant 32
@32
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop this 2
@2
D=A
@SP
A=M
M=D
@THIS
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
// push constant 46
@46
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop that 6
@6
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
// push pointer 0
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
// push pointer 1
@THAT
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
// push this 2
@2
D=A
@THIS
A=D+M
D=M
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
// push that 6
@6
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
(END)
@END
0;JMP
