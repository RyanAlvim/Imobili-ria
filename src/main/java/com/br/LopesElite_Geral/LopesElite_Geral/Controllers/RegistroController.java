package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewResolverMethodReturnValueHandler;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.MuralEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.MuralRepository;

import Email.Email;

@Controller
@RequestMapping
public class RegistroController {
	
	@Autowired
	private CadastroRepository repository;
	
	
	@GetMapping("/cadastrar")
	public Object cadastroUsuarios(HttpSession session) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("cadastro");
		List<PessoasEntity> pessoas = repository.findByCargo("GERENTE");
		mv.addObject("pessoas",pessoas);
		return mv;
	}
	
	@GetMapping("/listarUsers")
	public Object listarUsuarios(HttpSession session) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listarUsuarios");
		mv.addObject("listaUsuarios", repository.findAll());
		return mv;
	}
	
	@GetMapping("/edit/usuarios")
	public Object edituser(@RequestParam int id, HttpSession session) {
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("edituser");
		int user_id = repository.getById(id).getId();
		String nome = repository.getById(id).getNome();
		String email = repository.getById(id).getEmail();
		String cargo = repository.getById(id).getCargo();
		String afiliados = repository.getById(id).getGerente();
		String telefone = repository.getById(id).getTelefone();
		String endereco = repository.getById(id).getEndereco();
		String pix = repository.getById(id).getPix();
		String cep = repository.getById(id).getCep();
		
		mv.addObject("user_id",user_id);
		mv.addObject("nome",nome);
		mv.addObject("email",email);
		mv.addObject("cargo",cargo);
		mv.addObject("afiliados",afiliados);
		mv.addObject("telefone",telefone);
		mv.addObject("endereco",endereco);
		mv.addObject("pix",pix);
		mv.addObject("cep",cep);
		
		return mv;
	}
	
	@PostMapping("/edit/usuarios")
	public String atualizarRegistros(int id, String nome, String email, String cargo,String afiliados,String telefone,
			String endereco,String pix,String cep, HttpSession session) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		PessoasEntity pessoas = repository.getById(id);
		pessoas.setNome(nome);
		pessoas.setEmail(email);
		pessoas.setCargo(cargo);
		pessoas.setAfiliados(afiliados);
		pessoas.setTelefone(telefone);
		pessoas.setEndereco(endereco);
		pessoas.setPix(pix);
		pessoas.setCep(cep);
		
		repository.save(pessoas);
		return "redirect:/administrar";
	}
	
	
	
	@PostMapping("/cadastrar")
	public String envioCadstro(HttpSession session, String nome, String email, String data, String telefone, String endereco, String conta, String gerente, String senha, String cep,String cargo, String creci, String agencia, String nome_banco, String conta_corrente) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}

		PessoasEntity pessoas = new PessoasEntity();
		pessoas.setNome(nome);
		pessoas.setEmail(email);
		pessoas.setDataEntrada(data);
		pessoas.setTelefone(telefone);
		pessoas.setEndereco(endereco);
		pessoas.setPix(conta);
		pessoas.setGerente(gerente);
		pessoas.setSenha(new BCryptPasswordEncoder().encode(senha));
		pessoas.setCep(cep);
		pessoas.setDinheiroPendente("0");
		pessoas.setDinheiroRecebido("0");
		pessoas.setCargo(cargo);
		pessoas.setCreci(creci);
		pessoas.setAgencia(agencia);
		pessoas.setContaCorrente(conta_corrente);
		pessoas.setNomeBanco(nome_banco);
		
		repository.save(pessoas);
		Email.Enviar(email, "Lopes Elite - Cadastro", String.format("Olá %s!<br>"
				+ "Seja Bem-Vindo ao nosso Sistema de controle financeiro e Mural de avisos.<br>"
				+ "Logue agora mesmo na plataforma.<br>"
				+ "Email: %s<br>"
				+ "Senha: %s<br>", nome,email,senha));

		return "redirect:/administrar";
	}
	
	@GetMapping("/delete")
	public Object delete(HttpSession session) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deletar");
		mv.addObject("deletar", repository.findAll());
		return mv;
	}
	
	
	@GetMapping("/delete/usuario")
	public String deletar(@RequestParam String id, HttpSession session) {
		
		try {
			PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
			if(!pessoas.getCargo().equals("ADM")) {
				return "redirect:/index";
			}
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		repository.deleteById(Integer.parseInt(id));
		return "redirect:/delete";
	}
}
