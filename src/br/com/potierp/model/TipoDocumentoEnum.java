package br.com.potierp.model;

public enum TipoDocumentoEnum {
	
	CPF("CPF"),
	
	CNPJ("CNPJ"),
	
	RG("RG");
	
	private String tipoDocumento;
	
	private TipoDocumentoEnum(final String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}