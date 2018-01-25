package br.com.drograria.dao;

import org.junit.Test;

import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	
	@Test
	public void salvar(){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("André Bezerra Marinho");
		pessoa.setEmail("andre.bmarinho@hotmail.com");
		pessoa.setBairro("Saude");
		pessoa.setCelular("991391905");
		pessoa.setCpf("008.688.074-86");
		pessoa.setNumero(new Short("66"));
		pessoa.setRua("Rua Zafira Ataide Cerqueira");
		pessoa.setRg("5944809");
		pessoa.setCep("57000-000");
		pessoa.setTelefone("3721-1908");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
	}

}
