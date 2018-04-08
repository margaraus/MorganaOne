package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
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

@NamedQueries(
		{
		@NamedQuery(name = AlteracaoCliente.GET_ALL,
					query = "FROM AlteracaoCliente ")	
		}
)
@Entity
@Table(name="alteracaocliente")
public class AlteracaoCliente extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 5835321714091821877L;

	public static final String GET_ALL = "AlteracaoCliente.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTransferencia;
	
	@ManyToOne
	@JoinColumn(name = "idClienteAnterior")
	private Cliente clienteAnterior;
	
	@ManyToOne
	@JoinColumn(name = "idClienteAtual")
	private Cliente clienteAtual;
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	/**
	 * @return the dataTransferencia
	 */
	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	/**
	 * @param dataTransferencia the dataTransferencia to set
	 */
	public void setDataTransferencia(final Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
	
	/**
	 * @return the clienteAnterior
	 */
	public Cliente getClienteAnterior() {
		return clienteAnterior;
	}

	/**
	 * @param clienteAnterior the clienteAnterior to set
	 */
	public void setClienteAnterior(final Cliente clienteAnterior) {
		this.clienteAnterior = clienteAnterior;
	}

	/**
	 * @return the clienteAtual
	 */
	public Cliente getClienteAtual() {
		return clienteAtual;
	}

	/**
	 * @param clienteAtual the clienteAtual to set
	 */
	public void setClienteAtual(final Cliente clienteAtual) {
		this.clienteAtual = clienteAtual;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dataTransferencia == null) ? 0 : dataTransferencia
						.hashCode());
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
		if (!(obj instanceof AlteracaoCliente)) {
			return false;
		}
		AlteracaoCliente other = (AlteracaoCliente) obj;
		if (dataTransferencia == null) {
			if (other.dataTransferencia != null) {
				return false;
			}
		} else if (!dataTransferencia.equals(other.dataTransferencia)) {
			return false;
		}
		return true;
	}

	@Override
	public AlteracaoCliente clone() throws CloneNotSupportedException {
		return (AlteracaoCliente)super.clone();
	}	
}