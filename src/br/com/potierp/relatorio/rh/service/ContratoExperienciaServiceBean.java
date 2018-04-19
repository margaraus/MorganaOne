package br.com.potierp.relatorio.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.business.rh.comparator.LocalTrabalhoClienteComparator;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.IntervaloJornada;
import br.com.potierp.model.JornadaTrabalho;
import br.com.potierp.model.LocalTrabalho;
import br.com.potierp.util.CurrencyWriter;
import br.com.potierp.util.DateUtil;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Doug
 *
 */
@Stateless(mappedName = "ContratoExperienciaService", name="ContratoExperienciaService")
@Local(ContratoExperienciaService.class)
@Interceptors(DAOInterceptor.class)
public class ContratoExperienciaServiceBean implements
		ContratoExperienciaService {

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.ContratoExperienciaService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {

			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listFuncionario);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(
					MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO,
					new Object[] { "Contrato de experiência" }, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<Funcionario> listFuncionario){
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(Funcionario funcionario : listFuncionario){
			List<Funcionario> listFunc = new ArrayList<Funcionario>();
			Collections.sort(funcionario.getLocaisTrabalho());
			listFunc.add(funcionario);
			CurrencyWriter cw = new CurrencyWriter();
			String locaisTrabalho = "";
			String horariosTrabalho = "";
			int qtdJornada = 0;
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("dataContrato", funcionario.getDataAdmissao());
			parametros.put("dataFinalContrato", DateUtil.addDias(funcionario.getDataAdmissao(), 44));
			Cliente cliente = null;
			JornadaTrabalho jornada = null;
			Collection<IntervaloJornada> intervalosJornada = null;
			Collections.sort(funcionario.getLocaisTrabalho(), new LocalTrabalhoClienteComparator());
			for (LocalTrabalho localTrabalho : funcionario.getLocaisTrabalho()) {
				if("".equals(locaisTrabalho)) {
					locaisTrabalho += localTrabalho.getCliente().getRazaoSocial() 
					+ ", " + localTrabalho.getCliente().getEndereco().getEndereco() + ", " + localTrabalho.getCliente().getEndereco().getNumero()
					+ " - " + localTrabalho.getCliente().getEndereco().getBairro() + " - " + localTrabalho.getCliente().getEndereco().getCidade()
					+ " - " + localTrabalho.getCliente().getEndereco().getEstado().getSigla();
				}else {
					if(!localTrabalho.getCliente().equals(cliente)) {
						locaisTrabalho += "; / " + localTrabalho.getCliente().getRazaoSocial()
						+ ", " + localTrabalho.getCliente().getEndereco().getEndereco() + ", " + localTrabalho.getCliente().getEndereco().getNumero()
						+ " - " + localTrabalho.getCliente().getEndereco().getBairro() + " - " + localTrabalho.getCliente().getEndereco().getCidade()
						+ " - " + localTrabalho.getCliente().getEndereco().getEstado().getSigla();
					}
				}
				
				if("".equals(horariosTrabalho)) {
					horariosTrabalho += localTrabalho.getCliente().getNomeFantasia() 
							+ " (" + localTrabalho.getCliente().getEndereco().getCidade() + "): "
							+ montarJornadaTrabalho(localTrabalho);
				}else {
					if(localTrabalho.getCliente().equals(cliente) && localTrabalho.getJornadaTrabalho().equals(jornada)){
						qtdJornada++;
						horariosTrabalho += ", " + montarJornadaTrabalho(localTrabalho);
					} else if(localTrabalho.getCliente().equals(cliente) && !localTrabalho.getJornadaTrabalho().equals(jornada)) {
						if(qtdJornada > 1)
							horariosTrabalho = retirarUltimaVirgula(horariosTrabalho);
						qtdJornada = 0;
						horariosTrabalho += ": " + jornada.getNome() + " " 
							+ montarIntervaloJornada(jornada.getIntervalosJornada()) 
							+ ", " + montarJornadaTrabalho(localTrabalho);
						qtdJornada++;
					} else if(!localTrabalho.getCliente().equals(cliente)){
						horariosTrabalho = retirarUltimaVirgula(horariosTrabalho);
						horariosTrabalho += ": " + jornada.getNome() + " " 
							+ montarIntervaloJornada(jornada.getIntervalosJornada()) + " // " + localTrabalho.getCliente().getNomeFantasia()
						+ "(" + localTrabalho.getCliente().getEndereco().getCidade() + "): "
						+ montarJornadaTrabalho(localTrabalho); 
					}
				}
				
				cliente = localTrabalho.getCliente();
				jornada = localTrabalho.getJornadaTrabalho();
				intervalosJornada = jornada.getIntervalosJornada();
			}
			if(!"".equals(horariosTrabalho)) {
				if(qtdJornada > 1)
					horariosTrabalho = retirarUltimaVirgula(horariosTrabalho);
				horariosTrabalho += ": " + jornada.getNome() + " " + montarIntervaloJornada(intervalosJornada) + ".";
			}
				 
			
			parametros.put("horariosTrabalho", horariosTrabalho);
			parametros.put("locaisTrabalho", locaisTrabalho);
			parametros.put("salarioExtenso", "(" + cw.write(funcionario.getSalario()) + ")");
			parametros.put("dataContratoExtenso", DateUtil.convertDateToExtenso(funcionario.getDataAdmissao()));
			parametros.put("dataProrrogada", DateUtil.dateToPtBrString((DateUtil.addDias(funcionario.getDataAdmissao(), 89))));
			if("M".equalsIgnoreCase(funcionario.getPessoa().getSexo().getSexo())) {
				parametros.put("nomeComTitulo", "o Sr. " + funcionario.getPessoa().getNome());
			}else{
				parametros.put("nomeComTitulo", "a Sra. " + funcionario.getPessoa().getNome());
			} 
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(listFunc);
			jasperReportUtilHelper.setOrigem("ContratoExperiencia.jasper");
			jasperReportUtilHelper.setParametros(parametros);
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}

	/**
	 * @param texto
	 * @return 
	 */
	private String retirarUltimaVirgula(String texto) {
		String[] frases = texto.split(",");
		return texto.replace("," + frases[frases.length-1], " e " + frases[frases.length-1]);		
	}

	/**
	 * @param localTrabalho
	 * @return
	 */
	private String montarJornadaTrabalho(LocalTrabalho localTrabalho) {
		return localTrabalho.getDiaSemana().getDiaSemana()
				+ ((localTrabalho.getDiaSemana().getDiaCalendar() == 7 || localTrabalho
						.getDiaSemana().getDiaCalendar() == 1) ? "" : "-feira");
	}

	/**
	 * @param intervalosJornada
	 * @return
	 */
	private String montarIntervaloJornada(
			Collection<IntervaloJornada> intervalosJornada) {
		String intervalo = "";
		for (IntervaloJornada intervaloJornada : intervalosJornada) {
			if(intervalo.equals("")){
				intervalo += "com " + getTempoFormatado(intervaloJornada.getTempo()) + " para " + intervaloJornada.getTipoRefeicao().getTipoRefeicao();
			}else {
				intervalo += ", " + getTempoFormatado(intervaloJornada.getTempo()) + " para " + intervaloJornada.getTipoRefeicao().getTipoRefeicao();
			}
		}
		intervalo = retirarUltimaVirgula(intervalo);
		return intervalo;
	}

	/**
	 * @param intervaloJornada
	 * @return
	 */
	private String getTempoFormatado(Long tempo) {
		Long hora = tempo / 60;
		Long minutos = tempo % 60;
		
		if(hora == 0) {
			return minutos.toString() + "min";
		}
		
		return hora.toString() + ":" + (minutos.toString().length() < 2 ? minutos.toString() + "0" : minutos.toString()) + "h";
	}

}
