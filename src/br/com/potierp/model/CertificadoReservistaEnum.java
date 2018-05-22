package br.com.potierp.model;

public enum CertificadoReservistaEnum {
	
	DISPENSA("Dispensa"),
	
	RESERVA("Reserva");
	
	private String nome;
	
	private CertificadoReservistaEnum(final String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}