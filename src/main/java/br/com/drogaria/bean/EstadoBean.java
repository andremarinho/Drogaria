package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.management.RuntimeErrorException;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> estados;

	public Estado getEstado() {

		return estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void salvar() {

		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);

			Messages.addGlobalInfo("Estado salvo com sucesso!!!");
			novo();
			this.listar();

		} catch (RuntimeException e) {
			Messages.addError(null, "Aconteceu erro ao tentar salvar", null);
			e.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {

		try {

			EstadoDAO estadoDAO = new EstadoDAO();
			this.estados = estadoDAO.listar();

		} catch (Exception e) {
			Messages.addError(null, "Aconteceu um erro ao tentar acessar o banco", null);
		}
	}

	public void novo() {
		this.estado = new Estado();
	}

	public void excluir(ActionEvent actionEvent) {
		this.estado = (Estado) actionEvent.getComponent().getAttributes().get("estadoSelecionado");
				
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			this.listar();
			Messages.addGlobalInfo("Estado removido com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Não é possivel deletar o estado, erro:  "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent actionEvent){
		this.estado = (Estado) actionEvent.getComponent().getAttributes().get("estadoSelecionado");
		
		
	}

}
