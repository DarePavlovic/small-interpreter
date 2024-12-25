package miniParser;
import java.util.HashMap;
import java.util.Map;

public class Scope {
	private Map<String,Integer> variables = new HashMap<String, Integer>();
	private Scope parentScope;
	public Scope(Scope parentScope) {
		this.parentScope = parentScope;
	}
	public void setVariable(String name, int value) {
		variables.put(name, value);
	}
	public Integer getVariable(String name) {
		if(variables.containsKey(name)) {
			return variables.get(name);
		}
		else if(parentScope!=null) {
			return parentScope.getVariable(name);
		}
		else {
			return null;
		}
	}
}
