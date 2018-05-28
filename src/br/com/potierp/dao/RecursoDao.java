package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Recurso;

public class RecursoDao extends BaseDao {

	public RecursoDao() throws DaoException {
		super();
	}
	
	public Recurso getPorDescricao(final Recurso recurso) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(Recurso.GET_BY_DESCRICAO);
			query.setParameter("descricao", recurso.getDescricao().trim());
			return getSingleResult(query);
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"RECURSO"}, ex);
		}
	}
	
	public Recurso getPorCodigo(final Recurso recurso) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(Recurso.GET_BY_CODIGO);
			query.setParameter("codigo", recurso.getCodigo().trim());
			return getSingleResult(query);
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"RECURSO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Recurso.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Recurso.class;
	}

}
