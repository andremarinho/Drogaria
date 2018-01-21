package br.com.drograria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void salvar() {

		Estado estado = new Estado();
		estado.setNome("Alagoas");
		estado.setSigla("AL");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);

	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		for (Estado estado : resultado) {
			System.out.println(estado.getNome());
		}
	}

	@Test
	@Ignore
	public void carregar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(5L);
		System.out.println(estado.getNome());

	}

	@Test
	@Ignore
	public void excluir() {

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(2L);

		if (estado != null) {
			estadoDAO.excluir(estado);
		} else{
			System.out.println("Nenhum registro encontrado!");
		}
	}
	
	
	@Test
	public void editar() {

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(10L);

		if (estado != null) {
			estado.setNome("Atualizado");
			estadoDAO.atualizar(estado);
		} else{
			System.out.println("Nenhum registro encontrado!");
		}
	}
	
	
}
