package br.com.drograria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void salvar(){
		Cidade cidade = new Cidade();
		cidade.setNome("Maceio");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(3L);
		System.out.println(estado.getNome());
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}
	
	@Test
	@Ignore
	public void listar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		for(Cidade cidade:resultado){
			System.out.print(cidade.getNome()+" ");
			System.out.print(cidade.getEstado().getNome()+" - ");
			System.out.print(cidade.getEstado().getSigla());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(2L);
		System.out.println(cidade.getNome());
	}
	
	@Test
	@Ignore
	public void excluir(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(7L);
		cidadeDAO.excluir(cidade);
	}
	
	
}
