package entities;

public class Customer extends Person {
	private String address;
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return name + "%nCPF: " + cpf + "%n" + address + "%n";
	}
	
	public Customer(String name, String cpf, String address) {
		super(name, cpf);
		this.address = address;
	}
	
	public Customer(String name, String cpf) {
		super(name, cpf);
	}
}
