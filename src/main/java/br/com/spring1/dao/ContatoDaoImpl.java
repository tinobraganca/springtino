package br.com.spring1.dao;

import br.com.spring1.Contato;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("dao")
public class ContatoDaoImpl implements ContatoDao{
	
	protected Class getClazz() {
        return Contato.class;
    }
	
	@Autowired
	private SessionFactory sessionFactory;

    protected Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sf) {
        sessionFactory = sf;
    }
    public Contato getUsuario(String login, String senha) {
        Query query = getSession().createQuery("from Usuario u where u.login = ? and u.hashSenha = ?");
        query.setString(0, login);
        return (Contato) query.uniqueResult();
    }

//	@Override
//	public Contato getContato(String id) {
//		Query query = getSession().createQuery("from contato where id = ?");
//		query.setString(0, id);
//		return (Contato) query;
//		
//	}

	@Override
	public Contato get(Long id) {
		// TODO Auto-generated method stub
		return (Contato) getSession().get(getClazz(), id);
	}

	@Override
	public void excluir(Contato contato) {
		// TODO Auto-generated method stub
		getSession().delete(contato);
	}

	@Override
	public List<Contato> list(int offset, int max) {
		// TODO Auto-generated method stub
        return (List<Contato>) getSession().createCriteria(getClazz()).setMaxResults(max).setFirstResult(offset).list();
	}

	@Override
	public void persistir(Contato contato) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(contato);
	}

	@Override
	public Contato getContato(String Nome) {
		// TODO Auto-generated method stub
		return null;
	}
	



}
