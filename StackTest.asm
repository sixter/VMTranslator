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
// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=M-D
@COMPARE0
D;JEQ
@SP
A=M-1
M=0
@END0
0;JMP
(COMPARE0)
@SP
A=M-1
M=-1
(END0)
// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=M-D
@COMPARE1
D;JEQ
@SP
A=M-1
M=0
@END1
0;JMP
(COMPARE1)
@SP
A=M-1
M=-1
(END1)
// push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=M-D
@COMPARE2
D;JEQ
@SP
A=M-1
M=0
@END2
0;JMP
(COMPARE2)
@SP
A=M-1
M=-1
(END2)
// push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// lt
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=D-M
@COMPARE3
D;JGT
@SP
A=M-1
M=0
@END3
0;JMP
(COMPARE3)
@SP
A=M-1
M=-1
(END3)
// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
// lt
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=D-M
@COMPARE4
D;JGT
@SP
A=M-1
M=0
@END4
0;JMP
(COMPARE4)
@SP
A=M-1
M=-1
(END4)
// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// lt
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=D-M
@COMPARE5
D;JGT
@SP
A=M-1
M=0
@END5
0;JMP
(COMPARE5)
@SP
A=M-1
M=-1
(END5)
// push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=M-D
@COMPARE6
D;JGT
@SP
A=M-1
M=0
@END6
0;JMP
(COMPARE6)
@SP
A=M-1
M=-1
(END6)
// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=M-D
@COMPARE7
D;JGT
@SP
A=M-1
M=0
@END7
0;JMP
(COMPARE7)
@SP
A=M-1
M=-1
(END7)
// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
D=M-D
@COMPARE8
D;JGT
@SP
A=M-1
M=0
@END8
0;JMP
(COMPARE8)
@SP
A=M-1
M=-1
(END8)
// push constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 53
@53
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
// push constant 112
@112
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
// neg
@SP
A=M-1
M=-M
// and
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M&D
// push constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
// or
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M|D
// not
@SP
A=M-1
M=!M
(END)
@END
0;JMP