package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@Entity
@Table(name="tipotelefone")
public class TipoTelefone extends BaseEntityPotiErp{
	
	private static final long serialVersionUID = 4627521411127135343L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String tipo;

	@Override
	public Long getId() {
		return this.id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public void setId(final Long id) {
		this.id = id;
	}
}