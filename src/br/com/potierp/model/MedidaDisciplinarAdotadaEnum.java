package br.com.potierp.model;

/**
 * 
 * @author Andrey Oliveira
 * @since 19/03/2012 
 *
 */
public enum MedidaDisciplinarAdotadaEnum {

	ADVERTENCIA("Advertência"),
	
	SUSPENSAO_1_DIA("Suspensão de 1 dia"),
	
	SUSPENSAO_2_DIAS("Suspensão de 2 dias");
	
	private String medidaDisciplinar;
	
	private MedidaDisciplinarAdotadaEnum(final String medidaDisciplinar) {
		this.medidaDisciplinar = medidaDisciplinar;
	}
	
	public String getMedidaDisciplinar() {
		return medidaDisciplinar;
	}
}
