package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.domain.Cidade;
import br.com.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	public List<Cidade> buscarPorEstado(Long codEstado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.codigo", codEstado));
			
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			System.out.println("Listando...");
			return resultado;

		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
	
}
