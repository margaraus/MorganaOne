package br.com.potierp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.PagamentoValeTransporte;

public class PagamentoValeTransporteDao extends BaseDao {

	public PagamentoValeTransporteDao() throws DaoException {
		super();
	}
	
	public List<PagamentoValeTransporte> getPorCalculo(final Long idCalculo) throws Exception{
		try{
			Query query = getEntityManager().createNamedQuery(PagamentoValeTransporte.GET_POR_CALCULO);
			query.setParameter("idCalculo", idCalculo);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"PAGAMENTO VT"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return PagamentoValeTransporte.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return PagamentoValeTransporte.class;
	}
	
	public List<?> getReciboValeTransporte(Date dataInicial, Date dataFinal) {
		Query query = getEntityManager().createNamedQuery(PagamentoValeTransporte.GET_RECIBO_VALE_TRANSPORTE);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		List<?> listRecibo = query.getResultList(); 
		return listRecibo;
	}
}