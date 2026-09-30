package Calculo;

import java.util.ArrayList;
import java.util.List;

public class Calculo {
	
	public static List<Double> calculo(double valor_total, double rede, double vistoria, double porcentagem_corretor, double custos, double valor_gerente , double gestao) {
			double calculo1 = valor_total - (valor_total * rede / 100);
			double calcular_rede = valor_total - calculo1;
			double calcular_custos = calculo1 - custos;
			double calcular_vistoria = calcular_custos - vistoria;
			double calcular_corretores = calcular_vistoria * (porcentagem_corretor + gestao) * 0.01;
			double calcular_empresa = calcular_vistoria - calcular_corretores - valor_gerente;
			
			List<Double> calculos = new ArrayList<>();
			calculos.add(valor_total);
			calculos.add(calcular_rede);
			calculos.add(calculo1);
			calculos.add(custos);
			calculos.add(vistoria);
			calculos.add(calcular_vistoria);
			calculos.add(calcular_corretores);
			calculos.add(valor_gerente);
			calculos.add(calcular_empresa);
			
			return calculos;
	}

}
