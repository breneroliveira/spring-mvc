package com.gft.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.entities.UnidadeMedida;
import com.gft.mvc.repositories.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public UnidadeMedida salvarUnidadeMedida(UnidadeMedida unidadeMedida) {
		
		return unidadeMedidaRepository.save(unidadeMedida);
	}
	
	public List<UnidadeMedida> listarUnidadeMedida(String nome) {
		
		if(nome != null)
			return listarUnidadeMedidaPorNome(nome);
		
		return listarTodasUnidadeMedida();
	}
	
	public List<UnidadeMedida> listarTodasUnidadeMedida() {
		
		return unidadeMedidaRepository.findAll();
	}
	
	private List<UnidadeMedida> listarUnidadeMedidaPorNome(String nome) {
		
		return unidadeMedidaRepository.findByNomeContains(nome);
	}
	
	public UnidadeMedida obterUnidadeMedida(Long id) throws Exception {
		
		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);
		
		if(unidadeMedida.isEmpty()) {
			throw new Exception("Unidade de medida não encontrada.");
		}
		
		return unidadeMedida.get();
	}
	
	public void excluirUnidadeMedida(Long id) {
		
		unidadeMedidaRepository.deleteById(id);
	}
}