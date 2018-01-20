package br.com.drograria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Estado;

public class EstadoDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		
	}

	@Test
	public void listar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		for(Estado estado:resultado){
			System.out.println(estado.getNome());
		}
	}
}
