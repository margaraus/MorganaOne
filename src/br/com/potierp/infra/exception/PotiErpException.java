package br.com.potierp.infra.exception;

import java.text.MessageFormat;

import javax.ejb.ApplicationException;

import br.com.potierp.infra.msg.MensagensExceptionEnum;

/**
 * Exception referente ao projeto PotiERP-Business.
 *
 * @author 
 * 04/02/2010
 *    	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
@ApplicationException(rollback = true)
public class PotiErpException extends Exception{

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 217019478293617325L;

	/**
	 * Construtor.
	 */
	public PotiErpException() {
		super();
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 */
	public PotiErpException(final String message) {
		super(message);
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param cause
	 */
	public PotiErpException(final MensagensExceptionEnum message,
			final Throwable cause) {
		super(message.getMsg(), cause);
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param params
	 * @param cause
	 */
	public PotiErpException(final MensagensExceptionEnum message,
			final Object[] params, final Throwable cause) {
		super(MessageFormat.format(message.getMsg(), params), cause);
	}

	/**
	 * Construtor.
	 * @param message
	 */
	public PotiErpException(final MensagensExceptionEnum message){
		super(message.getMsg());
	}

	/**
	 * Construtor.
	 *
	 * @param throwable
	 */
	public PotiErpException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * Construtor.
	 *
	 * @param message
	 * @param throwable
	 */
	public PotiErpException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
