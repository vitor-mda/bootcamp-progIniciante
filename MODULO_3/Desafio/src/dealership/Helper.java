package dealership;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
	protected static Scanner sc = new Scanner(System.in);
	
	// captura a opção digitada pelo usuário
	public static int getOption() {
		int option = getInt();
		
		if (option >= 0 && option <= 11 ) {
			return option;
		}
		else {
			System.out.printf("%nERRO: opção inválida.%n");
			return getOption();
		}
	}
	
	// função chamada após uma ação ser concluída para que o menu de opções só seja mostrado caso o usuário queira
	public static void nextOption() {
		int option;
		
		System.out.printf("%nDigite a próxima opção ou 0 para exibir o menu.%n");
			
		option = getOption();
			
		switch (option) {
			case 0:
				Controller.showMenu();
				break;
				
			default:
				Controller.option(option);
		}
	}
	
	public static String getString() {
		return sc.nextLine();
	}
	
	public static int getInt() {
		try {
			return sc.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.printf("%nPor favor, digite um número válido.%n");
			return getInt();
		}
		finally {
			sc.nextLine();
		}
	}
	
	public static double getDouble() {
		try {
			return sc.nextDouble();
		}
		catch (InputMismatchException e) {
			System.out.printf("%nPor favor, digite um valor válido.%n");
			return getDouble();
		}
		finally {
			sc.nextLine();
		}
	}
}
