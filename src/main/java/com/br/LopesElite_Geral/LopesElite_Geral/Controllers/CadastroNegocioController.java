package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Entity.cadastroNegocios;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroNegocios;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;

import Calculo.Calculo;
import Email.Email;

@Controller
@RequestMapping
public class CadastroNegocioController {

	/*nome,tipoNegocio,pv,referencia_imovel,endereco_imovel,valor_negocio,tipo_comissao,gerente
	 * rede,royalties_nf_impostos,vistoria,data_prevista_pagamento,status,data_assinatura,porcentagem,porcentagem_gestao
	 */
	
	@Autowired
	private CadastroNegocios repository;
	
	@Autowired
	private CadastroRepository cadastroNomes;
	
	@GetMapping("/cadastroNegocio")
	public Object cadastro(HttpSession session) {
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("cadastroNegocio");
		mv.addObject("nomes", cadastroNomes.findAll());
		
		return mv;
	}
	
	@PostMapping("/cadastroNegocio")
	public String cadastroPost(HttpSession session, String nome, String email, String tipoNegocio, String pv, String referencia_imovel,String endereco_imovel,String valor_negocio,
			String tipo_comissao, String gerente,String rede, String royalties_nf_impostos,String vistoria, String data_prevista_pagamento,String data_assinatura,
			String porcentagem,String porcentagem_gestao) {

		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		PessoasEntity pessoas = cadastroNomes.findByEmail(email);
		
		List<Double> calculos = Calculo.calculo(Double.parseDouble(valor_negocio), Double.parseDouble(rede), 
				Double.parseDouble(vistoria), Double.parseDouble(porcentagem), 
				Double.parseDouble(royalties_nf_impostos), Double.parseDouble(gerente), porcentagem_gestao == "" ? 0 : Double.parseDouble(porcentagem_gestao));

		cadastroNegocios cadastroNegocios = new cadastroNegocios();
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");

		pessoas.setDinheiroPendente(df.format(Double.parseDouble(calculos.get(6).toString().toString())));
		cadastroNegocios.setNome(nome);
		cadastroNegocios.setEmail(email);
		cadastroNegocios.setTipoNegocio(tipoNegocio);
		cadastroNegocios.setPv(pv);
		cadastroNegocios.setReferecia_imovel(referencia_imovel);
		cadastroNegocios.setEndereco_imovel(endereco_imovel);
		cadastroNegocios.setValor_negocio(df.format(Double.parseDouble(calculos.get(0).toString())));
		cadastroNegocios.setRede(df.format(Double.parseDouble(calculos.get(1).toString())));
		cadastroNegocios.setSaldo(df.format(Double.parseDouble(calculos.get(2).toString())));
		cadastroNegocios.setRoyalties_nf_impostos(df.format(Double.parseDouble(calculos.get(3).toString())));
		cadastroNegocios.setVistoria(df.format(Double.parseDouble(calculos.get(4).toString())));
		cadastroNegocios.setDistribuidor(df.format(Double.parseDouble(calculos.get(5).toString())));
		cadastroNegocios.setPorcentagem(String.valueOf(Double.parseDouble(calculos.get(6).toString())));
		cadastroNegocios.setGerente(df.format(Double.parseDouble(calculos.get(7).toString())));
		cadastroNegocios.setEmpresa(df.format(Double.parseDouble(calculos.get(8).toString())));
		System.out.println(Double.parseDouble(calculos.get(8).toString()));
		cadastroNegocios.setTipo_comissao(tipo_comissao);
		cadastroNegocios.setEmailGerente(pessoas.getGerente());
		
		PessoasEntity gerente_email = cadastroNomes.findByEmail(pessoas.getGerente());
		cadastroNegocios cadastro_negocios_gerente = new cadastroNegocios();
		
		if(pessoas.getGerente() != "NENHUM") {
			
			if(!cadastroNegocios.getGerente().equals("0")) {
			
				gerente_email.setDinheiroPendente(df.format(Double.parseDouble(calculos.get(7).toString().toString())));
	
				cadastro_negocios_gerente.setNome(nome);
				cadastro_negocios_gerente.setEmail(pessoas.getGerente());
				cadastro_negocios_gerente.setData_assinatura(data_assinatura);
				cadastro_negocios_gerente.setData_prevista_pagamento(data_prevista_pagamento);
				cadastro_negocios_gerente.setEndereco_imovel(endereco_imovel);


				cadastro_negocios_gerente.setTipoNegocio(tipoNegocio);
				cadastro_negocios_gerente.setPv(pv);
				cadastro_negocios_gerente.setReferecia_imovel(referencia_imovel);
				cadastro_negocios_gerente.setValor_negocio(df.format(Double.parseDouble(calculos.get(0).toString())));
				cadastro_negocios_gerente.setRede(df.format(Double.parseDouble(calculos.get(1).toString())));
				cadastro_negocios_gerente.setSaldo(df.format(Double.parseDouble(calculos.get(2).toString())));
				cadastro_negocios_gerente.setRoyalties_nf_impostos(df.format(Double.parseDouble(calculos.get(3).toString())));
				cadastro_negocios_gerente.setVistoria(df.format(Double.parseDouble(calculos.get(4).toString())));
				cadastro_negocios_gerente.setDistribuidor(df.format(Double.parseDouble(calculos.get(5).toString())));
				cadastro_negocios_gerente.setPorcentagem(df.format(Double.parseDouble(calculos.get(7).toString())));
				cadastro_negocios_gerente.setEmpresa(String.valueOf(Double.parseDouble(calculos.get(8).toString())));
				cadastro_negocios_gerente.setTipo_comissao(tipo_comissao);
				cadastro_negocios_gerente.setMes(sdf.format(data));
				cadastro_negocios_gerente.setStatus("PENDENTE");
				repository.save(cadastro_negocios_gerente);
			}
		}
		
		
		
		
		cadastroNegocios.setMes(sdf.format(data));
		
		cadastroNegocios.setData_prevista_pagamento(data_prevista_pagamento);
		cadastroNegocios.setStatus("PENDENTE");
		cadastroNegocios.setData_assinatura(data_assinatura);
		
		Email.Enviar(email, "Lopes Elite - Cadastro de Negócios", String.format(""
				+ "<strong>Nome: %s</strong><br>"
				+ "<strong>Tipo de Negócio: %s</strong><br>"
				+ "<strong>Pv: %s</strong><br>"
				+ "<strong>Referência Imóvel: %s</strong><br>"
				+ "<strong>Endereco Imóvel: %s</strong><br>"
				+ "<strong>Valor Pendente: %s</strong><br>"
				+ "<strong>Dinheiro Pendente: %s</strong><br>"
				+ "<strong>Gerente: %s<strong><br>"
				+ "<strong>Data Prevista: %s<br>"
				+ "<strong>Data Assinatura: %s<br>", nome, tipoNegocio,pv, referencia_imovel, endereco_imovel, df.format(Double.parseDouble(calculos.get(0).toString())), df.format(Double.parseDouble(calculos.get(6).toString())), df.format(Double.parseDouble(calculos.get(7).toString())), data_prevista_pagamento, data_assinatura));
		cadastroNegocios.setPorcentagem_gestao(porcentagem_gestao == "" ? "0" : porcentagem_gestao);
				
		repository.save(cadastroNegocios);
		
		calculos.clear();
		
		return "redirect:/administrar";
	}
	
	@GetMapping("/listarpagamentos")
	public Object ListarPagamentos(HttpSession session) {
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listarPagamentos");
		mv.addObject("nomes", cadastroNomes.findAll());
		return mv;
	}
	
	@PostMapping("/listarpagamentos")
	public Object PostListPagamentos(HttpSession session, String nome, String mes) {
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listarpagamento");
		
		System.out.println("MêS " + mes);

		Double valores_totais = Double.valueOf(0);

		if(!mes.equals("TODOS")) {
			String mesVer = mes.length() == 1 ? String.format("0%s", mes) : mes;
			String mes1 = mes.length() == 1 ? "0"+mes : mes;
			for(cadastroNegocios calculo : repository.findByEmailAndMes(nome.trim(), mes1)){
				valores_totais += Double.parseDouble(calculo.getPorcentagem());
				System.out.println("VALOR: " + valores_totais);
			}
			mv.addObject("valor_total", valores_totais);
			mv.addObject("usuario", repository.findByEmailAndMes(nome.trim(),mes1));
		}else {

			for(cadastroNegocios calculo : repository.findByEmail(nome)){
				valores_totais += Double.parseDouble(calculo.getPorcentagem());
			}

			mv.addObject("valor_total", valores_totais);
			mv.addObject("usuario", repository.findByEmail(nome));
		}
		return mv;
	}
	
	@GetMapping("/realizarPagamento")
	public Object Pagamento(HttpSession session) {
		
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("realizarPagamento");
		mv.addObject("negocios",repository.findAll());
		return mv;
	}
	
	
	@GetMapping("/realizarPagamento/pagar")
	public Object RealizarPagamento(@RequestParam int id, HttpSession session) {
		
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		cadastroNegocios cadastro = repository.getById(id);
		PessoasEntity pessoa = cadastroNomes.findByEmail(cadastro.getEmail());
		
		mv.setViewName("pagarPagamentos");
		mv.addObject("id", id);
		mv.addObject("nome", pessoa.getNome());
		mv.addObject("email", cadastro.getEmail());
		mv.addObject("tipoNegocio", cadastro.getTipoNegocio());
		mv.addObject("pv",cadastro.getPv());
		mv.addObject("referencia_imovel", cadastro.getReferecia_imovel());
		mv.addObject("endereco_imovel", cadastro.getEndereco_imovel());
		mv.addObject("valor_negocio", cadastro.getValor_negocio());
		mv.addObject("tipo_comissao", cadastro.getTipo_comissao());
		mv.addObject("gerente", cadastro.getGerente());
		mv.addObject("rede", cadastro.getRede());
		mv.addObject("royalties_nf_impostos",cadastro.getRoyalties_nf_impostos());
		mv.addObject("vistoria", cadastro.getVistoria());
		mv.addObject("data_prevista_pagamento", cadastro.getData_prevista_pagamento());
		mv.addObject("status", cadastro.getStatus());
		mv.addObject("data_assinatura", cadastro.getData_assinatura());
		mv.addObject("porcentagem", cadastro.getPorcentagem());
		mv.addObject("porcentagem_gestao", cadastro.getPorcentagem_gestao());
		mv.addObject("saldo", cadastro.getSaldo());
		mv.addObject("distribuidor",cadastro.getDistribuidor());
		mv.addObject("empresa", cadastro.getEmpresa());
		mv.addObject("Agencia", pessoa.getAgencia());
		mv.addObject("ContaCorrente", pessoa.getContaCorrente());
		mv.addObject("NomeBanco", pessoa.getNomeBanco());
		return mv;
	}
	
	@PostMapping("/realizarPagamento/pagar")
	
	public String pagamentoRealizado(@RequestParam int id, HttpSession session) {
		
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		cadastroNegocios cadastro = repository.getById(id);
		PessoasEntity pessoa = cadastroNomes.findByEmail(cadastro.getEmail());
		double valorPendente = Double.parseDouble(pessoa.getDinheiroPendente().replace(",", "."));
		double valorRecebido = Double.parseDouble(pessoa.getDinheiroRecebido().replace(",", "."));
		
		System.out.println("Valor Pendente: " + valorPendente);
		System.out.println("Valor Recebido: " + valorRecebido);
		double valor_conta = Double.parseDouble(cadastro.getPorcentagem().replace(",", "."));
		System.out.println("Valor conta: " + valor_conta);
		
		Double valor_pendente_descontar = valorPendente - valor_conta;
		
		System.out.println("Valor Pendente Descontar: " + valor_pendente_descontar);
		Double valor_recebido_acrescentar = valorRecebido + valor_conta;
		
		System.out.println("Valor Recebido Acrescentar: " + valor_recebido_acrescentar);
		Double valor_recebido_total = Double.parseDouble(pessoa.getDinheiroRecebido().replace(",", ".")) + valor_recebido_acrescentar; 
		
		System.out.println("Valor Recebido Total: " + valor_recebido_total);
		String valor_format = String.format("%.2f", valor_recebido_total);
		pessoa.setDinheiroPendente(valor_pendente_descontar.toString());
		pessoa.setDinheiroRecebido(valor_format);
		cadastro.setStatus("PAGO");
		cadastro.setDatapagamento(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
		
		cadastroNomes.save(pessoa);
		
		Email.Enviar(pessoa.getEmail(), "Pagamento Realizado", "Um pagamento foi realizado em sua conta");
		
		
		
		return "redirect:/administrar";
	}
	
	
	@GetMapping("/excluirNegocios")
	public Object excluirPagamentos(HttpSession session) {
		
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("excluirNegocios");
		mv.addObject("negocios", repository.findAll());
		return mv;
	}
	
	@GetMapping("/excluirNegocios/delete")
	public String excluirPagamentos(@RequestParam int id, HttpSession session) {
		
		try {
			PessoasEntity pessoas = cadastroNomes.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		repository.deleteById(id);
		return "redirect:/administrar";
	}
	
}
