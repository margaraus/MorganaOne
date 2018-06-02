package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.business.rh.helper.FuncionarioValeRefeicaoHelper;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Doug
 *
 */
@Stateless(mappedName = "ReciboValeRefeicaoService", name="ReciboValeRefeicaoService")
@Local(ReciboValeRefeicaoService.class)
@Interceptors(DAOInterceptor.class)
public class ReciboValeRefeicaoServiceBean implements ReciboValeRefeicaoService{
	
	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.ReciboValeRefeicaoService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(
			final List<CalculoValeRefeicao> listCalculoValeRefeicao)
			throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listCalculoValeRefeicao);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Recibo de vale refeiçao"}, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<CalculoValeRefeicao> listCalculoValeRefeicao) throws Exception{
		
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(CalculoValeRefeicao calculo : listCalculoValeRefeicao){
			
			int quantidadeFuncionario = 0;
			int totalFuncionarios = calculo.getFuncionariosValeRefeicaoHelper().size();
			int quantidadeFuncionariosHelper = 0;
			List<FuncionarioValeRefeicaoHelper> listFuncionarioValeRefeicaoHelper = new ArrayList<FuncionarioValeRefeicaoHelper>();
			for (FuncionarioValeRefeicaoHelper funcionarioValeRefeicaoHelper : calculo.getFuncionariosValeRefeicaoHelper()) {
				listFuncionarioValeRefeicaoHelper.add(funcionarioValeRefeicaoHelper);
				listFuncionarioValeRefeicaoHelper.add(funcionarioValeRefeicaoHelper);
				quantidadeFuncionariosHelper++;
				quantidadeFuncionario++;

				if(quantidadeFuncionario == 2 || totalFuncionarios == quantidadeFuncionariosHelper) {
					Map<String, Object> parametros = new HashMap<String, Object>();
					parametros.put("dataInicio", calculo.getDataInicio());
					parametros.put("dataFim", calculo.getDataFim());
					parametros.put("dataRecibo", calculo.getDataRecibo());
					JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
					jasperReportUtilHelper.setDataSource(listFuncionarioValeRefeicaoHelper);
					jasperReportUtilHelper.setOrigem("ReciboValeRefeicaoMultiplo.jasper");
					jasperReportUtilHelper.setParametros(parametros);
					listJasperReportUtilHelpers.add(jasperReportUtilHelper);
					
					quantidadeFuncionario = 0;
					listFuncionarioValeRefeicaoHelper = new ArrayList<FuncionarioValeRefeicaoHelper>();
				}
			}
		}
		return listJasperReportUtilHelpers;
	}
}