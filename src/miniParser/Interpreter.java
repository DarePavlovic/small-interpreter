package miniParser;
import java.util.Stack;
public class Interpreter {
	private Stack<Scope> scopes = new Stack<Scope>();
	
	public Interpreter() {
		scopes.push(new Scope(null));//global scope
	}
	
	public void isScopedClosed() {
		if (!scopes.isEmpty()) {
	        throw new IllegalStateException("Error: Syntax Error: Unclosed scopes detected!");
	    }
	}
	
	public void execute(String code) {
		String[] lines = code.split("\\n");
		for(String line:lines) {
			processLine(line.trim());//process every instruction
		}
		//close global scope
		scopes.pop();
	}
	
	private Scope currentScope() {
		if (scopes.isEmpty()) {
            throw new IllegalStateException("Error: Empty scope exception!");
        }
		else {
			return scopes.peek();
		}
		
	}
	
	private void processLine(String line) {
		if(!SyntaxValidator.isValidSyntax(line)) {	//check if the syntax is valid 
			System.out.println("Error: Syntax error: Unsupported syntax: "+ line);
			return;
		}
		if(line.startsWith("scope {")) {
			scopes.push(new Scope(currentScope()));	//open new scope
		}
		else if(line.startsWith("}")) {
			if (!scopes.isEmpty()) {
                scopes.pop();	//close current scope
            } 
			else {
                throw new IllegalStateException("Error: Syntax error: There is no open scope to be closed!");
            }
		}
		else if(line.startsWith("print")) {
			String varName = line.replace("print","").trim();
			Integer value = currentScope().getVariable(varName);
			if(value != null) {
				System.out.println(value);
			}
			else {
				System.out.println("null");
			}
		}
		else if(line.contains("=")) {
			String[] parts = line.split("=");
			String varName = parts[0].trim();
			String value = parts[1].trim();
			if(isNumber(value)) {//if its the second part is value put it into Variable
				currentScope().setVariable(varName, Integer.parseInt(value));
			}
			else {	//if the second value its variable, set value from second variable to the variable on the left 
				Integer varValue  = currentScope().getVariable(value);
				if(varValue !=null) {
					currentScope().setVariable(varName, varValue);
				}
				else {
					System.out.println("null");
				}
			}
		}
		else {
			line = line.trim();
	    	if(!line.isEmpty()) {//if its empty line skip it
			throw new IllegalStateException("Error: Wrong syntax: "+line+" !");
	    	}
		}
	}
	private boolean isNumber(String str) {
		return str.matches("\\d+");
	}
}
