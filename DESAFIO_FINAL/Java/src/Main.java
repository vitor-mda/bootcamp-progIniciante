import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public static void main(String[] args) {
		showMenu();
	}
	
	public static void showMenu() {
		System.out.printf("%n- - - F O L H A  D E  P A G A M E N T O - - -%n"
				+ "%n1 - Ver funcionários"
				+ "%n2 - Cadastrar funcionário"
				+ "%n3 - Imprimir contracheque"
				+ "%n0 - Sair"
				+ "%nDigite a opção desejada: ");
		
		option(getOption());
		
	}
	
	public static void option(int option) {
		switch (option) {
			case 1:
				mostrarFuncionarios();
				nextOption();
				break;
			case 2:
				adicionarFuncionario();
				nextOption();
				break;
			case 3:
				imprimirFuncionario();
				nextOption();
				break;
			case 0:
				System.out.printf("%nTchau tchau!");
				sc.close();
				break;
			default:
				System.out.printf("%nERRO: opção inválida.%n");
				nextOption();
		}
	}
	
	public static void mostrarFuncionarios() {
		int i = 1;
		
		if (!funcionarios.isEmpty()) {
			System.out.printf("%nLISTA DE FUNCIONÁRIOS:");
			
			for (Funcionario f : funcionarios) {
				System.out.printf("%n%d - %s", i, f.getNome());
				i++;
			}
		}
		else {
			System.out.printf("%nNenhum funcionário cadastrado.%n");
		}
		
		
	}
	
	public static void adicionarFuncionario() {
		String nome;
		double salarioBruto;
		
		System.out.printf("%n- - - - - CADASTRAR FUNCIONARIO - - - - -%nDigite o nome do funcionário: ");
		nome = getString();
		
		System.out.printf("%nDigite o salário do funcionário (ex: 2500,00): ");
		salarioBruto = getDouble();
		
		funcionarios.add(new Funcionario(nome, salarioBruto));
		
		System.out.printf("%nFuncionário cadastrado com sucesso!%n");
	}
	
	public static void imprimirFuncionario() {
		if (!funcionarios.isEmpty()) {
			Funcionario f;
			
			System.out.printf("%n- - - - - IMPRIMIR CONTRACHEQUE - - - - -%nDigite o índice do funcionário: ");
			try {
				f = funcionarios.get(getInt() - 1);
			}
			catch (IndexOutOfBoundsException e) {
				System.out.printf("%nERRO: índice inválido. Tente novamente.%n");
				return;
			}
			
			System.out.printf("%n- - - - - - - - - - - - - - - "
					+ "%nFuncionário:"
					+ "%n            %s"
					+ "%n%nSalário:"
					+ "%n     Bruto: %.2f"
					+ "%n   Líquido: %.2f"
					+ "%n%nDescontos:"
					+ "%n      INSS: %.2f"
					+ "%n      IRRF: %.2f"
					+ "%n     Total: %.2f"
					+ "%n- - - - - - - - - - - - - - -", f.getNome(), f.getSalarioBruto(), f.getSalarioLiquido(), f.getINSS(), f.getIRRF(), f.getINSS() + f.getIRRF());
		}
		else {
			System.out.printf("%nNenhum funcionário disponível para seleção.%n");
		}
		
	}
				
	// captura a opção digitada pelo usuário
	public static int getOption() {
		int option = getInt();
		
		if (option >= 0 && option <= 3) {
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
				showMenu();
				break;
					
			default:
				option(option);
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
			sc.nextLine();
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
			sc.nextLine();
			System.out.printf("%nPor favor, digite um valor válido.%n");
			return getDouble();
		}
		finally {
			sc.nextLine();
		}
	}
}
