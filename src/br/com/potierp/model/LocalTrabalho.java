package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.StringUtils;

@NamedQueries(
		{
		@NamedQuery(name = LocalTrabalho.GET_POR_FUNCIONARIO_COM_SETOR,
					query = "select loct FROM LocalTrabalho loct " +
							"inner join fetch loct.setor se " +
							"inner join fetch loct.funcionario func " +
							"where func.codigoRegistro = :codigoRegistro ")	
		}
)
@Entity
@Table(name="localtrabalho")
public class LocalTrabalho extends BaseEntityPotiErp implements Cloneable, Comparable<LocalTrabalho>{

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -1474705298986534123L;
	
	public static final String GET_POR_FUNCIONARIO_COM_SETOR = "LocalTrabalho.getPorFuncionarioComSetor";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idJornadaTrabalho")
	private JornadaTrabalho jornadaTrabalho;
	
	@ManyToOne
	@JoinColumn(name= "idSetor")
	private Setor setor;
	
	@Enumerated
	private DiaSemanaEnum diaSemana;
	
	@Transient
	private Long identificador;

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
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	public JornadaTrabalho getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(final JornadaTrabalho jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(final Setor setor) {
		this.setor = setor;
	}

	public DiaSemanaEnum getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(final DiaSemanaEnum diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public Long getIdentificador() {
		if(Long.valueOf(0).equals(identificador)){
			this.identificador = null;
		}
		return identificador;
	}

	public void setIdentificador(final Long identificador) {
		this.identificador = identificador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diaSemana == null) ? 0 : diaSemana.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LocalTrabalho))
			return false;
		LocalTrabalho other = (LocalTrabalho) obj;
		if (diaSemana != other.diaSemana)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "LocalTrabalho [id=" + id + ", cliente=" + cliente
				+ ", jornadaTrabalho=" + jornadaTrabalho + ", setor=" + setor
				+ ", diaSemana=" + diaSemana + ", identificador="
				+ identificador + "]";
	}

	@Override
	public LocalTrabalho clone() throws CloneNotSupportedException {
		return (LocalTrabalho)super.clone();
	}
	
	@Override
	public int compareTo(final LocalTrabalho outro) {
		return StringUtils.compareNumbersInString(String.valueOf(this.getDiaSemana().ordinal()), String.valueOf(outro.getDiaSemana().ordinal()));
	}
}