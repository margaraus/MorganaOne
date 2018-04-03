package br.com.potierp.dao;

import static br.com.potierp.infra.msg.MensagensExceptionEnum.ERRO_ENTITY_MANAGER;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

/**
 * Factory responsavel pela geracao da instancia do EntityManager.
 *
 * @author 
 * 07/06/2010
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class FactoryEntityManager {

	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(FactoryEntityManager.class);

	/**
	 * Construtor default.
	 */
	private FactoryEntityManager() {

	}

	/**
	 * Metodo respons�vel pela gera��o da instancia do EntityMnager.
	 * @return
	 * @throws Exception
	 */
	public static EntityManager getEntityManager() throws Exception {
		try {
			Context contexto = new InitialContext();
			return (EntityManager)contexto.lookup("java:/unitErpBusiness");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new Exception(ERRO_ENTITY_MANAGER.getMsg() + ex.getMessage(), ex);
		}
	}

}