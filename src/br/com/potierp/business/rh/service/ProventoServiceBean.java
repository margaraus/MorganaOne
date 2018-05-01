package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.AdicionalDao;
import br.com.potierp.dao.BeneficioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Adicional;
import br.com.potierp.model.Beneficio;

/**
 * @author renan
 */
@Stateless(mappedName="ProventoService", name="ProventoService")
@Local(ProventoService.class)
@Interceptors(DAOInterceptor.class)
public class ProventoServiceBean implements ProventoService {
	
	@DAO
	private AdicionalDao adicionalDao;
	
	@DAO
	private BeneficioDao beneficioDao;
	
	public Adicional salvarAdicional(Adicional adicional) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(adicional)) {
				if (adicional.isNew()) {
					return incluirAdicional(adicional);
				} else {
					return alterarAdicional(adicional);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_ADICIONAL_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(Adicional adicional) throws DaoException {
		Adicional adicionalNome = adicionalDao.getPorNome(adicional);
		if (adicionalNome != null && !adicionalNome.getId().equals(adicional.getId())) {
			return true;
		}
		return false;
	}

	private Adicional incluirAdicional(Adicional adicional) throws PotiErpException {
		try {
			return adicionalDao.salvar(adicional);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Adicional" }, e);
		}
	}

	private Adicional alterarAdicional(Adicional adicional) throws PotiErpException {
		try {
			return adicionalDao.salvar(adicional);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Adicional" }, e);
		}
	}

	public void excluirAdicional(Adicional adicional) throws PotiErpException {
		try {
			adicionalDao.excluir(adicional);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "Adicional" }, e);
		}
	}

	public void excluirListaAdicional(List<Adicional> adicionais) throws PotiErpException {
		try {
			for (Adicional adicional : adicionais){
				adicionalDao.excluir(adicional);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Adicional" }, e);
		}
	}

	public List<Adicional> consultarAdicional(Adicional adicional) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Adicional> consultarTodosAdicionais() throws PotiErpException {
		try {
			Collection<Adicional> collectionAdicional = adicionalDao.getAll();
			return new ArrayList<Adicional>(collectionAdicional);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Adicional" }, e);
		}
	}
	
	public Beneficio salvarBeneficio(Beneficio beneficio)throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(beneficio)) {
				if (beneficio.isNew()) {
					return incluirBeneficio(beneficio);
				} else {
					return alterarBeneficio(beneficio);
				}
			} else {
				throw new PotiErpMensagensException( MensagensExceptionEnum.ERRO_JA_EXISTE_UM_BENEFICIO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(Beneficio beneficio) throws DaoException {
		Beneficio beneficioNome = beneficioDao.getPorNome(beneficio);
		if (beneficioNome != null
				&& !beneficioNome.getId().equals(beneficio.getId())) {
			return true;
		}
		return false;
	}

	private Beneficio incluirBeneficio(Beneficio beneficio)
			throws PotiErpException {
		try {
			return beneficioDao.salvar(beneficio);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Beneficio" }, e);
		}
	}

	private Beneficio alterarBeneficio(Beneficio beneficio) throws PotiErpException {
		try {
			return beneficioDao.salvar(beneficio);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Beneficio" }, e);
		}
	}

	public void excluirBeneficio(Beneficio beneficio) throws PotiErpException {
		try {
			beneficioDao.excluir(beneficio);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[] { "Beneficio" }, e);
		}
	}

	public void excluirListaBeneficio(List<Beneficio> beneficios) throws PotiErpException {
		try {
			for (Beneficio beneficio : beneficios) {
				beneficioDao.excluir(beneficio);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Beneficio" }, e);
		}
	}

	public List<Beneficio> consultarBeneficio(Beneficio beneficio) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Beneficio> consultarTodosBeneficios() throws PotiErpException {
		try {
			Collection<Beneficio> collectionBeneficio = beneficioDao
					.getAll();
			return new ArrayList<Beneficio>(collectionBeneficio);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Beneficio" }, e);
		}
	}
}