package com.gft.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.entities.Ingrediente;
import com.gft.mvc.entities.Item;
import com.gft.mvc.entities.Origem;
import com.gft.mvc.entities.Receita;
import com.gft.mvc.entities.UnidadeMedida;
import com.gft.mvc.repositories.IngredienteRepository;
import com.gft.mvc.repositories.ItemRepository;
import com.gft.mvc.repositories.ReceitaRepository;
import com.gft.mvc.repositories.UnidadeMedidaRepository;

@Service
public class PrincipalService {
	
	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void populaBD() {
		
		// Receita 1
		Receita receita1 = new Receita();
		receita1.setNome("Pizza de 4 Queijos");
		receita1.setTempoPreparo(2.00);
		receita1.setModoPreparo("Prepare os temperos, separe os queijos, coloque na massa e leve ao forno.");
		receitaRepository.save(receita1);
		
		Ingrediente ingrediente1 = new Ingrediente();
		ingrediente1.setNome("Orégano");
		ingrediente1.setOrigem(Origem.VEGETAL);
		ingredienteRepository.save(ingrediente1);
		
		Ingrediente ingrediente2 = new Ingrediente();
		ingrediente2.setNome("Cebolinha picada");
		ingrediente2.setOrigem(Origem.VEGETAL);
		ingredienteRepository.save(ingrediente2);
		
		UnidadeMedida unidadeMedida1 = new UnidadeMedida();
		unidadeMedida1.setNome("Porção");
		unidadeMedidaRepository.save(unidadeMedida1);
		
		Item item1 = new Item();
		item1.setReceita(receita1);
		item1.setIngrediente(ingrediente1);
		item1.setUnidadeMedida(unidadeMedida1);
		item1.setQuantidade(3);
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setReceita(receita1);
		item2.setIngrediente(ingrediente2);
		item2.setQuantidade(2);
		item2.setUnidadeMedida(unidadeMedida1);
		itemRepository.save(item2);
		
		// Receita 2
		Receita receita2 = new Receita();
		receita2.setNome("Strogonoff");
		receita2.setTempoPreparo(1.00);
		receita2.setModoPreparo("Prepare a carne, cozinhe o arroz, misture e coloque batata palha.");
		receitaRepository.save(receita2);
		
		UnidadeMedida unidadeMedida2 = new UnidadeMedida();
		unidadeMedida2.setNome("Colher de sopa");
		unidadeMedidaRepository.save(unidadeMedida2);

		Item item3 = new Item();
		item3.setReceita(receita2);
		item3.setIngrediente(ingrediente1);
		item3.setUnidadeMedida(unidadeMedida1);
		item3.setQuantidade(2);
		itemRepository.save(item3);
		
		Item item4 = new Item();
		item4.setReceita(receita2);
		item4.setIngrediente(ingrediente2);
		item4.setUnidadeMedida(unidadeMedida2);
		item4.setQuantidade(1);
		itemRepository.save(item4);
		
		// Receita 3
		Receita receita3 = new Receita();
		receita3.setNome("Arroz Cozido");
		receita3.setTempoPreparo(1.00);
		receita3.setModoPreparo("Adicione duas xícaras de água para cada uma de arroz.");
		receitaRepository.save(receita3);
		
		Ingrediente ingrediente3 = new Ingrediente();
		ingrediente3.setNome("Arroz");
		ingrediente3.setOrigem(Origem.VEGETAL);
		ingredienteRepository.save(ingrediente3);
		
		Ingrediente ingrediente4 = new Ingrediente();
		ingrediente4.setNome("Sal");
		ingrediente4.setOrigem(Origem.MINERAL);
		ingredienteRepository.save(ingrediente4);
		
		UnidadeMedida unidadeMedida3 = new UnidadeMedida();
		unidadeMedida3.setNome("Xícara");
		unidadeMedidaRepository.save(unidadeMedida3);
		
		UnidadeMedida unidadeMedida4 = new UnidadeMedida();
		unidadeMedida4.setNome("Colher de chá");
		unidadeMedidaRepository.save(unidadeMedida4);
		
		Item item5 = new Item();
		item5.setReceita(receita3);
		item5.setIngrediente(ingrediente3);
		item5.setUnidadeMedida(unidadeMedida3);
		item5.setQuantidade(2);
		itemRepository.save(item5);
		
		Item item6 = new Item();
		item6.setReceita(receita3);
		item6.setIngrediente(ingrediente4);
		item6.setUnidadeMedida(unidadeMedida4);
		item6.setQuantidade(1);
		itemRepository.save(item6);
	}
}