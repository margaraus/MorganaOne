package br.com.potierp.model;

public enum RumoTransporteEnum {
	
	IDA("IDA"),
	
	VOLTA("VOLTA");
	
	private String rumo;
	
	private RumoTransporteEnum(final String rumo) {
		this.rumo = rumo;
	}

	public String getRumo() {
		return rumo;
	}
}