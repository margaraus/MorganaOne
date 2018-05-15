package br.com.potierp.util;



/**
 * Classe utilitária geral do PotiErp.
 * 
 * @author 
 *         <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public final class PotiErpUtil {

	/**
	 * Construtor.
	 */
	private PotiErpUtil() {
	}

	/**
	 * String representando o booleano true do PotiErp.
	 */
	public static final String POTIERP_BOOLEAN_TRUE = "S";

	/**
	 * String representando o booleano false do PotiErp.
	 */
	public static final String POTIERP_BOOLEAN_FALSE = "N";

	/**
	 * String representando a situação Ativo.
	 */
	public static final String POTIERP_SITUACAO_ATIVO = "Ativo";

	/**
	 * String representando a situação Inativo.
	 */
	public static final String POTIERP_SITUACAO_INATIVO = "Inativo";

	/**
	 * Obtem um boolean, a partir de uma String booleana do Logos.
	 * @param value
	 * @return
	 */
	public static boolean getBoolean(final String value) {
		return POTIERP_BOOLEAN_TRUE.equals(value);
	}

	/**
	 * Obtem uma String booleana do Logos.
	 * @param value
	 * @return
	 */
	public static String getBoolean(final boolean value) {
		return value ? POTIERP_BOOLEAN_TRUE : POTIERP_BOOLEAN_FALSE;
	}
	
	/**
	 * Verifica se é uma String vazia.
	 * @param obj
	 * @return
	 */
	public static boolean isStringVazia(final Object obj){
		return  (EntityUtils.getString(obj) != null) && ("".equals(EntityUtils.getString(obj)));
	}
}