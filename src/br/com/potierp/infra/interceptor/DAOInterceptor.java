package br.com.potierp.infra.interceptor;

import java.lang.reflect.Field;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import br.com.potierp.dao.IBaseDao;
import br.com.potierp.infra.annotation.DAO;


/**
 * Interceptor respons�vel pela inje��o dos DAO's nos EJB's.
 *
 * @author 
 * 22/02/2010 14:21:13
 *    	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class DAOInterceptor {

	/**
	 * Logger.
	 */
	private final Logger logger = Logger.getLogger(DAOInterceptor.class);

	/**
	 * Fase em que o Bean ja existe.
	 *
	 * @param invocation
	 * @throws Exception
	 */
	@AroundInvoke
	public Object postConstruct(final InvocationContext invocation)
	throws Exception {
		verificaAnotacoes(invocation.getTarget());
		return invocation.proceed();
	}

	/**
	 * Verifica a exist�ncia da Anotacao de Injecao de <i>DAO</i>.
	 *
	 * @param handleDAO
	 * @throws Exception
	 */
	private void verificaAnotacoes(final Object handleDAO) throws Exception {
		Class<?> classe = handleDAO.getClass();
		Field[] fields = classe.getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(DAO.class))
				injetaDAO(field, handleDAO);
		}
	}

	/**
	 * Metodo respons�vel pela inje��o do DAO no EJB.
	 *
	 * @param field
	 * @param handleDAO
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void injetaDAO(final Field field, final Object handleDAO)
	throws Exception {
		Class dao = field.getType();

		// Realiza a instancia do DAO
		IBaseDao baseDao = (IBaseDao) dao.newInstance();

		boolean acessivel = field.isAccessible();
		field.setAccessible(true);

		logger.debug("Injetando " + dao.getName() + " em "	+ handleDAO.getClass().getName());

		// seta a instancia do DAO no field
		field.set(handleDAO, baseDao);
		field.setAccessible(acessivel);
	}
}