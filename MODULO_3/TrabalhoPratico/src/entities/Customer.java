package entities;

public class Customer {
	protected String name;
	protected String cpf;
	
	public String toString() {
		return name + "%nCPF: " + cpf;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public Customer(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
}
