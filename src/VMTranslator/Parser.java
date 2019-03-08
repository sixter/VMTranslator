package VMTranslator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
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
				command.add("D=M");
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
				command.add("@FOO." + parsedLine[2]);
				command.add("A=M");
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
				command.add("D=M");
				command.add("@SP");
				command.add("A=M");
				command.add("M=D+M");
				command.add("A=M-1");
				command.add("D=M");
				command.add("@SP");
				command.add("A=M");
				command.addAll(setNewStack());
			}
			else if (parsedLine[1].equals("pointer") && parsedLine[2].equals("0")){
				command.add("@SP");
				command.add("A=M-1");
				command.add("D=M");
				command.add("@THIS");
				command.addAll(setNewStack());
			}
			else if (parsedLine[1].equals("pointer") && parsedLine[2].equals("1")){
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
				command.addAll(setNewStack());
			}
			else if (parsedLine[1].equals("static")){
				command.add("@SP");
				command.add("A=M-1");
				command.add("D=M");
				command.add("@FOO." + parsedLine[2]);
				command.add("A=M");
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
