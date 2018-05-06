package br.com.potierp.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

import br.com.potierp.core.Identifiable;
import br.com.potierp.infra.bd.BaseEntityPotiErp;

@Entity
@Table(name="tipoperfil")
@NamedQueries({
	@NamedQuery(name = TipoPerfilErp.GET_ALL, query = "FROM TipoPerfilErp"),
	@NamedQuery(name = TipoPerfilErp.GET_BY_DESCRICAO, query = "FROM TipoPerfilErp t where UPPER(t.descricao) = UPPER(:descricao)")
})
public class TipoPerfilErp extends BaseEntityPotiErp implements Comparable<TipoPerfilErp>, Identifiable, Cloneable{

	private static final long serialVersionUID = 7764978656101654235L;
	
	/**
	 * NQ para buscar todos os TipoPerfil.
	 */
	public static final String GET_ALL = "TipoPerfilErp.getAll";
	
	/**
	 * NQ para buscar os TipoPerfil por Descrição.
	 */
	public static final String GET_BY_DESCRICAO = "TipoPerfilErp.getByDescricao";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private String sigla;
	
	@ManyToMany
	@JoinTable(name ="tipoperfil_funcionalidade",
			joinColumns = @JoinColumn(name="idTipoPerfil"),
			inverseJoinColumns = @JoinColumn(name="idFuncionalidade"))
	private Collection<Funcionalidade> funcionalidades;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(final String sigla) {
		this.sigla = sigla;
	}
	
	public Collection<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(final Collection<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	@Override
	public int compareTo(final TipoPerfilErp o) {
		return this.descricao.compareTo(o.descricao);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPerfilErp other = (TipoPerfilErp) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}
	
	public TipoPerfilErp clone() throws CloneNotSupportedException {
		return (TipoPerfilErp) super.clone();
	}
	
}