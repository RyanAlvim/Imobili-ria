package com.br.LopesElite_Geral.LopesElite_Geral.Entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "LopesElite_Geral")
public class PessoasEntity implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	private String email;
	private String senha;
	private String cargo;
	private String afiliados;
	private String dataEntrada;
	private String telefone;
	private String endereco;
	private String pix;
	private String cep;
	private String dinheiroRecebido;
	
	private String ContaCorrente;
	private String Agencia;
	public String getContaCorrente() {
		return ContaCorrente;
	}
	public void setContaCorrente(String contaCorrente) {
		ContaCorrente = contaCorrente;
	}
	public String getAgencia() {
		return Agencia;
	}
	public void setAgencia(String agencia) {
		Agencia = agencia;
	}
	public String getNomeBanco() {
		return NomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		NomeBanco = nomeBanco;
	}
	private String NomeBanco;
	
	public String getCreci() {
		return creci;
	}
	public void setCreci(String creci) {
		this.creci = creci;
	}
	private String dinheiroPendente;
	private String creci;
	
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	private String gerente;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getAfiliados() {
		return afiliados;
	}
	public void setAfiliados(String afiliados) {
		this.afiliados = afiliados;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getPix() {
		return pix;
	}
	public void setPix(String pix) {
		this.pix = pix;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getDinheiroRecebido() {
		return dinheiroRecebido;
	}
	public void setDinheiroRecebido(String dinheiroRecebido) {
		this.dinheiroRecebido = dinheiroRecebido;
	}
	public String getDinheiroPendente() {
		return dinheiroPendente;
	}
	public void setDinheiroPendente(String dinheiroPendente) {
		this.dinheiroPendente = dinheiroPendente;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
