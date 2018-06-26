package br.com.drogaria.bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.sound.midi.Patch;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

@ManagedBean
public class ProdutoBean {

	private List<Fabricante> fabricantes;
	private List<Produto> produtos;
	private Produto produto;

	@PostConstruct
	public void init() {
		this.listarProdutos();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		if (produto == null) {
			this.produto = new Produto();
		}
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		if (this.fabricantes == null) {
			this.fabricantes = this.listarFabricantes();
		}

		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Fabricante> listarFabricantes() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		this.fabricantes = fabricanteDAO.listar();
		return this.fabricantes;
	}

	public List<Produto> listarProdutos() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		this.produtos = produtoDAO.listar();
		return produtos;
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			this.listarProdutos();

			Messages.addGlobalInfo("Produto salvo com sucesso!!!");

		} catch (RuntimeException e) {
			Messages.addError(null, "Aconteceu erro ao tentar salvar", null);
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}
	}
	
	public void upload(FileUploadEvent evento){
		
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path temp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), temp, StandardCopyOption.values());
			produto.setCaminho(temp.toString());
			Messages.addGlobalInfo(produto.getCaminho());
		} catch (IOException e) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar criar o arquivo temporario");
		}
		
	}

}
