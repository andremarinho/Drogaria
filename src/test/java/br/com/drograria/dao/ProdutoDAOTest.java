package br.com.drograria.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {

	@Test
	public void salvar(){
		Produto produto = new Produto();
		produto.setDescricao("Cataflan");
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(1L);
		
		
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal(13.70));
		produto.setQuantidade(new Short("76"));
		
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
}
