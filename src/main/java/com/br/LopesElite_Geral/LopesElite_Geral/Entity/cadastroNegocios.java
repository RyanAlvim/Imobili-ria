package com.br.LopesElite_Geral.LopesElite_Geral.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cadastroNegocios")
public class cadastroNegocios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;
	private String tipoNegocio;
	private String pv;
	private String referecia_imovel;
	private String endereco_imovel;
	private String valor_negocio;
	private String tipo_comissao;
	private String gerente;
	private String rede;
	private String royalties_nf_impostos;
	private String vistoria;
	private String data_prevista_pagamento;
	private String status;
	private String data_assinatura;
	private String porcentagem;
	private String porcentagem_gestao;
	private String saldo;
	private String distribuidor;
	private String empresa;
	private String datapagamento;
	private String emailGerente;
	public String getEmailGerente() {
		return emailGerente;
	}
	public void setEmailGerente(String emailGerente) {
		this.emailGerente = emailGerente;
	}
	private String email;
	
	public String getDatapagamento() {
		return datapagamento;
	}
	public void setDatapagamento(String datapagamento) {
		this.datapagamento = datapagamento;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String mes;

	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipoNegocio() {
		return tipoNegocio;
	}
	public void setTipoNegocio(String tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv;
	}
	public String getReferecia_imovel() {
		return referecia_imovel;
	}
	public void setReferecia_imovel(String referecia_imovel) {
		this.referecia_imovel = referecia_imovel;
	}
	public String getEndereco_imovel() {
		return endereco_imovel;
	}
	public void setEndereco_imovel(String endereco_imovel) {
		this.endereco_imovel = endereco_imovel;
	}
	public String getValor_negocio() {
		return valor_negocio;
	}
	public void setValor_negocio(String valor_negocio) {
		this.valor_negocio = valor_negocio;
	}
	public String getTipo_comissao() {
		return tipo_comissao;
	}
	public void setTipo_comissao(String tipo_comissao) {
		this.tipo_comissao = tipo_comissao;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	public String getRede() {
		return rede;
	}
	public void setRede(String rede) {
		this.rede = rede;
	}
	public String getRoyalties_nf_impostos() {
		return royalties_nf_impostos;
	}
	public void setRoyalties_nf_impostos(String royalties_nf_impostos) {
		this.royalties_nf_impostos = royalties_nf_impostos;
	}
	public String getVistoria() {
		return vistoria;
	}
	public void setVistoria(String vistoria) {
		this.vistoria = vistoria;
	}
	public String getData_prevista_pagamento() {
		return data_prevista_pagamento;
	}
	public void setData_prevista_pagamento(String data_prevista_pagamento) {
		this.data_prevista_pagamento = data_prevista_pagamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData_assinatura() {
		return data_assinatura;
	}
	public void setData_assinatura(String data_assinatura) {
		this.data_assinatura = data_assinatura;
	}
	public String getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}
	public String getPorcentagem_gestao() {
		return porcentagem_gestao;
	}
	public void setPorcentagem_gestao(String porcentagem_gestao) {
		this.porcentagem_gestao = porcentagem_gestao;
	}
	
}
