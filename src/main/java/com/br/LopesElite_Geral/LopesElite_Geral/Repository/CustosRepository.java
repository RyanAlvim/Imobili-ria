package com.br.LopesElite_Geral.LopesElite_Geral.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.CustosEntity;

public interface CustosRepository extends JpaRepository<CustosEntity, Integer>{

	List<CustosEntity> findByInicialMes(String InicialMes);
}
