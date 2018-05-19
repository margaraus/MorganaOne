package br.com.potierp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CtpsEstrangeiro implements Serializable{

	private static final long serialVersionUID = -4775396103998237882L;
	
	@Column(name = "ctpsEstrangeiro")
	private String ctps;
	
	@Column(name = "dataEmissaoCtpsEstrangeiro")
	private Date dataEmissao;
	
	@Column(name = "dataValidadeCtpsEstrangeiro")
	private Date dataValidade;

	public String getCtps() {
		return ctps;
	}

	public void setCtps(final String ctps) {
		this.ctps = ctps;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(final Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(final Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public String toString() {
		return "CtpsEstrangeiro [ctps=" + ctps + ", dataEmissao=" + dataEmissao
				+ ", dataValidade=" + dataValidade + "]";
	}
}