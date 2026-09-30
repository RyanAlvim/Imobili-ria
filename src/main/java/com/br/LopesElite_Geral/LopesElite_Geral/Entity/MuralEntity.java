package com.br.LopesElite_Geral.LopesElite_Geral.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Mural")
public class MuralEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String titulo;
	private String tema;
	private String situacao;
	private String dataPostagem;
	private String dataRevogacao;
	private String texto;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDataPostagem() {
		return dataPostagem;
	}
	public void setDataPostagem(String dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
	public String getDataRevogacao() {
		return dataRevogacao;
	}
	public void setDataRevogacao(String dataRevogacao) {
		this.dataRevogacao = dataRevogacao;
	}

}
