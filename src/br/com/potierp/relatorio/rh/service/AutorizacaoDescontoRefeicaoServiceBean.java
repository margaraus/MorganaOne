package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Funcionario;
import br.com.potierp.util.DateUtil;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

@Stateless(mappedName = "AutorizacaoDescontoRefeicaoService", name="AutorizacaoDescontoRefeicaoService")
@Local(AutorizacaoDescontoRefeicaoService.class)
@Interceptors(DAOInterceptor.class)
public class AutorizacaoDescontoRefeicaoServiceBean implements
		AutorizacaoDescontoRefeicaoService {

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.AutorizacaoDescontoRefeicaoService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Desvio de Função"}, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<Funcionario> listFuncionario){
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			listFunc.add(funcionario);
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("dataExtenso", DateUtil.convertDateToExtenso(funcionario.getDataAdmissao()));
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(listFunc);
			jasperReportUtilHelper.setOrigem("AutorizacaoDescontoRefeicaoGeral.jasper");
			jasperReportUtilHelper.setParametros(parametros);
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}
}