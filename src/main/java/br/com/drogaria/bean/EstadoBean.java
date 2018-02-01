package br.com.drogaria.bean;


import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;

import br.com.drogaria.domain.Estado;


@ManagedBean
public class EstadoBean {

	
	private Estado estado;
	
	
	
	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public void salvar() {
		Messages.addGlobalInfo("Programação Web Java");
	}

}
