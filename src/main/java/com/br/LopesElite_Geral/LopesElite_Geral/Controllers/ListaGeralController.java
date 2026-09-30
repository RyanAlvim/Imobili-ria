package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;

import io.netty.handler.codec.http.HttpResponseStatus;

@RequestMapping
@Controller
public class ListaGeralController {
	
	@Autowired
	private CadastroRepository repository;

	@GetMapping("/corretores")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("corretores");
		return mv.addObject("lista",repository.findAll());
	}
	
	@GetMapping("/corretores/id")
	public ModelAndView listaCorretoresID(@RequestParam Integer id, Model model) {
		model.addAttribute("lista", repository.findById(id));
		System.out.println(repository.findById(id));
		return new ModelAndView("corretores");
	}
}
