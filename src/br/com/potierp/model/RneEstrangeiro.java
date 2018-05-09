package br.com.potierp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RneEstrangeiro implements Serializable{

	private static final long serialVersionUID = 6528804370276578297L;
	
	private String rne;
	
	@Column(name = "dataValidadeRne")
	private Date dataValidade;
	
	@Column(name = "tipoVistoRne")
	private String tipoVisto;

	public String getRne() {
		return rne;
	}

	public void setRne(final String rne) {
		this.rne = rne;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(final Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getTipoVisto() {
		return tipoVisto;
	}

	public void setTipoVisto(final String tipoVisto) {
		this.tipoVisto = tipoVisto;
	}

	@Override
	public String toString() {
		return "RneEstrangeiro [rne=" + rne + ", dataValidade=" + dataValidade
				+ ", tipoVisto=" + tipoVisto + "]";
	}
}