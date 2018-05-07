package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@Entity
@Table(name="telefone")
public class Telefone extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = 3926650722254302697L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String numero;
	
	private String ramal;
	
	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
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

	public String getRamal() {
		return ramal;
	}

	public void setRamal(final String ramal) {
		this.ramal = ramal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((ramal == null) ? 0 : ramal.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Telefone))
			return false;
		Telefone other = (Telefone) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (ramal == null) {
			if (other.ramal != null)
				return false;
		} else if (!ramal.equals(other.ramal))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", ramal=" + ramal
				+ "]";
	}

	@Override
	public Telefone clone() throws CloneNotSupportedException {
		Telefone telefone = (Telefone)super.clone();
		return telefone;
	}
}