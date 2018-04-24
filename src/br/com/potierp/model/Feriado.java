package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author Doug
 *
 */
@NamedQueries(
		{
		@NamedQuery(name=Feriado.GET_ALL,
					query="select f FROM Feriado f "),
		@NamedQuery(name=Feriado.GET_POR_CRITERIOS,
					query="select f FROM Feriado f WHERE " +
						  " f.nome = :nome and " +
						  " f.cidade.id = :idCidade and " +
						  " f.estado.id = :idEstado "),
		@NamedQuery(name=Feriado.GET_POR_DATA_INI_DATA_FIM,
					query="select f From Feriado f " +
						  "Where f.data between :dataInicial and :dataFinal ")
		}
)
@Entity
@Table(name="feriado")
public class Feriado extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 849002852698237328L;
	
	public static final String GET_ALL = "Feriado.getAll";
	
	public static final String GET_POR_CRITERIOS = "Feriado.getPorCriterios";
	
	public static final String GET_POR_DATA_INI_DATA_FIM = "Feriado.getPorDataInicialEFinal";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="idCidade")
	private Cidade cidade;
	
	@ManyToOne
	@JoinColumn(name="idEstado")
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name="idPais")
	private Pais pais;
	
	private Boolean isExpira;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoFeriadoEnum tipoFeriado;

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(final String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(final Cidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(final Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the pais
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(final Pais pais) {
		this.pais = pais;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(final Date data) {
		this.data = data;
	}

	/**
	 * @return the isExpira
	 */
	public Boolean getIsExpira() {
		return isExpira;
	}

	/**
	 * @param isExpira the isExpira to set
	 */
	public void setIsExpira(final Boolean isExpira) {
		this.isExpira = isExpira;
	}

	/**
	 * @return the tipoFeriado
	 */
	public TipoFeriadoEnum getTipoFeriado() {
		return tipoFeriado;
	}

	/**
	 * @param tipoFeriado the tipoFeriado to set
	 */
	public void setTipoFeriado(final TipoFeriadoEnum tipoFeriado) {
		this.tipoFeriado = tipoFeriado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((isExpira == null) ? 0 : isExpira.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Feriado))
			return false;
		Feriado other = (Feriado) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if(isExpira == null) {
			if(other.isExpira != null)
				return false;
		} else if(!isExpira.equals(other.isExpira))
			return false;
		return true;
	}

	@Override
	public Feriado clone() throws CloneNotSupportedException {
		return (Feriado)super.clone();
	}
}
