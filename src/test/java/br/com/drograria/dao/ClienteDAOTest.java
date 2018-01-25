package br.com.drograria.dao;

import java.util.Date;

import org.hibernate.secure.spi.PermissibleAction;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ClienteDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

public class ClienteDAOTest {

	@Test
	public void salvar(){
		
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
		
	}
}
