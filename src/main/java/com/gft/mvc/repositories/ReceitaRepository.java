package com.gft.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.mvc.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	List<Receita> findByNomeContains(String nome);
	List<Receita> findByNomeContainsAndItensIngredienteNomeContains(String nome, String nomeIngrediente);
}