package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

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

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.MuralEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.MuralRepository;

import Email.Email;

@Controller
@RequestMapping
public class MuralController {
	
	@Autowired
	private MuralRepository repository;
	
	@Autowired
	private CadastroRepository pessoasCadastro;
	
	@GetMapping("/cadastrarmural")
	public String mural(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		return "cadastrarmural";
	}
	
	
	@PostMapping("/cadastrarmural")
	public String postMural(String titulo, String tema, String dataPostagem,String texto, HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		MuralEntity mural = new MuralEntity();
		mural.setTitulo(titulo);
		mural.setTema(tema);
		mural.setDataPostagem(dataPostagem);
		mural.setDataRevogacao("-");
		mural.setTexto(texto);
		mural.setSituacao("ATIVA");
		
		repository.save(mural);
		
		for(PessoasEntity pessoas : pessoasCadastro.findAll()) {
			Email.Enviar(pessoas.getEmail(), titulo, texto);
		}
		
	
		
		return "redirect:/administrar";
	}
	
	@GetMapping("/atualizarmural")
	public Object atualizarMural(@RequestParam int id, HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		@SuppressWarnings("deprecation")
		MuralEntity mural = repository.getById(id);
		
		int th_id = id;
		String data_postagem = mural.getDataPostagem();
		String tema = mural.getTema();
		String titulo = mural.getTitulo();
		String texto = mural.getTexto();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("atualizarmural");
		mv.addObject("th_id", th_id);
		mv.addObject("data_postagem",data_postagem);
		mv.addObject("tema", tema);
		mv.addObject("titulo", titulo);
		mv.addObject("texto", texto);
		return mv;
	}
	
	@PostMapping("/atualizarmural")
	public String postAtualizarMural(int id, String titulo, String tema, String dataPostagem,String texto, HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		MuralEntity mural = repository.getById(id);
		mural.setTitulo(titulo);
		mural.setTema(tema);
		mural.setDataPostagem(dataPostagem);
		mural.setTexto(texto);
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mural.setDataRevogacao(sdf.format(data));
		mural.setSituacao("REVOGADA");
		repository.save(mural);
		return "redirect:/administrar";
	}
	
	@GetMapping("/editarmural")
	public Object editMural(HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editarmural");
		mv.addObject("editmural", repository.findAll());
		return mv;
	}
	
	
	@GetMapping("/mural")
	public ModelAndView cadMural() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mural");
		mv.addObject("mural",repository.findAll());
		
		return mv;
	}
	
	@GetMapping("/mural/delete")
	public String deletarMural(@RequestParam int id, HttpSession session) {
		
		try {
			PessoasEntity pessoas = pessoasCadastro.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		repository.deleteById(id);
		return "redirect:/editarmural";
	}

	
	
	@GetMapping("/visualizar")
	public ModelAndView visualizar(@RequestParam int id) {
		MuralEntity mural = repository.getById(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("visualizar");
		String titulo = mural.getTitulo();
		String texto = mural.getTexto();
		String tema = mural.getTema();
		String situacao = mural.getSituacao();
		mv.addObject("titulo",titulo);
		mv.addObject("texto",texto);
		mv.addObject("tema",tema);
		mv.addObject("situacao", situacao);
		return mv;
		
	}
}
