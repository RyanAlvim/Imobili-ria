package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.CustosEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CustosRepository;

@Controller
@RequestMapping
public class CustosController {

	@Autowired
	private CustosRepository repository;
	
	@Autowired
	private CadastroRepository pessoasCadastro;
	
	@GetMapping("/custos")
	public Object custos(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("custos");
		
		mv.addObject("custos", repository.findAll());
		
		double precoSomado = 0;
		for(CustosEntity precos : repository.findAll()) {
			precoSomado += Double.parseDouble(precos.getValorCusto());
		}
		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println(precoSomado);
		mv.addObject("preco",df.format(precoSomado));
		return mv;
	}
	
	@GetMapping("/cadastrarCustos")
	public String custo(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		return "cadastrarCusto";
	}
	
	@PostMapping("/cadastrarCustos")
	public String cadastrarCustos(HttpSession session, String nome, String dataCusto,String valorCusto) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		CustosEntity custos = new CustosEntity();
		custos.setNomeCusto(nome);
		custos.setDataCusto(dataCusto);
		custos.setValorCusto(valorCusto);
		String dataSplit[] = dataCusto.split("-");
		custos.setInicialMes(dataSplit[1]);
		repository.save(custos);
		return "redirect:/administrar";
		
	}
	
	@GetMapping("/delete/custos")
	public String deletarCustos(@RequestParam Integer id,HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		repository.deleteById(id);
		return "redirect:/custos";
	}
	
	@GetMapping("/deleteCustos")
	public Object deletarCustosIndex(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deletarCustos");
		mv.addObject("custos", repository.findAll());
		return mv;
	}
	
	@GetMapping("/listarcustos")
	public String listGanhos(HttpSession session) {
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		return "listarcustos";
		
		
	}
	
	@PostMapping("/listarcustos")
	public Object listaGanhos(HttpSession session,String mes) {
		ModelAndView mv = new ModelAndView();
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		
		if(!mes.equals("TODOS")) {
			String mesVer = mes.length() == 1 ? "0"+mes : mes;
			mv.addObject("usuario", repository.findByInicialMes(mesVer));

		}else {

			mv.addObject("usuario", repository.findAll());
		}
		mv.setViewName("listacustos");
		return mv;
		
	}
	
	
}
