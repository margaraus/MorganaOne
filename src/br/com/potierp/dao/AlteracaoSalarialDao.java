package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.AlteracaoSalarial;
import br.com.potierp.model.BaseEntity;

/**
 * @author Andrey Oliveira
 */
public class AlteracaoSalarialDao extends BaseDao {

	public AlteracaoSalarialDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return AlteracaoSalarial.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return AlteracaoSalarial.class;
	}
	
	
	
}
