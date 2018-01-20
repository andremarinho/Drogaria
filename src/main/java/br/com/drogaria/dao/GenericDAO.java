package br.com.drogaria.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> {

	@SuppressWarnings("unused")
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			System.out.println("Listando...");
			return resultado;
			
		} catch (Exception e) {
			throw e;
		}finally {
			sessao.close();
		}
	}
}
