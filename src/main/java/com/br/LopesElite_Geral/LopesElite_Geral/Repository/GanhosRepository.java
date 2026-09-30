package com.br.LopesElite_Geral.LopesElite_Geral.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.GanhosEntity;

@Repository
public interface GanhosRepository extends JpaRepository<GanhosEntity, Integer>{
	

	
	List<GanhosEntity> findByInicialMes(String InicialMes);
}
