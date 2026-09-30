package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;

@RequestMapping
@Controller
public class Perfil {

	
	@Autowired
	private CadastroRepository repository;
	
	@GetMapping("/perfil")
	public ModelAndView perfil(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
		mv.setViewName("perfil");
		mv.addObject("nome", pessoas.getNome());
		mv.addObject("email", pessoas.getEmail());
		mv.addObject("data_entrada", pessoas.getDataEntrada());
		mv.addObject("telefone", pessoas.getTelefone());
		mv.addObject("endereco", pessoas.getEndereco());
		mv.addObject("pix", pessoas.getPix());
		mv.addObject("gerente", pessoas.getGerente());
		mv.addObject("cep", pessoas.getCep());
		return mv;
	}
}
