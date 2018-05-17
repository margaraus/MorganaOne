package br.com.potierp.model;

/**
 * 
 * @author Andrey Oliveira
 * @since 19/03/2012
 */
public enum MotivoMedidaDisciplinarEnum {

	FALTA_SEM_JUSTIFICATIVA("Falta sem justificativa"),
	
	FALTA_SEM_AVISO_COM_ANTECEDENCIA("Falta sem aviso com antecedência"),
	
	ATRASOS_CONSTANTES("Atrasos constantes"),
	
	NAO_USAR_UNIFORME("Não usar uniforme"),
	
	NAO_USAR_EPI("Não usar EPI"),
	
	NAO_USAR_EPI_CORRETAMENTE("Não usar EPI corretamente"),
	
	ABANDONO_POSTO_TRABALHO_SEM_AUTORIZACAO("Abandono de posto de trabalho sem autorização"),
	
	INSUBORDINACAO("Insubordinação"),
	
	DESRESPEITO_A_COLEGA_DE_TRABALHO("Desrespeito a colega de trabalho"),
	
	USO_VALE_TRANSPORTE_OUTRAS_FINALIDADES("Uso do Vale-Transporte para outras finalidades"),
	
	OUTROS("Outros");
	
	private String motivo;
	
	private MotivoMedidaDisciplinarEnum(final String motivo) {
		this.motivo = motivo;
	}
	
	public String getMotivo() {
		return motivo;
	}
}
