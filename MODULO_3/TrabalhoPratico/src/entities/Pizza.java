package entities;

public class Pizza {
	private final static String[] types = new String[] {"Calabresa", "Frango com catupiry", "Marguerita", "Lombo com abacaxi"};
	protected String type;
	
	public static String getTypes() {
		String string = "";
		
		for (int i = 0; i < types.length; i++) {
			string += (i + 1) + ". " + types[i] + "%n";
		}
		
		return string;
	}
	
	public String toString() {
		return "Pizza de " + type;
	}
	
	public Pizza(int type) {
		this.type = types[type];
	}
}
