package com.br.LopesElite_Geral.LopesElite_Geral.WebSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementsUserDetailsService repository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/login").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().formLogin().loginPage("/login")
		.failureUrl("/login?error=true")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(repository)
			.passwordEncoder(new BCryptPasswordEncoder());
			
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**","/style/**");
	}
}
