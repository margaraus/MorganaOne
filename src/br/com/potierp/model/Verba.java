package br.com.potierp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.StringUtils;

@NamedQueries(
		{
		@NamedQuery(name = Verba.GET_ALL,
				    query = "FROM Verba "),
	    @NamedQuery(name = Verba.GET_BY_CODIGO,
			    query = "FROM Verba t " +
			    		"WHERE t.codigo = :codigo ")
		}
)

@Entity
@Table(name="verba")
public class Verba extends BaseEntityPotiErp implements Cloneable, Comparable<Verba>{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todos os tipos de cesta básica.
	 */
	public static final String GET_ALL = "Verba.getAll";
	
	/**
	 * NQ para buscar tipos de cesta básica de acordo com o codigo. 
	 */
	public static final String GET_BY_CODIGO = "Verba.getByCodigo";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String codigo;
	
	private String nome;
	
	private String descricao;
	
	@Column(name="base")
	@Enumerated(EnumType.STRING)
	private BaseVerbasEnum baseVerbas;
	
	@Column(name="sobre")
	@Enumerated(EnumType.STRING)
	private VerbasSobreEnum verbasSobre;
	
	private String percentual;
	
	@Enumerated(EnumType.STRING)
	private TipoVerbaEnum tipo;
	
	@Column(name="atestado")
	private Boolean isAtestado;

	@Column(name="abono")
	private Boolean isAbono;
	
	@Column(name="dsr")
	private Boolean isDsr;
	
	@Column(name="valeTransporte")
	private Boolean isValeTransporte;
	
	@Column(name="valeRefeicao")
	private Boolean isValeRefeicao;
	
	@Column(name="cestaBasica")
	private Boolean isCestaBasica;
	
	@Column(name="emprestimo")
	private Boolean isEmprestimo;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao;

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
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}
	
	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(final SituacaoEnum situacao) {
		this.situacao = situacao;
	}
	
	/**
	 * @return the baseVerbas
	 */
	public BaseVerbasEnum getBaseVerbas() {
		return baseVerbas;
	}

	/**
	 * @param baseVerbas the baseVerbas to set
	 */
	public void setBaseVerbas(final BaseVerbasEnum baseVerbas) {
		this.baseVerbas = baseVerbas;
	}

	/**
	 * @return the verbasSobre
	 */
	public VerbasSobreEnum getVerbasSobre() {
		return verbasSobre;
	}

	/**
	 * @param verbasSobre the verbasSobre to set
	 */
	public void setVerbasSobre(final VerbasSobreEnum verbasSobre) {
		this.verbasSobre = verbasSobre;
	}

	/**
	 * @return the percentual
	 */
	public String getPercentual() {
		return percentual;
	}

	/**
	 * @param percentual the percentual to set
	 */
	public void setPercentual(final String percentual) {
		this.percentual = percentual;
	}
	
	/**
	 * @return the tipo
	 */
	public TipoVerbaEnum getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(final TipoVerbaEnum tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return the isAtestado
	 */
	public Boolean getIsAtestado() {
		return isAtestado;
	}

	/**
	 * @param isAtestado the isAtestado to set
	 */
	public void setIsAtestado(final Boolean isAtestado) {
		this.isAtestado = isAtestado;
	}

	/**
	 * @return the isAbono
	 */
	public Boolean getIsAbono() {
		return isAbono;
	}

	/**
	 * @param isAbono the isAbono to set
	 */
	public void setIsAbono(final Boolean isAbono) {
		this.isAbono = isAbono;
	}

	/**
	 * @return the isDsr
	 */
	public Boolean getIsDsr() {
		return isDsr;
	}

	/**
	 * @param isDsr the isDsr to set
	 */
	public void setIsDsr(final Boolean isDsr) {
		this.isDsr = isDsr;
	}

	/**
	 * @return the isValeTransporte
	 */
	public Boolean getIsValeTransporte() {
		return isValeTransporte;
	}

	/**
	 * @param isValeTransporte the isValeTransporte to set
	 */
	public void setIsValeTransporte(final Boolean isValeTransporte) {
		this.isValeTransporte = isValeTransporte;
	}

	/**
	 * @return the isValeRefeicao
	 */
	public Boolean getIsValeRefeicao() {
		return isValeRefeicao;
	}

	/**
	 * @param isValeRefeicao the isValeRefeicao to set
	 */
	public void setIsValeRefeicao(final Boolean isValeRefeicao) {
		this.isValeRefeicao = isValeRefeicao;
	}

	/**
	 * @return the isCestaBasica
	 */
	public Boolean getIsCestaBasica() {
		return isCestaBasica;
	}

	/**
	 * @param isCestaBasica the isCestaBasica to set
	 */
	public void setIsCestaBasica(final Boolean isCestaBasica) {
		this.isCestaBasica = isCestaBasica;
	}

	/**
	 * @return the isEmprestimo
	 */
	public Boolean getIsEmprestimo() {
		return isEmprestimo;
	}

	/**
	 * @param isEmprestimo the isEmprestimo to set
	 */
	public void setIsEmprestimo(final Boolean isEmprestimo) {
		this.isEmprestimo = isEmprestimo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((baseVerbas == null) ? 0 : baseVerbas.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((percentual == null) ? 0 : percentual.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result
				+ ((verbasSobre == null) ? 0 : verbasSobre.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Verba)) {
			return false;
		}
		Verba other = (Verba) obj;
		if (baseVerbas != other.baseVerbas) {
			return false;
		}
		if (codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.equals(other.descricao)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (percentual == null) {
			if (other.percentual != null) {
				return false;
			}
		} else if (!percentual.equals(other.percentual)) {
			return false;
		}
		if (situacao != other.situacao) {
			return false;
		}
		if (verbasSobre != other.verbasSobre) {
			return false;
		}
		return true;
	}

	@Override
	public Verba clone() throws CloneNotSupportedException {
		return (Verba)super.clone();
	}
	
	@Override
	public int compareTo(final Verba outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, outro.getNome());
	}
}