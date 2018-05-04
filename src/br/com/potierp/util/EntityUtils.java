package br.com.potierp.util;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.model.BaseEntity;

/**
 * Classe utilitaria em opera��es com Entidades.
 * @see br.metodista.service.model.BaseEntity
 * 
 * @author
 */
public final class EntityUtils {

	/**
	 * Construtor default.
	 */
	public EntityUtils(){}

	/**
	 * Executa o m�todo getResultList de forma padrao sem se preocupar com cast.
	 * @param <T>
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getResultList(final Query query) {
		return query.getResultList();
	}

	/**
	 * @param entity
	 * @return Nome do Objeto, conforme a classe.
	 */
	public String getEntityName(final BaseEntity entity) {
		String name = entity.getClass().getName().substring(entity.getClass().getName().lastIndexOf('.')+1,
				entity.getClass().getName().length());
		return name;
	}

	/**
	 * A partir de um object obtem um Long.
	 *
	 * @param object
	 * @return
	 */
	public static Long getLong(final Object object) {
		if (object instanceof Number) {
			return ((Number) object).longValue();
		}
		if (object instanceof String) {
			return Long.valueOf((String) object);
		}
		return null;
	}

	/**
	 * A partir de um object obtem um Integer.
	 *
	 * @param object
	 * @return
	 */
	public static Integer getInteger(final Object object) {
		if (object instanceof Number) {
			return ((Number) object).intValue();
		}
		if (object instanceof String) {
			return Integer.valueOf((String) object);
		}
		return null;
	}

	/**
	 * A partir de um object obtem uma String.
	 *
	 * @param object
	 * @return
	 */
	public static String getString(final Object object) {
		if (object instanceof String) {
			return object.toString();
		}
		return null;
	}

	/**
	 * Verifica se dois objetos s�o iguais.
	 *
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean isEquals(final Object o1, final Object o2) {
		if (o1 == null) {
			return false;
		}
		return o1.equals(o2);
	}
}
