package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import java.text.DecimalFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.GanhosEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.GanhosRepository;

@Controller
@RequestMapping
public class GanhosController {

	@Autowired
	private GanhosRepository repository;
	
	@Autowired 
	private CadastroRepository pessoasLista;
	
	@GetMapping("/ganhos")
	public Object ganhos(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasLista.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ganhos");
		mv.addObject("ganhos", repository.findAll());
		
		double ganho = 0;
		for(GanhosEntity ganhos : repository.findAll()) {
			ganho += Double.parseDouble(ganhos.getValorGanho());
		}
		DecimalFormat df = new DecimalFormat("#.##");

		mv.addObject("precoGanho", df.format(ganho));
		return mv;
		
	}
	
	@GetMapping("/cadastrarGanhos")
	public String cadastroGanhos(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasLista.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		return "cadastrarGanhos";
	}
	
	@PostMapping("/cadastrarGanhos")
	public String enviarCadastro(String nome, String dataGanho, String valorGanho,String tipoNegocio,String descricaoNegocio, HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasLista.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		GanhosEntity ganhos = new GanhosEntity();
		ganhos.setNomeGanho(nome);
		ganhos.setDataGanho(dataGanho);
		ganhos.setValorGanho(valorGanho);
		ganhos.setDescricaoNegocio(descricaoNegocio);
		ganhos.setTipoNegocio(tipoNegocio);
		String dataSplit[] = dataGanho.split("-");
		ganhos.setInicialMes(dataSplit[1]);
		repository.save(ganhos);
		return "redirect:/ganhos";
	}
	
	@RequestMapping("/deleteGanhos")
	public Object deletarGanhos(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasLista.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deletarGanhos");
		mv.addObject("deleteList", repository.findAll());
		return mv;
	}
	
	@RequestMapping("/deletarGanhos/ganhos")
	public String deleteGanhos(@RequestParam Integer id, HttpSession session) {
		try {
			PessoasEntity pessoas = pessoasLista.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		repository.deleteById(id);
		return "redirect:/administrar";
	}
	
	
	@GetMapping("/listarganhos")
	public String listGanhos(HttpSession session) {
		try {
			PessoasEntity pessoas = pessoasLista.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		return "listaganhos";
		
		
	}
	
	@PostMapping("/listarganhos")
	public Object listaGanhos(HttpSession session,String mes) {
		ModelAndView mv = new ModelAndView();
		try {
			PessoasEntity pessoas = pessoasLista.findByEmail(session.getAttribute(session.getId()).toString());
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
		mv.setViewName("listarganhos");
		return mv;
		
	}
}
