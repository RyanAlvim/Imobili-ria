package com.br.LopesElite_Geral.LopesElite_Geral.Repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.PessoasEntity;


@Repository
public interface CadastroRepository extends JpaRepository<PessoasEntity, Integer>{

	PessoasEntity findByEmail(String email);
	
	List<PessoasEntity> findByCargo(String cargo);
	
	List<PessoasEntity> findByGerente(String gerente);
	
}
