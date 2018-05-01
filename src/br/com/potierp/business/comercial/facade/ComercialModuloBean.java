
package br.com.potierp.business.comercial.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import br.com.potierp.business.comercial.service.ComercialService;
import br.com.potierp.business.rh.service.ClienteService;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.HistoricoComercial;

@Stateless(mappedName="ComercialModulo", name="ComercialModulo")
@Remote(ComercialModulo.class)
@Interceptors(DAOInterceptor.class)
public class ComercialModuloBean implements ComercialModulo {
	
	private static Logger log = Logger.getLogger(ComercialModuloBean.class);
	
	@EJB
	private ComercialService comercialService;
	
	@EJB
	private ClienteService clienteService;

	@Override
	public HistoricoComercial salvar(final HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException {
		try{
			return this.comercialService.salvar(historicoComercial);
		}catch(PotiErpMensagensException peme){
			log.error(peme.getMessage(), peme);
			throw peme;
		}catch(PotiErpException pee){
			log.error(pee.getMessage(), pee);
			throw pee;
		}
	}

	@Override
	public void excluir(final HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException {
		try{
			this.comercialService.excluir(historicoComercial);
		}catch(PotiErpMensagensException peme){
			log.error(peme.getMessage(), peme);
			throw peme;
		}catch(PotiErpException pee){
			log.error(pee.getMessage(), pee);
			throw pee;
		}
	}

	@Override
	public void excluir(final List<HistoricoComercial> listaHistoricoComercial) throws PotiErpMensagensException, PotiErpException {
		try{
			this.comercialService.excluir(listaHistoricoComercial);
		}catch(PotiErpMensagensException peme){
			log.error(peme.getMessage(), peme);
			throw peme;
		}catch(PotiErpException pee){
			log.error(pee.getMessage(), pee);
			throw pee;
		}
	}

	@Override
	public HistoricoComercial consultarPorId(final Long idHistoricoComercial) throws PotiErpException {
		try{
			return this.comercialService.consultarPorId(idHistoricoComercial);
		}catch(PotiErpException pee){
			log.error(pee.getMessage(), pee);
			throw pee;
		}
	}

	@Override
	public List<HistoricoComercial> consultarTodos() throws PotiErpException {
		try{
			return this.comercialService.consultarTodos();
		}catch(PotiErpException pee){
			log.error(pee.getMessage(), pee);
			throw pee;
		}
	}
	
	@Override
	public List<Cliente> consultarTodosClientesComNomeFantasiaCodigo()
			throws PotiErpException {
		try {
			return clienteService.consultarTodosComNomeFantasiaCodigo();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}