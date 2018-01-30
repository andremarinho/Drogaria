package br.com.drogaria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class EstadoBean {

	public void salvar() {
		FacesMessage messagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Programação Web com Java",
				"Programação Web com Java");
		FacesContext contexto = FacesContext.getCurrentInstance(); 
		contexto.addMessage(null, messagem);
	}

}
