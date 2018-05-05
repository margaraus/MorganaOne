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
 * 
 * @author Andrey Oliveira
 * @since 19/03/2012
 * 
 */
@NamedQueries({ @NamedQuery(name = MedidaDisciplinar.GET_ALL, query = "FROM MedidaDisciplinar ") })
@Entity
@Table(name = "medidadisciplinar")
public class MedidaDisciplinar extends BaseEntityPotiErp implements Cloneable {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -741847514574100630L;

	public static final String GET_ALL = "MedidaDisciplinar.getAll";

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dataInicialOcorrencia;

	@Temporal(TemporalType.DATE)
	private Date dataFinalOcorrencia;

	@Enumerated(EnumType.STRING)
	private MotivoMedidaDisciplinarEnum motivo;

	@Enumerated(EnumType.STRING)
	private MedidaDisciplinarAdotadaEnum medidaAdotada;

	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;

	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicialOcorrencia() {
		return dataInicialOcorrencia;
	}

	public void setDataInicialOcorrencia(Date dataInicialOcorrencia) {
		this.dataInicialOcorrencia = dataInicialOcorrencia;
	}

	public Date getDataFinalOcorrencia() {
		return dataFinalOcorrencia;
	}

	public void setDataFinalOcorrencia(Date dataFinalOcorrencia) {
		this.dataFinalOcorrencia = dataFinalOcorrencia;
	}

	public MotivoMedidaDisciplinarEnum getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoMedidaDisciplinarEnum motivo) {
		this.motivo = motivo;
	}

	public MedidaDisciplinarAdotadaEnum getMedidaAdotada() {
		return medidaAdotada;
	}

	public void setMedidaAdotada(MedidaDisciplinarAdotadaEnum medidaAdotada) {
		this.medidaAdotada = medidaAdotada;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public MedidaDisciplinar clone() throws CloneNotSupportedException {
		return (MedidaDisciplinar) super.clone();
	}
}