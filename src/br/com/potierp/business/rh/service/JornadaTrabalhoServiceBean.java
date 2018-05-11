package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.HorariosJornadaDao;
import br.com.potierp.dao.JornadaTrabalhoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.HorariosJornada;
import br.com.potierp.model.JornadaTrabalho;

@Stateless(mappedName="JornadaTrabalhoService", name="JornadaTrabalhoService")
@Local(JornadaTrabalhoService.class)
@Interceptors(DAOInterceptor.class)
public class JornadaTrabalhoServiceBean implements JornadaTrabalhoService {
	
	@DAO
	private JornadaTrabalhoDao jornadaTrabalhoDao;
	
	@DAO
	private HorariosJornadaDao horariosJornadaDao;

	public JornadaTrabalho salvarJornadaTrabalho(JornadaTrabalho jornadaTrabalho)
			throws PotiErpMensagensException, PotiErpException {
		try {
			if(!isDuplicado(jornadaTrabalho)){
				if(jornadaTrabalho.isNew()){
					return incluirJornadaTrabalho(jornadaTrabalho);
				}else{
					return alterarJornadaTrabalho(jornadaTrabalho);
				}
			}else{
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_UMA_JORNADA_DE_TRABALHO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(JornadaTrabalho jornadaTrabalho) throws DaoException {
		JornadaTrabalho jornada = jornadaTrabalhoDao.getByNome(jornadaTrabalho);
		if(jornada != null && !jornada.getId().equals(jornadaTrabalho.getId())){
			return true;
		}
		return false;
	}

	private JornadaTrabalho incluirJornadaTrabalho(
			JornadaTrabalho jornadaTrabalho) throws PotiErpException {
		try {
			return jornadaTrabalhoDao.salvar(jornadaTrabalho);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"JornadaTrabalho"}, e);		
		}
	}

	private JornadaTrabalho alterarJornadaTrabalho(
			JornadaTrabalho jornadaTrabalho) throws PotiErpException {
		try {
			return jornadaTrabalhoDao.salvar(jornadaTrabalho);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"JornadaTrabalho"}, e);
		}
	}

	public void excluirJornadaTrabalho(JornadaTrabalho jornadaTrabalho)
			throws PotiErpException {
		try {
			jornadaTrabalhoDao.excluir(jornadaTrabalho);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"JornadaTrabalho"}, e);		
		}
	}
	
	public void excluirListaJornadaTrabalho(List<JornadaTrabalho> jornadasTrabalho)throws PotiErpException{
		try {
			for(JornadaTrabalho jornadaTrabalho : jornadasTrabalho){
				jornadaTrabalhoDao.excluir(jornadaTrabalho);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"JornadaTrabalho"}, e);
		}
	}

	public List<JornadaTrabalho> consultarJornadaTrabalho(
			JornadaTrabalho jornadaTrabalho) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<JornadaTrabalho> consultarTodasJornadasTrabalho() throws PotiErpException {
		try {
			Collection<JornadaTrabalho> collectionJornadaTrabalho = jornadaTrabalhoDao.getAll();
			return new ArrayList<JornadaTrabalho>(collectionJornadaTrabalho);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"JornadaTrabalho"}, e);
		}
	}

	public HorariosJornada salvarHorariosJornada(HorariosJornada horariosJornada)
			throws PotiErpException {
		try {
			if(horariosJornada.isNew()){
				return incluirHorariosJornada(horariosJornada);
			}else{
				return alterarHorariosJornada(horariosJornada);
			}
		} catch (Exception e) {
			throw new PotiErpException(e);
		}
	}

	private HorariosJornada incluirHorariosJornada(
			HorariosJornada horariosJornada) throws PotiErpException {
		try {
			return horariosJornadaDao.salvar(horariosJornada);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"HorariosJornada"}, e);		
		}
	}

	private HorariosJornada alterarHorariosJornada(
			HorariosJornada horariosJornada) throws PotiErpException {
		try {
			return horariosJornadaDao.salvar(horariosJornada);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"HorariosJornada"}, e);
		}
	}

	public void excluirHorariosJornada(HorariosJornada horariosJornada)
			throws PotiErpException {
		try {
			horariosJornadaDao.excluir(horariosJornada);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"HorariosJornada"}, e);		
		}
	}

	public List<HorariosJornada> consultarHorariosJornada(
			HorariosJornada horariosJornada) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JornadaTrabalho consultarPorIdComIntervalos(Long id)
			throws PotiErpException {
		try {
			return jornadaTrabalhoDao.getPorIdComIntervalo(id);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"JornadaTrabalho"}, e);		
		}
	}
}