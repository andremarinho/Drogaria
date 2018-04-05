package br.com.drogaria.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.ClienteDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	private Cliente cliente;

	@PostConstruct
	public void init() {
		this.listar();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void listar() {
		try {

			ClienteDAO clienteDAO = new ClienteDAO();
			this.clientes = clienteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			this.cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu erro ao listar clientes");
			ex.getMessage();
		}

	}

	public void salvar() {

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			cliente = new Cliente();

			
			clientes = clienteDAO.listar();
			Messages.addGlobalInfo("Cliente salvo com sucesso.");
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar cliente.");
			e.printStackTrace();
		}

	}
	
	public void excluir(ActionEvent evento){
		Cliente cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);
			this.clientes = clienteDAO.listar();
			
			Messages.addGlobalError("Excluido cliente com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o cliente.");
			e.printStackTrace();
		}
	}

}
