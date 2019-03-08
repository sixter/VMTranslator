package VMTranslator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

public class Main {
	
//	public static int sp = 256;
//	
//	public static void main(String args[]) throws IOException{
//		
//		
//		String fileName = new String();
//		String filePath = new String();
//		List<String> input = new ArrayList<String>();
//		if (args.length == 0){
//			fileName = "StackTest";
//		}
//		else{
//			fileName = args[0];
//		}
//		//filePath = "C:/Users/User/Documents/Nand2Tetris/nand2tetris/projects/07/StackArithmetic/" + fileName + "/" + fileName + ".vm";
//		filePath = fileName + "/" + fileName + ".vm";
//		input = Files.readAllLines(Paths.get(filePath));
//			
//		List<String> output = new ArrayList<String> ();
//		
//		output.addAll(startCode());
//		
//		for (String line : input){
//			if (line.length() == 0 || line.startsWith("//"))
//				continue;
//			
//			if (line.startsWith("push") || line.startsWith("pop")){
//				output.add("// " + line);
//				output.addAll(getMemoryCommand(line));
//			}
//			
//			if (line.startsWith("add") || line.startsWith("sub") || line.startsWith("neg") || 
//					line.startsWith("eq") || line.startsWith("gt") || line.startsWith("lt") || 
//					line.startsWith("and") || line.startsWith("or") || line.startsWith("not")){
//				output.add("// " + line);
//				output.addAll(getArithmeticCommand(line));
//			}	
//		}
//		
//		output.addAll(endCode());
//		
//		FileWriter writer = new FileWriter(fileName + ".asm"); 
//		for(String str: output) {
//		  writer.write(str + "\n");
//		}
//		writer.close();
//	}
//	
//	public static List<String> getMemoryCommand(String input){
//		List<String> command = new ArrayList<String> ();
//		System.out.println("// "+input);
//		command.addAll(Memory.parseMemory(input));
//		for (String line : command){
//			System.out.println(line);
//		}
//		return command;
//	}
//	
//	public static List<String> getArithmeticCommand(String input){
//		List<String> command = new ArrayList<String> ();
//		System.out.println("// "+input);
//		command.addAll(Arithmetic.parseArithmetic(input));
//		for (String line : command){
//			System.out.println(line);
//		}
//		return command;
//	}
//	
//	public static List<String> startCode() {
//		
//		List<String> output = new ArrayList<String>();
//		
//		output.add("@256");
//		output.add("D=A");
//		output.add("@SP");
//		output.add("M=D");
//		
//		output.add("@300");
//		output.add("D=A");
//		output.add("@LCL");
//		output.add("M=D");
//		
//		output.add("@400");
//		output.add("D=A");
//		output.add("@ARG");
//		output.add("M=D");
//		
//		output.add("@3000");
//		output.add("D=A");
//		output.add("@THIS");
//		output.add("M=D");
//		
//		output.add("@3010");
//		output.add("D=A");
//		output.add("@THAT");
//		output.add("M=D");
//		
//		return output;
//	}
//	
//public static List<String> endCode() {
//		
//		List<String> output = new ArrayList<String>();
//		
//		output.add("(END)");
//		output.add("@END");
//		output.add("0;JMP");
//		
//		return output;
//	}
}
