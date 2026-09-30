package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;
import com.br.LopesElite_Geral.LopesElite_Geral.WebSecurityConfig.ImplementsUserDetailsService;

import Email.Email;

@RequestMapping
@Controller
public class AdministrarController {
	
	@Autowired
	private CadastroRepository repository;

	@GetMapping("/administrar")
	public Object administrar(HttpSession session) throws MessagingException {
	
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		ModelAndView mv = new ModelAndView();
		PessoasEntity pessoasCargo = repository.findByEmail(session.getAttribute(session.getId()).toString());
		
		mv.addObject("cargo",pessoasCargo.getCargo());
		mv.setViewName("administrar");
		//Email.sendEmail()
		return mv;
	}
	
}
