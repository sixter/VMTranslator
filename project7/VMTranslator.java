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
		
		
		String fileName = new String();
		String filePath = new String();
		List<String> input = new ArrayList<String>();
		if (args.length == 0){
			fileName = "StackTest";
		}
		else{
			fileName = args[0].replace(".vm", "");
		}
		//filePath = "C:/Users/User/Documents/Nand2Tetris/nand2tetris/projects/07/StackArithmetic/" + fileName + "/" + fileName + ".vm";
		filePath = fileName + ".vm";
		input = Files.readAllLines(Paths.get(filePath));
			
		List<String> output = new ArrayList<String> ();
		
		output.addAll(startCode());
		
		for (String line : input){
			if (line.length() == 0 || line.startsWith("//"))
				continue;
			
			if (line.startsWith("push") || line.startsWith("pop")){
				output.add("// " + line);
				output.addAll(getMemoryCommand(line));
			}
			
			if (line.startsWith("add") || line.startsWith("sub") || line.startsWith("neg") || 
					line.startsWith("eq") || line.startsWith("gt") || line.startsWith("lt") || 
					line.startsWith("and") || line.startsWith("or") || line.startsWith("not")){
				output.add("// " + line);
				output.addAll(getArithmeticCommand(line));
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
}
