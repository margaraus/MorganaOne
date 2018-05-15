package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.EmpresaDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Empresa;

/**
 * @author renan
 */
@Stateless(mappedName="EmpresaService", name="EmpresaService")
@Local(EmpresaService.class)
@Interceptors(DAOInterceptor.class)
public class EmpresaServiceBean implements EmpresaService { 
	
	@DAO
	private EmpresaDao empresaDao;

	public Empresa salvar(Empresa empresa) throws PotiErpMensagensException, PotiErpException {
		try {
			if(!isDuplicado(empresa)){
				if(empresa.isNew()){
					return incluirEmpresa(empresa);
				}else{
					return alterarEmpresa(empresa);
				}
			}else{
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_EMPRESA_COM_ESTE_CNPJ.getMsg());
			}
		} catch (DaoException e) {
			throw new PotiErpException(e);
		}
	}
	
	private boolean isDuplicado(Empresa empresa) throws DaoException {
		Empresa empresaCnpj = empresaDao.getByCnpj(empresa);
		if(empresaCnpj != null && !empresaCnpj.getId().equals(empresa.getId())){
			return true;
		}
		return false;
	}

	private Empresa incluirEmpresa(Empresa empresa) throws PotiErpException {
		try {
			return empresaDao.salvar(empresa);
		} catch (Exception de) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Empresa"}, de);		
		}
	}

	private Empresa alterarEmpresa(Empresa empresa) throws PotiErpException {
		try {
			return empresaDao.salvar(empresa);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Empresa"}, e);		
		}
	}

	public void excluir(Empresa empresa) throws PotiErpException {
		try {
			empresaDao.excluir(empresa);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Empresa"}, e);
		}
	}
	
	public void excluirLista(List<Empresa> empresas)throws PotiErpException{
		try {
			for(Empresa empresa : empresas){
				empresaDao.excluir(empresa);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Empresas"}, e);
		}
	}

	public List<Empresa> consultar(Empresa empresa) throws PotiErpException {
		try {
			return empresaDao.getEmpresasPorExemplo(empresa);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Empresa"}, e);
		}
	}
	
	public List<Empresa> consultarTodos() throws PotiErpException {
		try {
			Collection<Empresa> collectionEmpresa = empresaDao.getAll();
			return new ArrayList<Empresa>(collectionEmpresa);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Empresa"}, e);
		}
	}
}