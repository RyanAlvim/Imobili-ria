package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Entity.cadastroNegocios;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroNegocios;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;
import com.br.LopesElite_Geral.LopesElite_Geral.WebSecurityConfig.ImplementsUserDetailsService;

@Controller
@RequestMapping
public class IndexController {
	
	@Autowired
	private CadastroRepository repository;
	
	@Autowired
	private CadastroNegocios negocios;
	
	@GetMapping("/")
	public String CreateSession(HttpSession session) {
		
		if(session.getAttribute(session.getId()) == null) {
			session.setAttribute(session.getId(), ImplementsUserDetailsService.getEmail);
		}
		return "redirect:/index";
	}
	

	
	@GetMapping("/index")
	public ModelAndView index(HttpSession session) {
		
		PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());		
		
		List<cadastroNegocios> CadNegocios = negocios.findByEmail(pessoas.getEmail());
		
		List<cadastroNegocios> pessoasAdm = negocios.findAll();

		List<cadastroNegocios> empresasAdm = negocios.findAll();
		List<PessoasEntity> listaAfiliados = repository.findAll();
		
		List<PessoasEntity> listaAfiliadosGerente = repository.findByGerente(session.getAttribute(session.getId()).toString());

		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		
		double dinheiro_pendente = 0;
		double dinheiro_recebido = 0;
		for(cadastroNegocios cadastro : CadNegocios) {
			if(cadastro.getStatus().equals("PENDENTE")) {
				dinheiro_pendente += Double.parseDouble(cadastro.getPorcentagem().replace(",", "."));
			}else if(cadastro.getStatus().equals("PAGO")) {
				dinheiro_recebido += Double.parseDouble(cadastro.getPorcentagem().replace(",", "."));
			}
		}
		
		if(pessoas.getCargo().equals("CORRETOR")) {
			mv.addObject("nome", pessoas.getNome());
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			mv.addObject("dinheiro_pendente", df.format(dinheiro_pendente));
			mv.addObject("dinheiro_recebido", df.format(dinheiro_recebido));
			double dinheiro_total = dinheiro_pendente + dinheiro_recebido;
			mv.addObject("dinheiro_total", df.format(dinheiro_total));
			mv.addObject("cargo", pessoas.getCargo());
			mv.addObject("lista", CadNegocios);		
			
		}
		else if(pessoas.getCargo().equals("GERENTE")) {
			mv.addObject("cargo", pessoas.getCargo());
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			double dinheiro_gerente_pendente = Double.parseDouble(pessoas.getDinheiroPendente().replaceAll(",", "."));
			double dinheiro_gerente_recebido = Double.parseDouble(pessoas.getDinheiroRecebido().replaceAll(",", "."));
			
			int afiliados_gerente = 0;
			for(PessoasEntity afiliadosGerente : listaAfiliadosGerente) {
				afiliados_gerente++;
			}
			
			
			mv.addObject("afiliadosGerente", afiliados_gerente);
			mv.addObject("nome", pessoas.getNome());
			mv.addObject("dinheiro_pendente", df.format(dinheiro_gerente_pendente));
			mv.addObject("dinheiro_recebido", df.format(dinheiro_gerente_recebido));
			mv.addObject("dinheiro_total", df.format(dinheiro_gerente_recebido+dinheiro_gerente_pendente));
			mv.addObject("lista", CadNegocios);	
		}else if(pessoas.getCargo().equals("ADM")) {
			
			
			double valor_adm_pendente = 0;
			double valor_adm_pago = 0;
			double valor_total_adm = 0;
			int afiliados = 0;
			for(cadastroNegocios adm : pessoasAdm) {
				if(adm.getStatus().equals("PENDENTE")) {
					valor_adm_pendente += Double.parseDouble(adm.getPorcentagem().replace(",", "."));
				}else if(adm.getStatus().equals("PAGO")) {
					valor_adm_pago += Double.parseDouble(adm.getPorcentagem().replace(",", "."));
				}
		
			}
			
			for(PessoasEntity afiliadosGerente : listaAfiliados) {
				if(!afiliadosGerente.getGerente().equals("NENHUM")) {
					afiliados++;
				}
			}

			double empresa_total = 0;

			for(cadastroNegocios empresa : empresasAdm){
				if(!repository.findByEmail(empresa.getEmail()).getCargo().equals("GERENTE")){
					empresa_total += Double.parseDouble(empresa.getEmpresa().replaceAll(",","."));
				}
			}

			DecimalFormat df = new DecimalFormat("#.##");
			mv.addObject("dinheiro_pendente", NumberFormat.getCurrencyInstance().format(valor_adm_pendente));
			mv.addObject("afiliadosGerente", afiliados);
			mv.addObject("dinheiro_recebido", NumberFormat.getCurrencyInstance().format(valor_adm_pago));
			valor_total_adm = valor_adm_pendente + valor_adm_pago;
			mv.addObject("dinheiro_total", NumberFormat.getCurrencyInstance().format(valor_total_adm));
			mv.addObject("cargo", pessoas.getCargo());
			mv.addObject("nome", pessoas.getNome());
			mv.addObject("lista", pessoasAdm);

			mv.addObject("empresa",empresa_total);
		}
		
		return mv;
		
	}
}
