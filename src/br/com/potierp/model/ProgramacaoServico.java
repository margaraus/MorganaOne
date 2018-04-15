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

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * 
 * @author Andrey Oliveira
 *
 */
@NamedQueries(
		{
		@NamedQuery(name=ProgramacaoServico.GET_ALL,
					query=" FROM ProgramacaoServico "),
		@NamedQuery(name=ProgramacaoServico.GET_POR_CLIENTE,
					query=" SELECT ps " +
							"FROM ProgramacaoServico ps " +
							"inner join ps.cliente cl " +
							"WHERE cl.id = :idCliente"),
		@NamedQuery(name=ProgramacaoServico.GET_POR_TIPO_SERVICO,
					query=" SELECT ps " +
							"FROM ProgramacaoServico ps " +
							"inner join ps.tipoServico ts " +
							"WHERE ts.id = :idTipoServico")
		}
)
@Entity
@Table(name="programacaoservico")
public class ProgramacaoServico extends BaseEntityPotiErp implements Cloneable {
	
	private static final long serialVersionUID = 6626403474352814197L;

	public static final String GET_ALL = "ProgramacaoServico.getAll";
	
	public static final String GET_POR_CLIENTE = "ProgramacaoServico.getPorCliente";
	
	public static final String GET_POR_TIPO_SERVICO = "ProgramacaoServico.getPorTipoServico";

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idTipoServico")
	private TipoServico tipoServico;
	
	private Date dataPrevisao;
	
	private Date dataRealizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Date getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(Date dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataPrevisao == null) ? 0 : dataPrevisao.hashCode());
		result = prime * result
				+ ((dataRealizacao == null) ? 0 : dataRealizacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgramacaoServico other = (ProgramacaoServico) obj;
		if (dataPrevisao == null) {
			if (other.dataPrevisao != null)
				return false;
		} else if (!dataPrevisao.equals(other.dataPrevisao))
			return false;
		if (dataRealizacao == null) {
			if (other.dataRealizacao != null)
				return false;
		} else if (!dataRealizacao.equals(other.dataRealizacao))
			return false;
		return true;
	}
	
	@Override
	public ProgramacaoServico clone() throws CloneNotSupportedException {
		return (ProgramacaoServico)super.clone();
	}
}
