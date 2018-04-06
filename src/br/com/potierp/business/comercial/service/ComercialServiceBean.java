package br.com.potierp.business.comercial.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.HistoricoComercialDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.HistoricoComercial;

@Stateless(mappedName="ComercialService", name="ComercialService")
@Local(ComercialService.class)
@Interceptors(DAOInterceptor.class)
public class ComercialServiceBean implements ComercialService{
	
	@DAO
	private HistoricoComercialDao historicoComercialDao;

	@Override
	public HistoricoComercial salvar(final HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException {
		try {
			return this.historicoComercialDao.salvar(historicoComercial);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"HISTORICOCOMERCIAL"}, e);
		}
	}

	@Override
	public void excluir(final HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException {
		try {
			this.historicoComercialDao.excluir(historicoComercial);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"HISTORICOCOMERCIAL"}, e);
		}
	}

	@Override
	public void excluir(final List<HistoricoComercial> listaHistoricoComercial) throws PotiErpMensagensException, PotiErpException {
		try {
			for(HistoricoComercial h : listaHistoricoComercial) {
				this.historicoComercialDao.excluir(h);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"HISTORICOCOMERCIAL"}, e);
		}
	}

	@Override
	public HistoricoComercial consultarPorId(final Long idHistoricoComercial) throws PotiErpException {
		try {
			return this.historicoComercialDao.getByPrimaryKey(idHistoricoComercial);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"HISTORICOCOMERCIAL"}, e);
		}
	}

	@Override
	public List<HistoricoComercial> consultarTodos() throws PotiErpException {
		try {
			Collection<HistoricoComercial> historicos = this.historicoComercialDao.getAll();
			return new ArrayList<HistoricoComercial>(historicos);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"HISTORICOCOMERCIAL"}, e);
		}
	}
	
	@Override
	public List<HistoricoComercial> consultarPorCliente(final Long idCliente) throws PotiErpException {
		try {
			Collection<HistoricoComercial> historicos = this.historicoComercialDao.getPorCliente(idCliente);
			return new ArrayList<HistoricoComercial>(historicos);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"HISTORICOCOMERCIAL"}, e);
		}
	}

}