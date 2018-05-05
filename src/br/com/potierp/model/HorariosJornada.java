package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = HorariosJornada.GET_ALL,
				    query = "FROM HorariosJornada ")
		}
)

@Entity
@Table(name="HorariosJornada")
public class HorariosJornada extends BaseEntityPotiErp implements Cloneable {

	private static final long serialVersionUID = -2120115758610349872L;
	
	/**
	 * NQ para buscar todos os horarios da jornada de trabalho.
	 */
	public static final String GET_ALL = "HorariosJornada.getAll";
	
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated
	private DiaSemanaEnum diaSemana;
	
	private String horarioInicial;
	
	private String horarioFinal;
	
	private String intervaloInicial;
	
	private String intervaloFinal;
	
	private Boolean diaSeguinte;
	
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
	
	public DiaSemanaEnum getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(final DiaSemanaEnum diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public String getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(final String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public String getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(final String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public String getIntervaloInicial() {
		return intervaloInicial;
	}

	public void setIntervaloInicial(final String intervaloInicial) {
		this.intervaloInicial = intervaloInicial;
	}

	public String getIntervaloFinal() {
		return intervaloFinal;
	}

	public void setIntervaloFinal(final String intervaloFinal) {
		this.intervaloFinal = intervaloFinal;
	}
	
	public Boolean getDiaSeguinte() {
		return diaSeguinte;
	}

	public void setDiaSeguinte(final Boolean diaSeguinte) {
		this.diaSeguinte = diaSeguinte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diaSeguinte == null) ? 0 : diaSeguinte.hashCode());
		result = prime * result
				+ ((diaSemana == null) ? 0 : diaSemana.hashCode());
		result = prime * result
				+ ((horarioFinal == null) ? 0 : horarioFinal.hashCode());
		result = prime * result
				+ ((horarioInicial == null) ? 0 : horarioInicial.hashCode());
		result = prime * result
				+ ((intervaloFinal == null) ? 0 : intervaloFinal.hashCode());
		result = prime
				* result
				+ ((intervaloInicial == null) ? 0 : intervaloInicial.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HorariosJornada))
			return false;
		HorariosJornada other = (HorariosJornada) obj;
		if (diaSeguinte == null) {
			if (other.diaSeguinte != null)
				return false;
		} else if (!diaSeguinte.equals(other.diaSeguinte))
			return false;
		if (diaSemana != other.diaSemana)
			return false;
		if (horarioFinal == null) {
			if (other.horarioFinal != null)
				return false;
		} else if (!horarioFinal.equals(other.horarioFinal))
			return false;
		if (horarioInicial == null) {
			if (other.horarioInicial != null)
				return false;
		} else if (!horarioInicial.equals(other.horarioInicial))
			return false;
		if (intervaloFinal == null) {
			if (other.intervaloFinal != null)
				return false;
		} else if (!intervaloFinal.equals(other.intervaloFinal))
			return false;
		if (intervaloInicial == null) {
			if (other.intervaloInicial != null)
				return false;
		} else if (!intervaloInicial.equals(other.intervaloInicial))
			return false;
		return true;
	}

	@Override
	public HorariosJornada clone() throws CloneNotSupportedException {
		HorariosJornada horarioJornada = (HorariosJornada)super.clone();
		return horarioJornada;
	}
}