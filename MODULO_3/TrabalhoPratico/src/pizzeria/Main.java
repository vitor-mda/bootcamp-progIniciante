package pizzeria;

import java.util.Scanner;
import java.util.Stack;
import entities.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static Queue<Order> q = new LinkedList<Order>();
	private static ArrayList<Order> toSort = new ArrayList<Order>();
	private static Stack<Order> delivery = new Stack<Order>();
	
	public static void main(String[] args) {
		System.out.printf("%n! BEM-VINDO(A) À PIZZARIA !%n");
		menu();
	}
	
	private static void menu() {
		int option = 0;
		
		System.out.printf("%n-------------- MENU --------------%n%n"+
				"1 - Ver lista de clientes%n"+
				"2 - Cadastrar cliente%n"+
				"3 - Remover cliente%n"+
				"4 - Inserir pedido na fila de preparo%n"+
				"5 - Remover pedido da fila de preparo%n"+
				"6 - Organizar entrega%n"+
				"7 - Avançar para o próximo pedido%n"+
				"0 - Sair%n");
		 
		try {
			option = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.printf("%nDigite uma opção numérica válida.%n");
			menu();
		}
		
		switch (option) {
		case 1: 
			showCustomers();
			menu();
			break;
		case 2: 
			addCustomer();
			menu();
			break;
		case 3:
			removeCustomer();
			menu();
			break;
		case 4:
			addToQ();
			menu();
			break;
		case 5:
			removeFromQ();
			menu();
			break;
		case 6:
			sortStack();
			menu();
			break;
		case 7:
			next();
			menu();
			break;
		case 0:
			sc.close();
			System.out.printf("%nPrograma encerrado. Até mais!");
			break;
		default:
			System.out.printf("%nDigite uma opção numérica válida.%n");
			menu();
		}
	}
	
	// 1
	private static void showCustomers() {
		if (customers.isEmpty()) {
			System.out.printf("%nLista de clientes vazia.%n");
		}
		else {
			int i = 1;
			
			System.out.printf("%nLista de clientes:%n");
			
			for (Customer c : customers) {
				System.out.printf("%n" + i + ". " + c + "%n");
				i++;
			}
		}
	}
	
	// 2
	private static void addCustomer() {
		String name;
		String cpf;
		
		System.out.printf("%nDigite o nome do cliente: ");
		name = sc.nextLine();
		
		System.out.printf("%nDigite o CPF do cliente: ");
		cpf = sc.nextLine();
		
		if (!isRegistered(cpf)) {
			customers.add(new Customer(name, cpf));
			System.out.printf("%nCliente cadastrado com sucesso.%n");
		}
		else {
			System.out.printf("%nCliente já possui cadastro.%n");
		}
	}
	
	// 3
	private static void removeCustomer() {
		if (!customers.isEmpty()) {
			System.out.printf("%nDigite o índice do cliente que deseja remover: ");
			
			try {
				System.out.printf("%nCliente " + customers.remove(sc.nextByte() - 1).getName() + " removido com sucesso; lista atualizada.%n");
			} catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.printf("%nÍndice inválido.%n");
			}
			finally {
				sc.nextLine();
			}
		}
		else {
			System.out.printf("%nNão há clientes a serem removidos.%n");
		}
	}
	
	// 4
	private static void addToQ() {
		Customer c;
		int pizzaType;
		float distance;
		Order o;
		
		if (!customers.isEmpty()) {
			System.out.printf("%nDigite o índice cliente: ");
			try {
				c = customers.get(sc.nextInt() - 1);
			}
			catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.printf("%nÍndice inválido, tente novamente.%n");
				return;
			}
			finally {
				sc.nextLine();
			}
			
			System.out.printf("%n%nTIPOS DE PIZZA:%n" + Pizza.getTypes() + "%n");
			System.out.printf("%nAgora, digite o índice do tipo do pedido do cliente: ");
			try {
				pizzaType = sc.nextInt() - 1;
			} catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.printf("%nÍndice do tipo de pedido inválido, tente novamente.%n");
				return;
			}
			finally {
				sc.nextLine();
			}
			
			System.out.printf("%nAgora, digite a distância do destino: ");
			try {
				distance = sc.nextFloat();
			}
			catch (InputMismatchException e) {
				System.out.printf("%nDistância inválida, tente novamente.%n");
				return;
			}
			finally {
				sc.nextLine();
			}
			
			o = new Order(c, pizzaType, distance);
			q.add(o);
			
			System.out.printf("%nO pedido a seguir foi adicionado à fila de preparo:%n" + o + "%n");
		}
		else {
			System.out.printf("%nCadastre um cliente antes de adicionar um pedido à fila.%n");
		}
		
		
	}
	
	// 5
	private static void removeFromQ() {
		Order o;
		
		if (q.isEmpty()) {
			System.out.printf("%nA fila ainda não contém nenhum pedido a ser preparado.%n");
			return;
		}
		
		o = q.remove();
		toSort.add(o);
		System.out.printf("%nO pedido de " + o.getCustomerName() + " foi preparado e removido da fila.%n");
	}
	
	// 6
	private static void sortStack() {
		if (!toSort.isEmpty()) {
			int size = toSort.size();
			delivery.setSize(size);
			
			for (short i = 0; i < size; i++) {
				int pos = 0;
				
				for (short j = 0; j < size; j++) {
					if (toSort.get(i).getDistance() < toSort.get(j).getDistance()) {
						pos++;
					}
				}
				
				delivery.set(pos, toSort.get(i));
			}
			toSort.clear();
			
			System.out.printf("%nEntregas ordenadas com sucesso."
					+ "%nA primeira entrega será: " + delivery.peek() + "%n");
		}
		else {
			System.out.printf("%nNão há nenhum pedido a ser ordenado.%n");
		}
	}
	
	// 7
	private static void next() {
		if (!delivery.isEmpty()) {
			System.out.printf("%nO pedido de " + delivery.pop().getCustomerName() + " foi entregue e removido da pilha.");
			if (!delivery.isEmpty()) {
				System.out.printf("%nA próxima entrega será: " + delivery.peek() + "%n");
			}
			else {
				System.out.printf("%nTodas as entregas foram realizadas com sucesso!%n");
			}
		}
		else {
			System.out.printf("%nNão há pedidos para entregar.%n");
		}
	}
	
	private static boolean isRegistered(String cpf) {
		for (int i = 0; i < customers.size(); i++) {
			Customer checked = customers.get(i);
			if (checked.getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}
}
