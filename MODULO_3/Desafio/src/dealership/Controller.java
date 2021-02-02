package dealership;

import java.util.ArrayList;
import entities.*;

public class Controller {	
	static private boolean loadedData = false;
	
	public static void welcome() {
		System.out.printf("Olá, seja bem-vindo(a) à CONCESSIONÁRIA! :)%n");
	}
	
	public static void showMenu() {
		System.out.printf("%n-------------- MENU --------------%n"+
				" 1 - Listar carros%n"+
				" 2 - Listar motos%n"+
				" 3 - Listar clientes%n"+
				" 4 - Listar vendedores%n"+
				" 5 - Cadastrar carro%n"+
				" 6 - Cadastrar moto%n"+
				" 7 - Cadastrar cliente%n"+
				" 8 - Cadastrar vendedor%n"+
				" 9 - Vender carro%n"+
				"10 - Vender moto%n"+
				"11 - Carregar listas com dados genéricos%n"+
				" 0 - Sair%n%n"+
				"Digite a opção desejada:%n");
		
		option(Helper.getOption());
	}
	
	public static void option(int option) {
		switch (option) {
			case 1:
				showVehicles(Main.cars);
				Helper.nextOption();
				break;
				
			case 2:
				showVehicles(Main.bikes);
				Helper.nextOption();
				break;
				
			case 3:
				showPeople(Main.customers);
				Helper.nextOption();
				break;
				
			case 4:
				showPeople(Main.dealers);
				Helper.nextOption();
				break;
				
			case 5:
				addVehicle(Main.cars, "car");
				Helper.nextOption();
				break;
				
			case 6:
				addVehicle(Main.bikes, "bike");
				Helper.nextOption();
				break;
				
			case 7:
				addPerson(Main.customers, "customer");
				Helper.nextOption();
				break;
				
			case 8:
				addPerson(Main.dealers, "dealer");
				Helper.nextOption();
				break;
				
			case 9:
				sellVehicle(Main.cars);
				Helper.nextOption();
				break;
				
			case 10:
				sellVehicle(Main.bikes);
				Helper.nextOption();
				break;
				
			case 11:
				loadData();
				Helper.nextOption();
				break;
				
			case 0:
				Helper.sc.close();
				System.out.printf("%nTchau tchau! :)");
				break;
				
			default:
				System.out.printf("%nERRO: opção inválida.%n");
				Helper.nextOption();
		}
	}
	
	
	
	// imprime veículos no console
	public static void showVehicles(ArrayList<Vehicle> vehicles) {
		if (!vehicles.isEmpty() ) {
			int i = 1;
			
			System.out.printf("%n" + (vehicles.get(0) instanceof Car ? "------------- CARROS -------------" : "------------- MOTOS --------------") + "%n");
			
			for (Vehicle v : vehicles) {
				System.out.printf(i + ". " + v.toString() + "%n");
				i++;
			}
		}
		else {
			System.out.printf("%nERRO: nenhum veículo foi cadastrado ainda.%n");
		}
	}
	
	// imprime pessoas no console
	public static void showPeople(ArrayList<Person> people) {
		if (!people.isEmpty()) {
			int i = 1;
			
			System.out.printf("%n" + (people.get(0) instanceof Customer ? "------------ CLIENTES ------------" : "----------- VENDEDORES -----------") + "%n");
			
			for (Person p : people) {
				System.out.printf(i + ". " + p.toString() + "%n");
				i++;
			}
		}
		else {
			System.out.printf("%nERRO: ninguém foi cadastrado ainda.%n");
		}
	}
	
	// adiciona um veículo na lista passada como argumento
	public static void addVehicle(ArrayList<Vehicle> vehicles, String type) {
		if (!Main.dealers.isEmpty()) {
			String model;
			String brand;
			int year;
			double price;
			Dealer dealer;
			
			System.out.printf((type == "car" ? "%n-------- CADASTRAR  CARRO --------" : "%n--------- CADASTRAR MOTO ---------") +
					"%nDigite a marca: ");
			brand = Helper.getString();
			
			System.out.printf("%nDigite o modelo: ");
			model = Helper.getString();
			
			System.out.printf("%nDigite o ano: ");
			year = Helper.getInt();
			
			System.out.printf("%nDigite o preço: ");
			price = Helper.getDouble();
			
			
			System.out.printf("%nDigite o índice do vendedor: ");
			try {
				dealer = (Dealer)Main.dealers.get(Helper.getInt() - 1);
			}
			catch (IndexOutOfBoundsException e) {
				System.out.printf("%nERRO: índice inválido. Tente realizar o cadastro novamente.%n");
				return;
			}
			
			vehicles.add(type == "car" ? new Car(model, brand, year, price, dealer) : new Motorcycle(model, brand, year, price, dealer));
			
			System.out.printf("%n----- CADASTRADO COM SUCESSO -----%n");
		}
		else {
			System.out.printf("%nERRO: é necessário cadastrar algum vendedor antes de cadastrar um veículo.%n");
		}
		
	}
	
	// adiciona uma pessoa na lista passada como argumento
	public static void addPerson(ArrayList<Person> people, String type) {
		String name;
		String cpf;
		String idOrAddress;
		
		System.out.printf((type == "customer" ? "%n------- CADASTRAR  CLIENTE -------" : "%n------- CADASTRAR VENDEDOR -------") +
				"%nDigite o nome: ");
		name = Helper.getString();
		
		System.out.printf("%nDigite o CPF: ");
		cpf = Helper.getString();
		
		System.out.printf(type == "customer" ? "%nDigite o endereço: " : "%nDigite o ID: ");
		idOrAddress = Helper.getString();
		
		try {
			people.add(type == "customer" ? new Customer(name, cpf, idOrAddress) : new Dealer(name, cpf, Integer.parseInt(idOrAddress)));
		}
		catch(NumberFormatException e) {
			System.out.printf("%nERRO: ID inválido. Tente cadastrar novamente.%n");			
		}
	}
	
	// vende um veículo da lista passada como argumento
	public static void sellVehicle(ArrayList<Vehicle> vehicles) {
		if (!vehicles.isEmpty()) {
			if (!Main.customers.isEmpty()) {
				Vehicle v;
				
				System.out.printf((vehicles.get(0) instanceof Car ? "%n---------- VENDER CARRO ----------" : "%n---------- VENDER  MOTO ----------") +
						"%nDigite o índice do veículo: ");
				
				try {
					v = vehicles.get(Helper.getInt() - 1);
				}
				catch (IndexOutOfBoundsException e) {
					System.out.printf("%nERRO: índice inválido. Tente novamente.%n");
					return;
				}
				
				if (v.getBuyer() == null) {
					System.out.printf("%nDigite o índice do cliente: ");
					
					try {
						v.setBuyer((Customer)Main.customers.get(Helper.getInt() - 1));
					}
					catch (IndexOutOfBoundsException e) {
						System.out.printf("%nERRO: índice inválido. Tente novamente.%n");
						return;
					}
					
					System.out.printf("%n--------- VENDA EFETUADA ---------%n");
				}
				else {
					System.out.printf("%nERRO: o veículo não está à venda.%n");
				}
			}
			else {
				System.out.printf("%nERRO: é necessário cadastrar algum cliente antes de vender um veículo.%n");
			}
		}
		else {
			System.out.printf("%nERRO: não há veículos à venda.%n");
		}
	}
	
	public static void loadData() {
		if (!loadedData) {
			
			for (int i = 0; i < 4; i++) {
				Main.bikes.add(new Motorcycle("PCX " + (i+1), "Honda", 2013, 13000));
				Main.cars.add(new Car("Portofino " + (i+1), "Ferrari", 2017, 2500000));
				Main.dealers.add(new Dealer("Ciro Bottini " + (i+1), "123.111.222-33", 1000 + i));
				Main.customers.add(new Customer("Hugo Chavez " + (i+1), "242.424.656-69", "Rua Lá, Bairro Cá, 45" + i));
			}
			
			loadedData = true;
			
			System.out.printf("%n-------- DADOS  INSERIDOS --------%n");
		}
		else {
			System.out.printf("%nERRO: os dados genéricos já foram inseridos.%n");
		}
	}
}
