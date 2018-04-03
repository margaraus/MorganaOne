package br.com.potierp.business.rh.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.LocalTrabalhoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.LocalTrabalho;

@Stateless(mappedName="LocalTrabalhoService", name="LocalTrabalhoService")
@Local(LocalTrabalhoService.class)
@Interceptors(DAOInterceptor.class)
public class LocalTrabalhoServiceBean implements LocalTrabalhoService{
	
	@DAO
	private LocalTrabalhoDao localTrabalhoDao;

	@Override
	public List<LocalTrabalho> getPorReComSetores(final Long codigoRegistro)
			throws PotiErpException {
		try {
			return localTrabalhoDao.getPorFuncionarioComSetor(codigoRegistro);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"LocalTrabalho"}, e);
		}
	}
}
