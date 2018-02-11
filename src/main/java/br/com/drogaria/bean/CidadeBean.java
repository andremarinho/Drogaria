package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;

	private List<Cidade> cidades;
	private List<Estado> estados;

	@PostConstruct
	public void init() {
		this.listar();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public void listar() {
		try {

			CidadeDAO cidadeDAO = new CidadeDAO();
			this.cidades = cidadeDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}

	public void novo() {
		this.cidade = new Cidade();

		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			this.estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		
		try {
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			this.cidade = new Cidade();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			this.estados = estadoDAO.listar();
			
			this.cidades = cidadeDAO.listar();
			
			Messages.addGlobalInfo("Cidade Salva com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma cidade");
			erro.printStackTrace();
		}
		
		
	}
	
	public void excluir(ActionEvent actionEvent) {
		this.cidade = (Cidade) actionEvent.getComponent().getAttributes().get("cidadeSelecionada");
				
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);
			this.listar();
			Messages.addGlobalInfo("Cidade removido com sucesso");
		} catch (Exception e) {
			Messages.addGlobalError("Não é possivel deletar o cidade, erro:  "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent actionEvent){
		
		
		try {
			this.cidade = (Cidade) actionEvent.getComponent().getAttributes().get("cidadeSelecionada");
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.atualizar(cidade);
			
			EstadoDAO estadoDAO = new EstadoDAO();
			this.estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}

	}

	
	
	public void teste(){
		System.out.println("Alterou o valor");
	}

}
