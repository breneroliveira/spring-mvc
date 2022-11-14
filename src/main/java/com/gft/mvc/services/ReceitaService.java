package com.gft.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.entities.Receita;
import com.gft.mvc.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	public Receita salvarReceita(Receita receita) {
		
		return receitaRepository.save(receita);
	}
	
	public List<Receita> listarReceita(String nome, String nomeIngrediente) {
		
		if(nome != null || nomeIngrediente != null)
			return listarReceitaPorNomeENomeIngrediente(nome, nomeIngrediente);
		
		return listarTodasReceita();
	}
	
	public List<Receita> listarTodasReceita() {
		
		return receitaRepository.findAll();
	}
	
	private List<Receita> listarReceitaPorNomeENomeIngrediente(String nome, String nomeIngrediente) {

		return receitaRepository.findByNomeContainsAndItensIngredienteNomeContains(nome, nomeIngrediente);
	}
	
	public Receita obterReceita(Long id) throws Exception {
		
		Optional<Receita> receita = receitaRepository.findById(id);
		
		if(receita.isEmpty()) {
			throw new Exception("Receita não encontrada.");
		}
		
		return receita.get();
	}
	
	public void excluirReceita(Long id) {
		
		receitaRepository.deleteById(id);
	}
}