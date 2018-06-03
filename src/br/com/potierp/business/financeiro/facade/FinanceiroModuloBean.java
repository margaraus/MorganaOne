package br.com.potierp.business.financeiro.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import br.com.potierp.business.financeiro.service.SolicitacaoPagamentoService;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.SessionBDInterceptorPoti;
import br.com.potierp.model.SolicitacaoPagamento;

@Stateless(mappedName="FinanceiroModulo", name="FinanceiroModulo")
@Remote(FinanceiroModulo.class)
@Interceptors(SessionBDInterceptorPoti.class)
public class FinanceiroModuloBean implements FinanceiroModulo {
	
	private static Logger log = Logger.getLogger(FinanceiroModulo.class);
	
	@EJB
	private SolicitacaoPagamentoService solicitacaoPagamentoService;

	@Override
	public SolicitacaoPagamento salvar(SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException {
		try{
			return solicitacaoPagamentoService.salvar(solicitacaoPagamento);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluir(SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException {
		try{
			solicitacaoPagamentoService.excluir(solicitacaoPagamento);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}

	@Override
	public void excluir(List<SolicitacaoPagamento> listaSolicitacaoPagamento) throws PotiErpMensagensException, PotiErpException {
		try{
			solicitacaoPagamentoService.excluir(listaSolicitacaoPagamento);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public SolicitacaoPagamento consultarPorId(Long idSolicitacaoPagamento) throws PotiErpException {
		try{
			return solicitacaoPagamentoService.consultarPorId(idSolicitacaoPagamento);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<SolicitacaoPagamento> consultarTodas() throws PotiErpException {
		try{
			return solicitacaoPagamentoService.consultarTodas();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
}