package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.AlteracaoCliente;
import br.com.potierp.model.BaseEntity;

public class AlteracaoClienteDao extends BaseDao {

	public AlteracaoClienteDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return AlteracaoCliente.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return AlteracaoCliente.class;
	}
}