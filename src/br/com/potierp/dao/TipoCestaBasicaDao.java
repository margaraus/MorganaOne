package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.TipoCestaBasica;

public class TipoCestaBasicaDao extends BaseDao {

	public TipoCestaBasicaDao() throws DaoException {
		super();
	}
	
	public TipoCestaBasica getPorCodigo(final TipoCestaBasica tipoCestaBasica) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(TipoCestaBasica.GET_BY_CODIGO);
			query.setParameter("codigo", tipoCestaBasica.getCodigo().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"TIPOCESTABASICA", "CODIGO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return TipoCestaBasica.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return TipoCestaBasica.class;
	}
}