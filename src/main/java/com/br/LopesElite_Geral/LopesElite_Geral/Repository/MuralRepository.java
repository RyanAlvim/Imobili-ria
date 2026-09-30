package com.br.LopesElite_Geral.LopesElite_Geral.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.LopesElite_Geral.LopesElite_Geral.Entity.MuralEntity;

@Repository
public interface MuralRepository extends JpaRepository<MuralEntity, Integer>{

}
