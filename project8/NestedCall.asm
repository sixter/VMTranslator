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
// function Sys.init 0
(Sys.init)
// push constant 4000	// test THIS and THAT context save
@4000	//
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
// push constant 5000
@5000
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
// call Sys.main 0
@RETURN1
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.main
0;JEQ
(RETURN1)
// pop temp 1
@1
D=A
@SP
A=M
M=D
@5
D=A
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
// label LOOP
(LOOP)
// goto LOOP
@LOOP
0;JEQ
// function Sys.main 5
(Sys.main)
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
// push constant 4001
@4001
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
// push constant 5001
@5001
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
// push constant 200
@200
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 1
@1
D=A
@SP
A=M
M=D
@LCL
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
// push constant 40
@40
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 2
@2
D=A
@SP
A=M
M=D
@LCL
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
// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 3
@3
D=A
@SP
A=M
M=D
@LCL
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
// push constant 123
@123
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Sys.add12 1
@RETURN2
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
D=M
@1
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.add12
0;JEQ
(RETURN2)
// pop temp 0
@0
D=A
@SP
A=M
M=D
@5
D=A
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
// push local 0
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 2
@2
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 3
@3
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 4
@4
D=A
@LCL
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
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// return
@SP
M=M-1
A=M
D=M
@10
M=D
@ARG
D=M
@11
M=D
@LCL
D=M
@SP
M=D
@SP
M=M-1
A=M
D=M
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THIS
M=D
@SP
M=M-1
A=M
D=M
@ARG
M=D
@SP
M=M-1
A=M
D=M
@LCL
M=D
@SP
M=M-1
A=M
D=M
@12
M=D
@10
D=M
@11
A=M
M=D
@11
D=M+1
@SP
M=D
@12
A=M
0;JEQ
// function Sys.add12 0
(Sys.add12)
// push constant 4002
@4002
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
// push constant 5002
@5002
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
// push constant 12
@12
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
// return
@SP
M=M-1
A=M
D=M
@10
M=D
@ARG
D=M
@11
M=D
@LCL
D=M
@SP
M=D
@SP
M=M-1
A=M
D=M
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THIS
M=D
@SP
M=M-1
A=M
D=M
@ARG
M=D
@SP
M=M-1
A=M
D=M
@LCL
M=D
@SP
M=M-1
A=M
D=M
@12
M=D
@10
D=M
@11
A=M
M=D
@11
D=M+1
@SP
M=D
@12
A=M
0;JEQ
// function Sys.init 0
(Sys.init)
// push constant 4000	// test THIS and THAT context save
@4000	//
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
// push constant 5000
@5000
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
// call Sys.main 0
@RETURN3
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.main
0;JEQ
(RETURN3)
// pop temp 1
@1
D=A
@SP
A=M
M=D
@5
D=A
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
// label LOOP
(LOOP)
// goto LOOP
@LOOP
0;JEQ
// function Sys.main 5
(Sys.main)
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1
// push constant 4001
@4001
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
// push constant 5001
@5001
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
// push constant 200
@200
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 1
@1
D=A
@SP
A=M
M=D
@LCL
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
// push constant 40
@40
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 2
@2
D=A
@SP
A=M
M=D
@LCL
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
// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 3
@3
D=A
@SP
A=M
M=D
@LCL
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
// push constant 123
@123
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Sys.add12 1
@RETURN4
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
D=M
@1
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.add12
0;JEQ
(RETURN4)
// pop temp 0
@0
D=A
@SP
A=M
M=D
@5
D=A
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
// push local 0
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 2
@2
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 3
@3
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 4
@4
D=A
@LCL
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
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// add
@SP
M=M-1
A=M
D=M
M=0
@SP
A=M-1
M=M+D
// return
@SP
M=M-1
A=M
D=M
@10
M=D
@ARG
D=M
@11
M=D
@LCL
D=M
@SP
M=D
@SP
M=M-1
A=M
D=M
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THIS
M=D
@SP
M=M-1
A=M
D=M
@ARG
M=D
@SP
M=M-1
A=M
D=M
@LCL
M=D
@SP
M=M-1
A=M
D=M
@12
M=D
@10
D=M
@11
A=M
M=D
@11
D=M+1
@SP
M=D
@12
A=M
0;JEQ
// function Sys.add12 0
(Sys.add12)
// push constant 4002
@4002
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
// push constant 5002
@5002
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
// push constant 12
@12
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
// return
@SP
M=M-1
A=M
D=M
@10
M=D
@ARG
D=M
@11
M=D
@LCL
D=M
@SP
M=D
@SP
M=M-1
A=M
D=M
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THIS
M=D
@SP
M=M-1
A=M
D=M
@ARG
M=D
@SP
M=M-1
A=M
D=M
@LCL
M=D
@SP
M=M-1
A=M
D=M
@12
M=D
@10
D=M
@11
A=M
M=D
@11
D=M+1
@SP
M=D
@12
A=M
0;JEQ
(END)
@END
0;JMP
