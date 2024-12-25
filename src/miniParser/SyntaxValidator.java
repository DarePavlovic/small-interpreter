package miniParser;

import java.util.regex.Pattern;

public class SyntaxValidator {
	    // Valid syntax patterns
		private static final Pattern ASSIGNMENT_PATTERN = Pattern.compile("^\\s*\\w+\\s*=\\s*\\w+\\s*$");
    
		private static final Pattern PRINT_PATTERN = Pattern.compile("^\\s*print\\s+\\w+\\s*$");
    
		private static final Pattern SCOPE_START_PATTERN = Pattern.compile("^\\s*scope\\s*\\{\\s*$");
    
		private static final Pattern SCOPE_END_PATTERN = Pattern.compile("^\\s*\\}\\s*$");


	    public static boolean isValidSyntax(String line) {
	    	line = line.trim();//Trim whitespace at the beginning and at the end of line
	    	if(line.isEmpty()) {
	    		return true;//Ignore empty lines
	    	}
	        // Check if the line matches any valid pattern
	        return ASSIGNMENT_PATTERN.matcher(line).matches()
	            || PRINT_PATTERN.matcher(line).matches()
	            || SCOPE_START_PATTERN.matcher(line).matches()
	            || SCOPE_END_PATTERN.matcher(line).matches();
	    }

}
