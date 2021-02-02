package entities;

public class Dealer extends Person {
	private int id;
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return "(" + id + ") " + name + "%nCPF: " + cpf +"%n";
	}
	
	public Dealer(String name, String cpf, int id) {
		super(name, cpf);
		this.id = id;
	}
}
