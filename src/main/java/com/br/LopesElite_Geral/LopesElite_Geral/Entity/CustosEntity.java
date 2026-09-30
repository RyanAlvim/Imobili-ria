package com.br.LopesElite_Geral.LopesElite_Geral.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Custos")
public class CustosEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nomeCusto;
	private String dataCusto;
	private String valorCusto;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String inicialMes;
	public Integer getId() {
		return id;
	}
	public String getInicialMes() {
		return inicialMes;
	}
	public void setInicialMes(String inicialMes) {
		this.inicialMes = inicialMes;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCusto() {
		return nomeCusto;
	}
	public void setNomeCusto(String nomeCusto) {
		this.nomeCusto = nomeCusto;
	}
	public String getDataCusto() {
		return dataCusto;
	}
	public void setDataCusto(String dataCusto) {
		this.dataCusto = dataCusto;
	}
	public String getValorCusto() {
		return valorCusto;
	}
	public void setValorCusto(String valorCusto) {
		this.valorCusto = valorCusto;
	}
	
}
