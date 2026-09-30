package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/login")
	public Object login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		return mv;
	}
	
}
