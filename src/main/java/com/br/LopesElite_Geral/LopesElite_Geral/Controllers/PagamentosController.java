package com.br.LopesElite_Geral.LopesElite_Geral.Controllers;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;
import com.br.LopesElite_Geral.LopesElite_Geral.Entity.cadastroNegocios;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroNegocios;
import com.br.LopesElite_Geral.LopesElite_Geral.Repository.CadastroRepository;

@RequestMapping
@Controller
public class PagamentosController {
	
	@Autowired
	private CadastroRepository repository;

	@Autowired
	private CadastroNegocios negocios;
	
	@GetMapping("/pagamentos")
	public ModelAndView pagamentos(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		PessoasEntity pessoas = repository.findByEmail(session.getAttribute(session.getId()).toString());
		List<cadastroNegocios> negociosCad = negocios.findByEmail(session.getAttribute(session.getId()).toString());
		
		
		double dinheiro_recebido = 0;
		double dinheiro_pendente = 0;
		
		for(cadastroNegocios cadastro : negociosCad) {
			if(cadastro.getStatus().equals("PENDENTE")) {
				dinheiro_recebido += Double.parseDouble(pessoas.getDinheiroPendente().replace(",", "."));
			}else if(cadastro.getStatus().equals("PAGO")) {
				dinheiro_pendente += Double.parseDouble(pessoas.getDinheiroRecebido().replace(",", "."));
			}
		}
				
		if(pessoas.getCargo().equals("ADM")) {
			mv.addObject("negocios",negocios.findAll());
		}else {
			mv.addObject("negocios", negociosCad);
		}
		System.out.println(dinheiro_recebido);
		System.out.println(dinheiro_pendente);
		mv.addObject("email", pessoas.getEmail());
	    DecimalFormat df = new DecimalFormat("#.##");
	    
		mv.addObject("dinheiro_recebido", df.format(Double.parseDouble(pessoas.getDinheiroRecebido().replace(",", "."))));
		mv.addObject("dinheiro_pendente", df.format(Double.parseDouble(pessoas.getDinheiroPendente().replace(",", "."))));
		mv.addObject("pix", pessoas.getPix());
		mv.addObject("nome", pessoas.getNome());
		mv.setViewName("pagamentos");
		
		
		return mv;
	}
	
}
