package br.com.spring1.dao;
import br.com.spring1.Contato;
import java.util.List;


public interface ContatoDao {
	
	public Contato getContato (String Nome);
	
	public Contato get (Long id);
	
	public void excluir (Contato contato);
	
	public List<Contato> list (int offset,int max);
	
	public void persistir (Contato contato);

}

