package com.br.LopesElite_Geral.LopesElite_Geral.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ganhos")
public class GanhosEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nomeGanho;
	private String tipoNegocio;
	private String descricaoNegocio;
	public String getTipoNegocio() {
		return tipoNegocio;
	}
	public void setTipoNegocio(String tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}
	public String getDescricaoNegocio() {
		return descricaoNegocio;
	}
	public void setDescricaoNegocio(String descricaoNegocio) {
		this.descricaoNegocio = descricaoNegocio;
	}
	private String dataGanho;
	private String valorGanho;
	private String inicialMes;
	
	public String getInicialMes() {
		return inicialMes;
	}
	public void setInicialMes(String inicialMes) {
		this.inicialMes = inicialMes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeGanho() {
		return nomeGanho;
	}
	public void setNomeGanho(String nomeGanho) {
		this.nomeGanho = nomeGanho;
	}
	public String getDataGanho() {
		return dataGanho;
	}
	public void setDataGanho(String dataGanho) {
		this.dataGanho = dataGanho;
	}
	public String getValorGanho() {
		return valorGanho;
	}
	public void setValorGanho(String valorGanho) {
		this.valorGanho = valorGanho;
	}
}
