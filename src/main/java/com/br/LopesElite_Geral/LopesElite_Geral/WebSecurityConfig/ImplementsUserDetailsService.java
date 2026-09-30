package com.br.LopesElite_Geral.LopesElite_Geral.WebSecurityConfig;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;


@Repository
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CadastroRepository repository;
	
	public static String getEmail;


	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PessoasEntity pessoasLogin = repository.findByEmail(username);
		
		if(pessoasLogin == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}else {
			getEmail = pessoasLogin.getEmail();
			System.out.println(pessoasLogin.getEmail());
			System.out.println("Usuário: " + pessoasLogin.getEmail() + " Senha: " + pessoasLogin.getSenha());
			
		}
		return pessoasLogin;
	}

}
