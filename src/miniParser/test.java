package miniParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class test {

	public static void main(String[] args) {
		String filePath = "src/miniParser/program.txt";
		
		try {
			StringBuilder code = new StringBuilder();//helps me to append every line from the file
			//read lines from the file
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = reader.readLine())!=null) {
				code.append(line).append("\n");
			}
			reader.close();
	
			//initialize interpreter
			Interpreter interpreter = new Interpreter();
			interpreter.execute(code.toString());
			interpreter.isScopedClosed();//check if every scope is closed in code!
			
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		}

	}

}
