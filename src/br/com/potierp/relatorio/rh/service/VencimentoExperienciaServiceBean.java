package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.LocalTrabalho;
import br.com.potierp.model.Responsavel;
import br.com.potierp.relatorio.rh.helper.VencimentoExperienciaHelper;
import br.com.potierp.util.DateUtil;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Doug
 *
 */
@Stateless(mappedName = "VencimentoExperienciaService", name="VencimentoExperienciaService")
@Local(VencimentoExperienciaService.class)
@Interceptors(DAOInterceptor.class)
public class VencimentoExperienciaServiceBean implements
		VencimentoExperienciaService {

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.VencimentoExperienciaService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(final List<Funcionario> listFuncionario,
			final Date dataInicio, final Date dataFim, final Cidade cidade,
			final Responsavel responsavel) throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario, dataInicio, dataFim, cidade, responsavel);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Vencimento Experiencia"}, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<Funcionario> listFuncionario, final Date dataInicio,
			final Date dataFim, final Cidade cidade,
			final Responsavel responsavel) {
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		List<VencimentoExperienciaHelper> listHelper = new ArrayList<VencimentoExperienciaHelper>();
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			listFunc.add(funcionario);
			Collection<Cliente> clientes = new HashSet<Cliente>();
			for (LocalTrabalho localTrabalho : funcionario.getLocaisTrabalho()) {
				clientes.add(localTrabalho.getCliente());
			}
			if(clientes.isEmpty()) {
				clientes.add(new Cliente());
			}
			VencimentoExperienciaHelper helper = new VencimentoExperienciaHelper();
			helper.setClientes(clientes);
			helper.setFuncionario(funcionario);
			helper.setDataPrimeiroVencimento(DateUtil.addDias(funcionario.getDataAdmissao(), 44));
			helper.setDataSegundoVencimento(DateUtil.addDias(funcionario.getDataAdmissao(), 89));
			
			listHelper.add(helper);
		}
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dataInicio", dataInicio);
		parametros.put("dataFim", dataFim);
		parametros.put("cidade", cidade);
		String nomeResponsavel = responsavel != null && responsavel.getFuncionario() != null?responsavel.getFuncionario().getPessoa().getNome(): null;
		parametros.put("responsavel", nomeResponsavel);

		JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
		jasperReportUtilHelper.setDataSource(listHelper);
		jasperReportUtilHelper.setOrigem("VencimentoExperiencia.jasper");
		jasperReportUtilHelper.setParametros(parametros);
		listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		
		return listJasperReportUtilHelpers;
	}
}