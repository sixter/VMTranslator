package VMTranslator;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VMTranslator {
	
	public static int sp = 256;
	
	public static void main(String args[]) throws IOException{
		
		
		//functionCallNum = 0;
		String fileName = new String();
		String filePath = new String();
		List<String> input = new ArrayList<String>();
		if (args.length == 0){
			fileName = "NestedCall";
		}
		else{
			fileName = args[0].replace(".vm", "");
		}
		filePath = "C:/Users/User/Documents/Nand2Tetris/nand2tetris/projects/08/FunctionCalls/" + fileName + "/Sys.vm";
		//filePath = fileName + "/" + fileName + ".vm";
		input = Files.readAllLines(Paths.get(filePath));
			
		List<String> output = new ArrayList<String> ();
		
		//output.addAll(startCode());
		
		for (String line : input){
			if (line.length() == 0 || line.startsWith("//"))
				continue;
			
			if (line.startsWith("push") || line.startsWith("pop")){
				output.add("// " + line);
				output.addAll(getMemoryCommand(line));
			}
			else if (line.startsWith("add") || line.startsWith("sub") || line.startsWith("neg") || 
					line.startsWith("eq") || line.startsWith("gt") || line.startsWith("lt") || 
					line.startsWith("and") || line.startsWith("or") || line.startsWith("not")){
				output.add("// " + line);
				output.addAll(getArithmeticCommand(line));
			}
			else if (line.startsWith("goto") || line.startsWith("if-goto")){
				output.add("// " + line);
				output.addAll(getGoToCommand(line));
			}
			else if (line.startsWith("label")){
				output.add("// " + line);
				output.addAll(getLabelCommand(line));
			}
			else if (line.startsWith("function Sys.init")){
				output.add("// " + line);
				output.addAll(startCode());
			}
			else if (line.startsWith("function")){
				output.add("// " + line);
				output.addAll(getFunctionCommand(line));
			}
			else if (line.startsWith("call")){
				output.add("// " + line);
				output.addAll(getCallCommand(line));
			}
			else if (line.startsWith("return")){
				output.add("// " + line);
				output.addAll(getReturnCommand(line));
			}
		}
		
		output.addAll(endCode());
		
		FileWriter writer = new FileWriter(fileName + ".asm"); 
		for(String str: output) {
		  writer.write(str + "\n");
		}
		writer.close();
	}
	
	public static List<String> getMemoryCommand(String input){
		List<String> command = new ArrayList<String> ();
		System.out.println("// "+input);
		//command.addAll(Memory.parseMemory(input));
		command.addAll(parseMemory(input));
		for (String line : command){
			System.out.println(line);
		}
		return command;
	}
	
	public static List<String> getArithmeticCommand(String input){
		List<String> command = new ArrayList<String> ();
		System.out.println("// "+input);
		//command.addAll(Arithmetic.parseArithmetic(input));
		command.addAll(parseArithmetic(input));
		for (String line : command){
			System.out.println(line);
		}
		return command;
	}
	
	// Branching commands
	
	public static List<String> getGoToCommand (String input){
		List<String> command = new ArrayList<String> ();
		System.out.println("// "+input);
		command.addAll(parseGoTo(input));
		for (String line : command){
			System.out.println(line);
		}
		return command;
	}
	
	public static List<String> getLabelCommand (String input){
		List<String> command = new ArrayList<String> ();
		System.out.println("// "+input);
		command.addAll(parseLabel(input));
		for (String line : command){
			System.out.println(line);
		}
		return command;
	}
	
	// Function commands
	
	public static List<String> getFunctionCommand (String input){
		List<String> command = new ArrayList<String> ();
		System.out.println("// "+input);
		command.addAll(parseFunction(input));
		for (String line : command){
			System.out.println(line);
		}
		return command;
	}
	
	public static List<String> getCallCommand (String input){
		List<String> command = new ArrayList<String> ();
		System.out.println("// "+input);
		command.addAll(parseCall(input));
		for (String line : command){
			System.out.println(line);
		}
		return command;
	}
	
	public static List<String> getReturnCommand (String input){
		List<String> command = new ArrayList<String> ();
		System.out.println("// "+input);
		command.addAll(parseReturn(input));
		for (String line : command){
			System.out.println(line);
		}
		return command;
	}
	
	public static List<String> startCode() {
		
		List<String> output = new ArrayList<String>();
		
		output.add("@256");
		output.add("D=A");
		output.add("@SP");
		output.add("M=D");
		
		output.add("@300");
		output.add("D=A");
		output.add("@LCL");
		output.add("M=D");
		
		output.add("@400");
		output.add("D=A");
		output.add("@ARG");
		output.add("M=D");
		
		output.add("@3000");
		output.add("D=A");
		output.add("@THIS");
		output.add("M=D");
		
		output.add("@3010");
		output.add("D=A");
		output.add("@THAT");
		output.add("M=D");
		
		return output;
	}
	
	public static List<String> endCode() {
		
		List<String> output = new ArrayList<String>();
		
		output.add("(END)");
		output.add("@END");
		output.add("0;JMP");
		
		return output;
	}
	
	// Arithmetic
	public static List<String> parseArithmetic(String input){
		List<String> command = new ArrayList<String> ();
		
		if (input.equals("add"))
			command.addAll(add());
		if (input.equals("sub"))
			command.addAll(sub());
		if (input.equals("neg"))
			command.addAll(neg());
		if (input.equals("eq"))
			command.addAll(eq());
		if (input.equals("gt"))
			command.addAll(gt());
		if (input.equals("lt"))
			command.addAll(lt());
		if (input.equals("and"))
			command.addAll(and());
		if (input.equals("or"))
			command.addAll(or());
		if (input.equals("not"))
			command.addAll(not());
		
		return command;
	}
	
	private static int x = 0;
	private static int y = 0;
	
	private static List<String> basicSetup(){					// Used for add, sub, and, or
		List<String> command = new ArrayList<String> ();
		
		command.add("@SP");
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("M=0");
		command.add("@SP");
		command.add("A=M-1");
		
		return command;
	}
	
	private static List<String> compareSetup(String eq, String comp){
		List<String> command = new ArrayList<String> ();
		
		command.add("@SP");
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("M=0");
		command.add("@SP");
		command.add("A=M-1");
		command.add("D=" + eq);
		command.add("@COMPARE" + x);
		command.add("D;" + comp);
		command.add("@SP");
		command.add("A=M-1");
		command.add("M=0");
		command.add("@END" + y);
		command.add("0;JMP");
		command.add("(COMPARE" + x +")");
		command.add("@SP");
		command.add("A=M-1");
		command.add("M=-1");
		command.add("(END" + y +")");
		
		x++;
		y++;
		
		return command;
	}
	
	private static List<String> add(){
		List<String> command = new ArrayList<String> ();
		
		command.addAll(basicSetup());
		command.add("M=M+D");
		
		return command;
	}
	
	private static List<String> sub(){
		List<String> command = new ArrayList<String> ();
		
		command.addAll(basicSetup());
		command.add("M=M-D");
		
		return command;
	}
	
	private static List<String> neg(){
		List<String> command = new ArrayList<String> ();
		
		command.add("@SP");
		command.add("A=M-1");
		command.add("M=-M");
		
		return command;
	}
	
	private static List<String> eq(){
		List<String> command = new ArrayList<String> ();
		
		command.addAll(compareSetup("M-D", "JEQ"));
		
		return command;
	}
	
	private static List<String> gt(){
		List<String> command = new ArrayList<String> ();
		
		command.addAll(compareSetup("M-D", "JGT"));
		
		return command;
	}
	
	private static List<String> lt(){
		List<String> command = new ArrayList<String> ();
		
		command.addAll(compareSetup("D-M", "JGT"));
		
		return command;
	}
	
	private static List<String> and(){
		List<String> command = new ArrayList<String> ();
		
		command.addAll(basicSetup());
		command.add("M=M&D");
		
		return command;
	}
	
	
	private static List<String> or(){
		List<String> command = new ArrayList<String> ();
		
		command.addAll(basicSetup());
		command.add("M=M|D");
		
		return command;
	}
	
	private static List<String> not(){
		List<String> command = new ArrayList<String> ();
		
		command.add("@SP");
		command.add("A=M-1");
		command.add("M=!M");
		
		return command;
	}
	
	// Memory
	
	public static List<String> parseMemory (String line){
		List<String> command = new ArrayList<String> ();
		String[] parsedLine = line.split(" ");
		
		if (parsedLine[0].equals("push")){			// push
			if (parsedLine[1].equals("constant")){										// constant
				command.add("@" + parsedLine[2]);
				command.add("D=A");
				command.addAll(setSPToD());
			}
			else if (parsedLine[1].equals("temp")){										// temp
				command.add("@5");
				command.add("D=A");
				command.add("@" + parsedLine[2]);
				command.add("A=D+A");
				command.addAll(setSPToM());
			}
			else if (parsedLine[1].equals("pointer") && parsedLine[2].equals("0")){		// pointer 0
				command.add("@THIS");
				command.addAll(setSPToM());
			}
			else if (parsedLine[1].equals("pointer") && parsedLine[2].equals("1")){		// pointer 1
				command.add("@THAT");
				command.addAll(setSPToM());
			}
			else if (parsedLine[1].equals("local") || parsedLine[1].equals("argument") || parsedLine[1].equals("this") || parsedLine[1].equals("that")){
				command.add("@" + parsedLine[2]);
				command.add("D=A");
				command.add("@" + RAMAddress.get(parsedLine[1]));
				command.add("A=D+M");
				command.addAll(setSPToM());
			}
			else if (parsedLine[1].equals("static")){									// static
				command.add("@StaticTest." + parsedLine[2]);
				//command.add("A=M");
				command.addAll(setSPToM());
			}
			command.addAll(addOneToSP());
		}
		else {									// pop
			if (parsedLine[1].equals("temp")){											// temp
				command.add("@"+parsedLine[2]);
				command.add("D=A");
				command.add("@SP");
				command.add("A=M");
				command.add("M=D");
				command.add("@5");
				command.add("D=A");
				command.add("@SP");
				command.add("A=M");
				command.add("M=D+M");
				command.add("@SP");
				command.add("A=M-1");
				command.add("D=M");
				command.add("@SP");
				command.add("A=M");
				command.add("A=M");
				command.addAll(setNewStack());
			}
			else if (parsedLine[1].equals("pointer") && parsedLine[2].equals("0")){		// pointer 0
				command.add("@SP");
				command.add("A=M-1");
				command.add("D=M");
				command.add("@THIS");
				command.addAll(setNewStack());
			}
			else if (parsedLine[1].equals("pointer") && parsedLine[2].equals("1")){		// pointer 1
				command.add("@SP");
				command.add("A=M-1");
				command.add("D=M");
				command.add("@THAT");
				command.addAll(setNewStack());
			}
			else if (parsedLine[1].equals("local") || parsedLine[1].equals("argument") || parsedLine[1].equals("this") || parsedLine[1].equals("that")){
				command.add("@"+parsedLine[2]);
				command.add("D=A");
				command.add("@SP");
				command.add("A=M");
				command.add("M=D");
				command.add("@" + RAMAddress.get(parsedLine[1]));
				command.add("D=M");
				command.add("@SP");
				command.add("A=M");
				command.add("M=D+M");
				command.add("@SP"); /// just added this
				command.add("A=M-1");
				command.add("D=M");
				command.add("@SP");
				command.add("A=M");
				command.add("A=M");
				command.addAll(setNewStack());
			}
			else if (parsedLine[1].equals("static")){									// static
				command.add("@SP");
				command.add("A=M-1");
				command.add("D=M");
				command.add("@StaticTest." + parsedLine[2]);
				//command.add("A=M");  // not sure if should be removed
				//command.add("A=M");  // not sure if should be removed
				command.addAll(setNewStack());
			}
		}
		
		return command;
	}

	private static List<String> setSPToD(){		// RAM[addr] = i;
		List<String> command = new ArrayList<String> ();
		
		command.add("@SP");
		command.add("A=M");
		command.add("M=D");
			
		return command;
	}
	
	private static List<String> setSPToM(){		// RAM[addr] = i;
		List<String> command = new ArrayList<String> ();
		
		command.add("D=M");
		command.addAll(setSPToD());
			
		return command;
	}
	
	private static List<String> setNewStack(){	// RAM[segment i]=RAM[SP];
		List<String> command = new ArrayList<String> ();
		
		command.add("M=D");
		command.add("@SP");
		command.add("A=M");
		command.add("M=0");
		command.add("@SP");
		command.add("M=M-1");
			
		return command;
	}
	
	private static List<String> addOneToSP(){					// SP++;
		List<String> command = new ArrayList<String> ();
		
		command.add("@SP");
		command.add("M=M+1");
			
		return command;
	}
	
	private static Map< String,String> RAMAddress = new HashMap< String,String>();
	static {
		RAMAddress.put("local", "LCL");
		RAMAddress.put("argument", "ARG");
		RAMAddress.put("this", "THIS");
		RAMAddress.put("that", "THAT");
	}
	
	// Pointers
	
	public static List<String> parseGoTo (String line){
		List<String> command = new ArrayList<String> ();
		String[] parsedLine = line.split(" ");
		
		if (parsedLine[0].equals("goto")){	
			command.add("@" + parsedLine[1]);
			command.add("0;JEQ");
		}
		else {									
			command.add("@SP");
			command.add("M=M-1");
			command.add("A=M");
			command.add("D=M");
			command.add("@" + parsedLine[1]);
			command.add("D;JGT");
		}
		
		return command;
	}
	
	public static List<String> parseLabel (String line){
		List<String> command = new ArrayList<String> ();
		String[] parsedLine = line.split(" ");
		
		command.add("(" + parsedLine[1] + ")");
		
		return command;
	}
	
	// Functions
	
	private static int functionCallNum;
	
	public static List<String> parseFunction (String line){
		List<String> command = new ArrayList<String> ();
		String[] parsedLine = line.split(" ");
		
		command.add("(" + parsedLine[1] + ")");
		int numLoc = Integer.parseInt(parsedLine[2]);
		while (numLoc > 0){
			command.add("@SP");
			command.add("A=M");
			command.add("M=0");
			command.addAll(addOneToSP());
			numLoc--;
		}
		
		return command;
	}
	
	public static List<String> parseCall (String line){
		List<String> command = new ArrayList<String> ();
		String[] parsedLine = line.split(" ");
		functionCallNum++;
		
		/*command.add("@SP");						//Return SP = Current SP - #Args
		command.add("D=M");
		command.add("@" + parsedLine[2]);
		command.add("D=D-A");
		command.addAll(setSPToD());
		command.addAll(addOneToSP());*/
		command.add("@RETURN" + functionCallNum);//Return SP = Current SP - #Args
		command.add("D=A");
		command.addAll(setSPToD());
		command.addAll(addOneToSP());
		
		
		command.add("@LCL");					//Return LCL = Current LCL
		command.add("D=M");
		command.addAll(setSPToD());
		command.addAll(addOneToSP());
		
		command.add("@ARG");					//Return ARG = Current ARG
		command.add("D=M");
		command.addAll(setSPToD());
		command.addAll(addOneToSP());
		
		command.add("@THIS");					//Return THIS = Current THIS
		command.add("D=M");
		command.addAll(setSPToD());
		command.addAll(addOneToSP());
		
		command.add("@THAT");					//Return THAT = Current THAT
		command.add("D=M");
		command.addAll(setSPToD());
		command.addAll(addOneToSP());
		
		command.add("@SP");						//New ARGs = SP-n-5
		command.add("D=M");
		command.add("@" + parsedLine[2]);
		command.add("D=D-A");
		command.add("@5");
		command.add("D=D-A");
		command.add("@ARG");
		command.add("M=D");
		
		command.add("@SP");						//New LCL = SP
		command.add("D=M");
		command.add("@LCL");
		command.add("M=D");
		
		command.addAll(parseGoTo("goto " + parsedLine[1]));
		command.add("(RETURN" + functionCallNum + ")");
		
		return command;
	}
	
	public static List<String> parseReturn (String line){
		List<String> command = new ArrayList<String> ();
		String[] parsedLine = line.split(" ");
		
		command.add("@SP");			//Store return value to temp 0
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("@5");
		command.add("M=D");
		
		command.add("@ARG");		//Store return location (functions ARG) to temp 1
		command.add("D=M");
		command.add("@6");
		command.add("M=D");
		
		command.add("@LCL");		// Set SP back to pre-call spot
		command.add("D=M");
		command.add("@SP");
		command.add("M=D");
		
		command.add("@SP");			// Reset THAT to the callers THAT location
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("@THAT");
		command.add("M=D");
		
		command.add("@SP");			// Reset THIS to the callers THIS location
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("@THIS");
		command.add("M=D");
		
		command.add("@SP");			// Reset ARG to the callers ARG location
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("@ARG");
		command.add("M=D");
		
		command.add("@SP");			// Reset LCL to the callers LCL location
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("@LCL");
		command.add("M=D");
		
		command.add("@SP");			// Reset the sequence of commands
		command.add("M=M-1");
		command.add("A=M");
		command.add("A=M");
		command.add("0;JEQ");
		
		command.add("@5");			//Take the return value (stored in temp 0) and store it at end of caller stack (location stored in temp 1)
		command.add("D=M");
		command.add("@6");
		command.add("A=M");
		command.add("M=D");
		command.add("@6");
		command.add("D=M+1");
		command.add("@SP");
		command.add("M=D");
		
		//command.addAll(parseGoTo("goto RETURN" + functionCallNum));
		
		return command;
	}

}
