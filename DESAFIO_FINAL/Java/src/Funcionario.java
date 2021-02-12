public class Funcionario {			
	private String nome;
	private double salarioBruto;
	private double salarioLiquido;
	private double INSS;
	private double IRRF;
	
	public String getNome() {
		return nome;
	}
	
	public double getSalarioBruto() {
		return salarioBruto;
	}
	
	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
		
		setSalarioLiquido();
	}
	
	public double getSalarioLiquido() {
		return salarioLiquido;
	}
	
	public double getINSS() {
		return INSS;
	}
	
	public double getIRRF() {
		return IRRF;
	}
	
	private void setSalarioLiquido() {
		final double INSSMax = 713.10;
		final double[][] tabelaINSS = {
				{0.0,     1045.00, 0.075},
				{1045.01, 2089.60, 0.09},
				{2089.61, 3134.40, 0.12},
				{3134.41, 6101.06, 0.14}
				};
		final double[][] tabelaIRRF = {
				{0.0,     1903.98, 					0.0,   0.0},
				{1903.99, 2826.65, 					0.075, 142.80},
				{2826.66, 3751.05, 					0.15,  354.80},
				{3751.06, 4664.68, 					0.225, 636.13},
				{4664.69, Double.POSITIVE_INFINITY, 0.275, 869.36}
				};
		
		if (salarioBruto > tabelaINSS[3][1]) {
			INSS = INSSMax;
		}
		else {
			for (double[] faixa : tabelaINSS) {
				if (salarioBruto >= faixa[0] && salarioBruto <= faixa[1]) {
					INSS += (salarioBruto - faixa[0]) * faixa[2];
					break;
				} else {
					INSS += (faixa[1] - faixa[0]) * faixa[2];
				}
			}
		}
		
		salarioLiquido = salarioBruto - INSS;
		
		for (double[] faixa : tabelaIRRF) {
			if (salarioLiquido >= faixa[0] && salarioLiquido <= faixa[1]) {
				IRRF = salarioLiquido * faixa[2] - faixa[3];
			}
		}
		
		salarioLiquido -= IRRF;
	}
	
	public Funcionario(String nome, double salarioBruto) {
		this.nome = nome;
		this.salarioBruto = salarioBruto;
		
		setSalarioLiquido();
	}
}
