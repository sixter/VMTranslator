package VMTranslator;

import java.util.ArrayList;
import java.util.List;

public class Arithmetic {
	
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
	
}
