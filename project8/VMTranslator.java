import java.io.File;
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
			fileName = "StaticsTest";
		}
		else{
			fileName = args[0];
		}
		
		input = getInputFile(fileName);
		
		//input = Files.readAllLines(Paths.get(filePath));
			
		List<String> output = new ArrayList<String> ();
		
		if (!fileName.endsWith(".vm"))
			output.addAll(startCode(fileName));
		
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
//			else if (line.startsWith("function Sys.init")){
//				output.add("// " + line);
//				output.addAll(startCode());
//			}
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
		
		if (fileName.equals("StaticsTest") || fileName.equals("FibonacciElement")){
			filePath = fileName + "/" + fileName.replace(".vm", "") + ".asm";
		}
		else{
			filePath = fileName.replace(".vm", "") + ".asm";
		}
		
		FileWriter writer = new FileWriter(filePath); 
		for(String str: output) {
		  writer.write(str + "\n");
		}
		writer.close();
	}
	
	public static List<String> getInputFile(String filename) throws IOException{
		String filepath = new String();
		List<String> input = new ArrayList<String>();
		
		if (filename.endsWith(".vm")){
			//filename = filename.replace(".vm", "");
			//filepath = "C:/Users/User/Documents/Nand2Tetris/nand2tetris/projects/08/ProgramFlow/" + filename + "/" + filename + ".vm";
			//filePath = fileName + ".vm";
			input = Files.readAllLines(Paths.get(filename));
			CurrentClassName = filename;
		}
		else{
			//filepath = "C:/Users/User/Documents/Nand2Tetris/nand2tetris/projects/08/FunctionCalls/" + filename;
			filepath = filename;
			File folder = new File(filepath);
			File[] listOfFiles = folder.listFiles();
			
			input.add("//Sys.vm");
			input = Files.readAllLines(Paths.get(filepath + "/Sys.vm"));
			CurrentClassName = "Sys";
			
			for (File file : listOfFiles) {
			    if (file.getName().endsWith(".vm") && file.getName()!="Sys.vm") {
			        input.add("//" + file.getName());
			    	input.addAll(Files.readAllLines(Paths.get(filepath + "/" + file.getName())));
			    }
			}
		}
		
		return input;
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
	
	public static List<String> startCode(String filename) {
		
		List<String> output = new ArrayList<String>();
		
		if (filename.equals("StaticsTest") || filename.equals("FibonacciElement")){
			output.add("@261");
		}
		else{
			output.add("@256");
		}
		
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
		
		if (input.startsWith("add"))
			command.addAll(add());
		if (input.startsWith("sub"))
			command.addAll(sub());
		if (input.startsWith("neg"))
			command.addAll(neg());
		if (input.startsWith("eq"))
			command.addAll(eq());
		if (input.startsWith("gt"))
			command.addAll(gt());
		if (input.startsWith("lt"))
			command.addAll(lt());
		if (input.startsWith("and"))
			command.addAll(and());
		if (input.startsWith("or"))
			command.addAll(or());
		if (input.startsWith("not"))
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
				command.add("@" + CurrentClassName + "." + parsedLine[2]);
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
				command.add("@" + CurrentClassName + "." + parsedLine[2]);
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
			command.add("D;JNE");
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
	public static String CurrentClassName;
	
	public static List<String> parseFunction (String line){
		List<String> command = new ArrayList<String> ();
		String[] parsedLine = line.split(" ");
		CurrentClassName = parsedLine[1].replace(".set", "").replace(".get", "");
		
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
		
		command.add("@SP");			//Store return value to temp 5
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("@10");
		command.add("M=D");
		
		command.add("@ARG");		//Store return location (functions ARG) to temp 6
		command.add("D=M");
		command.add("@11");
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
		
		command.add("@SP");			// Store return address in temp
		command.add("M=M-1");
		command.add("A=M");
		command.add("D=M");
		command.add("@12");
		command.add("M=D");
		
		command.add("@10");			//Take the return value (stored in temp 0) and store it at end of caller stack (location stored in temp 1)
		command.add("D=M");
		command.add("@11");
		command.add("A=M");
		command.add("M=D");
		command.add("@11");
		command.add("D=M+1");
		command.add("@SP");
		command.add("M=D");
		
		command.add("@12");			// Reset the sequence of commands
		//command.add("M=M+1");
		command.add("A=M");
		//command.add("A=M");
		command.add("0;JEQ");
		
		//command.addAll(parseGoTo("goto RETURN" + functionCallNum));
		
		return command;
	}

}
