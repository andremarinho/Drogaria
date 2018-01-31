package br.com.drogaria.bean;


import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;


@ManagedBean
public class EstadoBean {

	public void salvar() {
		Messages.addGlobalInfo("Programação Web Java");
	}

}
