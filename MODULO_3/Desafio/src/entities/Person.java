package entities;

public abstract class Person {
	protected String name;
	protected String cpf;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}
	
	public Person(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
}
