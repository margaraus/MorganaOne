package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.CestaBasica;

public class CestaBasicaDao extends BaseDao {

	public CestaBasicaDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return CestaBasica.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return CestaBasica.class;
	}
}