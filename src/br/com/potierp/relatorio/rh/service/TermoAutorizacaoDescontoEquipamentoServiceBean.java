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
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Andrey Oliveira
 */
@Stateless(mappedName = "TermoAutorizacaoDescontoEquipamentoService", name="TermoAutorizacaoDescontoEquipamentoService")
@Local(TermoAutorizacaoDescontoEquipamentoService.class)
@Interceptors(DAOInterceptor.class)
public class TermoAutorizacaoDescontoEquipamentoServiceBean implements
		TermoAutorizacaoDescontoEquipamentoService {

	@Override
	public byte[] getRelatorio(List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(
					MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO,
					new Object[] { "Termo de Autorização de Desconto de Equipamento" }, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<Funcionario> listFuncionario){
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			listFunc.add(funcionario);
			Map<String, Object> parametros = new HashMap<String, Object>();
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(listFunc);
			jasperReportUtilHelper.setOrigem("TermoAutorizacaoDescontoEquipamentos.jasper");
			jasperReportUtilHelper.setParametros(parametros);
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}

}
