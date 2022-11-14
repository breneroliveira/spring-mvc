package com.gft.mvc.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_receita")
public class Receita {

	@Id
	@Column(name = "receita_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome não pode estar vazio.")
	@Column(name = "receita_nome")
	private String nome;
	
	@Column(nullable = false)
	private Double tempoPreparo;
	
	@NotEmpty(message = "Modo de preparo deve ser informado.")
	@Size(max=255)
	private String modoPreparo;

	@OneToMany(mappedBy = "receita", cascade = CascadeType.ALL)
	private List<Item> itens;
	
	public Receita() {
		
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

	public Double getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(Double tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Item> getItens() {
		return itens;
	}
}