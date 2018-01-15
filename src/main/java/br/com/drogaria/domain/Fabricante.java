package br.com.drogaria.domain;

import javax.persistence.Entity;

@Entity
public class Fabricante extends GenericDomain
{

	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
