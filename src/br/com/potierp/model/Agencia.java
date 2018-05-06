package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = Agencia.GET_ALL,
				    query = "FROM Agencia ")
		}
)

@Entity
@Table(name="agencia")
public class Agencia extends BaseEntityPotiErp{

	private static final long serialVersionUID = -505677965034627456L;
	
	/**
	 * NQ para buscar todas as Agencias.
	 */
	public static final String GET_ALL = "Agencia.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String numero;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "idBanco")
	private Banco banco;

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return this.id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(final String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(final Banco banco) {
		this.banco = banco;
	}
}