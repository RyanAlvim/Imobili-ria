package com.br.LopesElite_Geral.LopesElite_Geral.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.cadastroNegocios;

@Repository
public interface CadastroNegocios extends JpaRepository<cadastroNegocios, Integer>{

	//@Query(value = "select * from cadastro_negocios WHERE mes='09'",nativeQuery = true)
	List<cadastroNegocios> findByEmailAndMes(String email, String mes);
	
	List<cadastroNegocios> findByEmail(String email);
	

}
