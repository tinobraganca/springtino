package br.com.spring1;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;



@Entity
@Table(name = "contato") // @Table is optional, but "user" is a keyword in many SQL variants 
public class Contato implements java.io.Serializable {
	
	@Id // @Id indicates that this it a unique primary key
	@GeneratedValue (strategy = GenerationType.IDENTITY)// @GeneratedValue indicates that value is automatically generated by the server
    @Column(name = "id", unique = true )
	private Long id;
	
	
	@NotNull
	@NotEmpty
	@Size(min=5, message="O nome não pode ter menos que 5 caracteres!")
	@Column(name = "nome" ,length = 37, unique = true)
	private String nome;
	
	@NotNull
	@Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message="E-mail com formato incorreto.")
	@Column (name = "email" ,length = 40 ,unique = true )
	private String email;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "\\b?([0-9]{4})[-. ]?([0-9]{4})\\b", message="Telefone em formato incorreto")
	@Column(name = "telefone" )
	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}

