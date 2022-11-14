package com.gft.mvc.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_ingrediente")
public class Ingrediente {
	
	@Id
	@Column(name = "ingrediente_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome não pode estar vazio.")
	@Column(name = "ingrediente_nome")
	private String nome;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Origem origem;
	
	@OneToMany(mappedBy = "ingrediente")
	private List<Item> itens;
	
	public Ingrediente() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setReceitaIngredientes(List<Item> itens) {
		this.itens = itens;
	}
}