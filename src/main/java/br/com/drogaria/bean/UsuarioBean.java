package br.com.drogaria.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.dao.UsuarioDAO;
import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	private Usuario usuario;

	@PostConstruct
	public void init() {
		this.listar();
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void listar() {

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			this.usuarios = usuarioDAO.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar listar usuarios");
			e.printStackTrace();
		}

	}

	public void novo() {

		try {
			this.usuario = new Usuario();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException ex) {
			Messages.addGlobalError("Ocorreu erro ao criar novo usuario");
			ex.getMessage();
		}

	}
	
	public void salvar(){
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);
			Messages.addGlobalInfo("Usuario salvo com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuario");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		Usuario usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			Messages.addGlobalInfo("Usuario excluido com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o usuario");
			e.printStackTrace();
		}
	}

}
