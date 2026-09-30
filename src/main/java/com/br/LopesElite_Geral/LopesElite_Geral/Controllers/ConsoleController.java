package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;
import com.mashape.unirest.http.exceptions.UnirestException;

import Assertiva.Assertiva;
import Login.Login;
import Request.Request;

@RequestMapping
@Controller
public class ConsoleController {
	
	private static ArrayList<String> read = new ArrayList<>();

	@Autowired
	private CadastroRepository repository;
	
	@GetMapping("/console")
	public String console(HttpSession session) {
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		
		return "console";
		
	}
	
	@PostMapping("/console")
	public String Run(String console, HttpSession session) throws UnirestException {
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		
		if(!console.equals("")) {
			try {
				System.out.println(Request.Request(console.replaceAll("/", "%20")));
				
			}catch(Exception e) {
				return "redirect:/console/finalizado";
			}
			return "redirect:/console/finalizado";
		}else {
			return "redirect:/console";
		}
		
	}
	
	@GetMapping("/console/finalizado")
	public String Finalizado(Model model, HttpSession session) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
			try {
				read.clear();
			      FileReader arq = new FileReader("nomes.txt");
			      BufferedReader lerArq = new BufferedReader(arq);

			      String linha = lerArq.readLine();
			
			      while (linha != null) {
			        System.out.printf("%s\n", linha);

			        linha = lerArq.readLine(); 
			        read.add(linha);
			      }

			      arq.close();
			}catch(Exception e) {
				
			}
	        model.addAttribute("arquivo",read);
			return "finalizado";
		
	}

	@GetMapping("/console/desligar")
	public String Desligar(HttpSession session) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
			try {
			Login.driver.quit();
			}catch(Exception e) {
				Assertiva.driver.quit();
			}
			return "redirect:/console";

		
	}
}
