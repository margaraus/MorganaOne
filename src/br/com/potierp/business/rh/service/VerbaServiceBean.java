package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.VerbaDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Verba;

/**
 * @author renan
 */
@Stateless(mappedName="VerbaService", name="VerbaService")
@Local(VerbaService.class)
@Interceptors(DAOInterceptor.class)
public class VerbaServiceBean implements VerbaService {
	
	@DAO
	private VerbaDao verbaDao;
	
	public Verba salvarVerba(final Verba verba) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(verba)) {
				if (verba.isNew()) {
					return incluirVerba(verba);
				} else {
					return alterarVerba(verba);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UMA_VERBA_COM_ESTE_CODIGO.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final Verba verba) throws DaoException {
		Verba verbaCodigo = verbaDao.getPorCodigo(verba);
		if (verbaCodigo != null && !verbaCodigo.getId().equals(verba.getId())) {
			return true;
		}
		return false;
	}

	private Verba incluirVerba(final Verba verba) throws PotiErpException {
		try {
			return verbaDao.salvar(verba);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "VERBA" }, e);
		}
	}

	private Verba alterarVerba(final Verba verba) throws PotiErpException {
		try {
			return verbaDao.salvar(verba);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "VERBA" }, e);
		}
	}

	public void excluirVerba(final Verba verba) throws PotiErpException {
		try {
			verbaDao.excluir(verba);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "VERBA" }, e);
		}
	}

	public void excluirListaVerba(final List<Verba> verbas) throws PotiErpException {
		try {
			for (Verba verba : verbas){
				verbaDao.excluir(verba);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "VERBA" }, e);
		}
	}

	public List<Verba> consultarVerba(final Verba verba) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Verba> consultarTodasVerbas() throws PotiErpException {
		try {
			Collection<Verba> collectionVerba = verbaDao.getAll();
			return new ArrayList<Verba>(collectionVerba);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "VERBA" }, e);
		}
	}
}