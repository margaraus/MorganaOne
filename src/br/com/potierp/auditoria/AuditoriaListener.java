package br.com.potierp.auditoria;

import static br.com.potierp.infra.msg.MensagensExceptionEnum.ERRO_AUDITORIA;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.log.TraceInfo;
import br.com.potierp.model.AuditoriaCadastro;
import br.com.potierp.util.EntityManagerUtil;
import br.com.potierp.util.TraceInfoUtil;

/**
 * Classe responsável pela realização da auditoria.
 * 
 * @author 
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 *
 */
public class AuditoriaListener {
	
	/**
	 * Logger.
	 */
	private Logger logger = Logger.getLogger(AuditoriaListener.class);
	
	/**
	 * Insert.
	 */
	private static final String INSERT  = "I";
	
	/**
	 * Insert.
	 */
	private static final String UPDATE  = "A";
	
	/**
	 * Insert.
	 */
	private static final String REMOVE  = "E";
	
	/**
	 * Construtor.
	 */
	public AuditoriaListener(){}
	
	/**
	 * Metodo responsável pelo log das operações de inserção.
	 * 
	 * @param entity
	 * @throws PotiErpException
	 */
	@PrePersist
	public void insert(final Auditavel entity) throws PotiErpException {
		try {
			if(entity instanceof Auditavel)
				auditar(INSERT, entity);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new PotiErpException(ERRO_AUDITORIA, new Object[]{entity.getTableName()}, ex);
		}
	}
	
	/**
	 * Metodo responsável pelo log das operações de alteração.
	 * 
	 * @param entity
	 * @throws PotiErpException
	 */
	@PreUpdate
	public void update(final Auditavel entity) throws PotiErpException {
		try {
			if(entity instanceof Auditavel)
				auditar(UPDATE, entity);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new PotiErpException(ERRO_AUDITORIA, new Object[]{entity.getTableName()}, ex);
		}
	}
	
	/**
	 * Metodo responsável pelo log das operações de exclusão.
	 * 
	 * @param entity
	 * @throws PotiErpException
	 */
	@PreRemove
	public void remove(final Auditavel entity) throws PotiErpException {
		try {
			if(entity instanceof Auditavel)
				auditar(REMOVE, entity);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new PotiErpException(ERRO_AUDITORIA, new Object[]{entity.getTableName()}, ex);
		}
	}
	
	/**
	 * Metodo responsável pela realização da auditoria.
	 * 
	 * @param tipoOperacao
	 * @throws DaoException 
	 */
	private void auditar(final String tipoOperacao, final Auditavel entity) throws DaoException{
		TraceInfo traceInfo = TraceInfoUtil.getCurrentTraceInfo();
		EntityManager em = EntityManagerUtil.getCurrentEntityManager();
		if(em != null && traceInfo != null){
			AuditoriaCadastro auditoriaCadastro = new AuditoriaCadastro();
			auditoriaCadastro.setDataOperacao(new Date());
			auditoriaCadastro.setIp(traceInfo.getIp());
			auditoriaCadastro.addUsuario(traceInfo.getUsuario());
			auditoriaCadastro.setTipoOperacao(tipoOperacao);
			auditoriaCadastro.setNomeTabela(entity.getTableName());
			auditoriaCadastro.setDescricaoOperacao(entity.auditoria());

			logger.info("Log de auditoria na tabela " + entity.getTableName());
			em.persist(auditoriaCadastro);
		}
	}
}