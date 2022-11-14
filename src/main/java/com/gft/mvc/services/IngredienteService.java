package com.gft.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.entities.Ingrediente;
import com.gft.mvc.repositories.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public Ingrediente salvarIngrediente(Ingrediente ingrediente) {
		
		return ingredienteRepository.save(ingrediente);
	}
	
	public List<Ingrediente> listarIngrediente(String nome) {
		
		if(nome != null)
			return listarIngredientePorNome(nome);
		
		return listarTodosIngrediente();
	}
	
	public List<Ingrediente> listarTodosIngrediente() {
		
		return ingredienteRepository.findAll();
	}
	
	private List<Ingrediente> listarIngredientePorNome(String nome) {
		
		return ingredienteRepository.findByNomeContains(nome);
	}
	
	public Ingrediente obterIngrediente(Long id) throws Exception {
		
		Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
		
		if(ingrediente.isEmpty()) {
			throw new Exception("Ingrediente não encontrado.");
		}
		
		return ingrediente.get();
	}
	
	public void excluirIngrediente(Long id) {
		
		ingredienteRepository.deleteById(id);
	}
}