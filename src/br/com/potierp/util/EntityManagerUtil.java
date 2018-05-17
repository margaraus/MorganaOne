package br.com.potierp.util;

import javax.persistence.EntityManager;

/**
 *  Encapsula uma <b>ThreadLocal</b> para <b><i>EntityManager</i></b>.
 *  
 * @author 
 *  	   <p>
 *         $LastChangedBy:$
 *         <p>
 *         $LastChangedDate: $
 */
public class EntityManagerUtil {
	
	/**
	 * Construtor privado.
	 */
	private EntityManagerUtil(){
	}

	/**
	 * ThreadLocal.
	 */
	private static ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();

	/**
	 * @return currentEntityManager.
	 */
	public static EntityManager getCurrentEntityManager() {
		return currentEntityManager.get();
	}

	/**
	 * @param entityMangager Configura entityMangager.
	 */
	public static void setCurrentEntityManager(final EntityManager entityMangager) {
		currentEntityManager.set(entityMangager);
	}

	/**
	 * Libera o EntityManager associado com a Thread.
	 */
	public static  void clean() {
		currentEntityManager.remove();
	}
}