package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.CalculoValeTransporteDao;
import br.com.potierp.dao.FeriadoDao;
import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.dao.PagamentoValeTransporteDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.model.CalculoValeTransporte;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Estado;
import br.com.potierp.model.Feriado;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.LocalTrabalho;
import br.com.potierp.model.PagamentoValeTransporte;
import br.com.potierp.model.SituacaoValeTransporteEnum;
import br.com.potierp.model.TipoCalculoValeTransporteEnum;
import br.com.potierp.model.TipoFeriadoEnum;
import br.com.potierp.model.ValeTransporte;
import br.com.potierp.util.DateUtil;

/**
 * @author renan
 */
@Stateless(mappedName="ValeTransporteService", name="ValeTransporteService")
@Local(ValeTransporteService.class)
@Interceptors(DAOInterceptor.class)
public class ValeTransporteServiceBean implements ValeTransporteService {

	@DAO
	private FeriadoDao feriadoDao;

	@DAO
	private FuncionarioDao funcionarioDao;
	
	@DAO
	private CalculoValeTransporteDao calculoValeTransporteDao;
	
	@DAO
	private PagamentoValeTransporteDao pagamentoValeTransporteDao;
	
	
	@Override
	public CalculoValeTransporte calcular(final CalculoValeTransporte calculoValeTransporte) throws PotiErpMensagensException,
			PotiErpException {
		try{
			List<Feriado> feriados = buscarFeriados(calculoValeTransporte.getDataInicio(), calculoValeTransporte.getDataFim());

			List<Funcionario> funcionarios = buscarFuncionariosTrabalhando(calculoValeTransporte.getFuncionario());

			List<PagamentoValeTransporte> pagamentos = realizarCalculo(
					calculoValeTransporte,
					calculoValeTransporte.getDataInicio(),
					calculoValeTransporte.getDataFim(), feriados, funcionarios);

			if(pagamentos != null && !pagamentos.isEmpty()){
				calculoValeTransporte.setPagamentosValeTransporte(pagamentos);
				calculoValeTransporte.setSituacao(SituacaoValeTransporteEnum.CALCULADO);
				calculoValeTransporte.setMesAno(getMesAno(calculoValeTransporte.getDataInicio()));
				calculoValeTransporte.somarPagamentos();
				calculoValeTransporte.setDataRecibo(getDataRecibo(feriados, calculoValeTransporte.getDataInicio()));
				setFuncionarioPorTipoCalculo(calculoValeTransporte,	funcionarios);
				calculoValeTransporteDao.salvar(calculoValeTransporte);
				CalcularValeTransporte calcularValeTransporte = new CalcularValeTransporte(calculoValeTransporte);
				calcularValeTransporte.calcular();
			}
			return calculoValeTransporte;
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}

	private Date getDataRecibo(final List<Feriado> feriados, final Date dataReferencia) throws Exception {
		CalculoDiaUtil calculoDiaUtil = new CalculoDiaUtil(5, dataReferencia, feriados);
		calculoDiaUtil.calcular();
		return calculoDiaUtil.getDiaUtilDoMes();
	}

	private String getMesAno(final Date dataInicioCalculo) {
		return DateUtil.convertDateToReferencia(dataInicioCalculo);
	}

	@Override
	public void gravar(final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		try{
			calculoValeTransporte.setSituacao(SituacaoValeTransporteEnum.GRAVADO);
			calculoValeTransporteDao.salvar(calculoValeTransporte);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}
	
	@Override
	public void excluirCalculo(final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException {
		try{
			calculoValeTransporteDao.excluir(calculoValeTransporte);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}
	
	@Override
	public List<CalculoValeTransporte> consultarTodos() throws PotiErpException{
		try{
			Collection<CalculoValeTransporte> calculos = calculoValeTransporteDao.getAll();
			return new ArrayList<CalculoValeTransporte>(calculos);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}

	private List<PagamentoValeTransporte> realizarCalculo(final CalculoValeTransporte calculoValeTransporte, final Date dataInicioCalculo,
			final Date dataFinalCalculo, final List<Feriado> feriados,
			final List<Funcionario> funcionarios) {

		List<PagamentoValeTransporte> pagamentos = new ArrayList<PagamentoValeTransporte>();

		for(Funcionario funcionario : funcionarios){

			if(funcionario.getValesTransporte() != null 
					&& funcionario.getValesTransporte().size() > 0){

				List<ValeTransporte> valesTransportes = new ArrayList<ValeTransporte>(funcionario.getValesTransporte());
				List<LocalTrabalho> locaisTrabalhos = new ArrayList<LocalTrabalho>(funcionario.getLocaisTrabalho());

				Calendar dataInicio = DateUtil.convertDateToCalendar((Date)dataInicioCalculo.clone());
				Calendar dataFinal = DateUtil.convertDateToCalendar((Date)dataFinalCalculo.clone());
				while(dataInicio.compareTo(dataFinal) <= 0){
					verificarPagamento(calculoValeTransporte, dataInicio.getTime(), feriados, valesTransportes, locaisTrabalhos, pagamentos);
					dataInicio.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}
		return pagamentos;
	}

	private void verificarPagamento(final CalculoValeTransporte calculoValeTransporte, final Date dataCalculo,
			final List<Feriado> feriados,
			final List<ValeTransporte> valesTransportes,
			final List<LocalTrabalho> locaisTrabalhos,
			final List<PagamentoValeTransporte> pagamentos) {

		List<LocalTrabalho> locais = getLocalTrabalhoDoDia(dataCalculo, locaisTrabalhos);
		if(locais.size() > 0){

			Feriado feriado = getFeriado(dataCalculo, feriados);
			Boolean isDiaEhSabado = isDiaEhSabado(dataCalculo);
			Boolean isDiaEhDomingo = isDiaEhDomingo(dataCalculo);

			for(LocalTrabalho localTrabalho : locais){

				Cliente cliente = localTrabalho.getCliente();
				if(feriado != null && !isFazerPagamentoFeriado(feriado, cliente)){
					if(!cliente.getIsTrabalhaFeriado()){
						continue;
					}
				}
				realizarPagamento(calculoValeTransporte, dataCalculo, valesTransportes, pagamentos,
						isDiaEhSabado, isDiaEhDomingo, cliente);
			}
		}
	}

	private boolean isFazerPagamentoFeriado(final Feriado feriado, final Cliente cliente) {
		Boolean isNacional = feriado.getTipoFeriado().equals(TipoFeriadoEnum.NACIONAL);
		if(isNacional){
			return false;
		}
		Boolean isEstadual = feriado.getTipoFeriado().equals(TipoFeriadoEnum.ESTADUAL);
		if(isEstadual){
			Estado estadoCliente = cliente.getEndereco().getEstado();
			Estado estadoFeriado = feriado.getEstado();
			if(estadoCliente.equals(estadoFeriado)){
				return false;
			}
		}
		Boolean isMunicipal = feriado.getTipoFeriado().equals(TipoFeriadoEnum.MUNICIPAL);
		if(isMunicipal){
			Cidade cidadeCliente = cliente.getCidade();
			Cidade cidadeFeriado = feriado.getCidade();
			if(cidadeCliente.equals(cidadeFeriado)){
				return false;
			}
		}
		return true;
	}

	private void realizarPagamento(final CalculoValeTransporte calculoValeTransporte, final Date dataValeTransporte,
			final List<ValeTransporte> valesTransportes,
			final List<PagamentoValeTransporte> pagamentos,
			final Boolean isDiaEhSabado, 
			final Boolean isDiaEhDomingo,
			final Cliente cliente) {
		if((isDiaEhSabado && !cliente.getIsSabadoDiaUtil())
				|| (isDiaEhDomingo && !cliente.getIsDomingoDiaUtil())){
			return;
		}
		pagar(calculoValeTransporte, dataValeTransporte, cliente, valesTransportes, pagamentos);
	}

	private void pagar(final CalculoValeTransporte calculoValeTransporte, final Date dataValeTransporte, final Cliente cliente,
			final List<ValeTransporte> valesTransportes,
			final List<PagamentoValeTransporte> pagamentos) {

		for(ValeTransporte valeTransporte : valesTransportes){
			if(valeTransporte.getCliente().getId().equals(cliente.getId())){
				PagamentoValeTransporte pagamentoValeTransporte = new PagamentoValeTransporte();
				pagamentoValeTransporte.setDataValeTransporte(dataValeTransporte);
				pagamentoValeTransporte.setFuncionario(valeTransporte.getFuncionario());
				pagamentoValeTransporte.setCliente(cliente);
				pagamentoValeTransporte.setTipoValeTransporte(valeTransporte.getTipoValeTransporte());
				pagamentoValeTransporte.setRumoTransporte(valeTransporte.getRumoTransporte());
				pagamentoValeTransporte.setCalculoValeTransporte(calculoValeTransporte);
				pagamentos.add(pagamentoValeTransporte);
			}
		}
	}

	private List<LocalTrabalho> getLocalTrabalhoDoDia(final Date dataInicio,
			final List<LocalTrabalho> locaisTrabalhos) {
		
		int diaSemana = getDiaSemana(dataInicio);
		List<LocalTrabalho> locaisTrabalhoDoDia = new ArrayList<LocalTrabalho>();
		
		for(LocalTrabalho localTrabalho : locaisTrabalhos){
			if(localTrabalho.getDiaSemana().getDiaCalendar() == diaSemana){
				locaisTrabalhoDoDia.add(localTrabalho);
			}
		}
		return locaisTrabalhoDoDia;
	}

	private int getDiaSemana(final Date dataInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dataInicio.getTime());
		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
		return diaSemana;
	}

	private Boolean isDiaEhSabado(final Date dataInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dataInicio.getTime());
		return DateUtil.isSabado(cal);
	}

	private Boolean isDiaEhDomingo(final Date dataInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dataInicio.getTime());
		return DateUtil.isDomingo(cal);
	}

	private Feriado getFeriado(final Date dataDia, final List<Feriado> feriados) {
		for(Feriado feriado : feriados){
			Calendar calFeriado = Calendar.getInstance();
			calFeriado.setTime(feriado.getData());
			
			Calendar calDataDia = Calendar.getInstance();
			calDataDia.setTime(dataDia);
			
			if(calFeriado.get(Calendar.DAY_OF_MONTH) == calDataDia.get(Calendar.DAY_OF_MONTH)
					&& calFeriado.get(Calendar.MONTH) == calDataDia.get(Calendar.MONTH)) {
				if(calFeriado.get(Calendar.YEAR) == calDataDia.get(Calendar.YEAR)
						|| !feriado.getIsExpira()){
					return feriado;
				}
			}
		}
		return null;
	}

	private List<Feriado> buscarFeriados(final Date dataInicioCalculo,
			final Date dataFinalCalculo) throws DaoException {
		List<Feriado> feriados = feriadoDao.getPorPeriodo(dataInicioCalculo, dataFinalCalculo);
		return feriados;
	}

	private List<Funcionario> buscarFuncionariosTrabalhando(final Funcionario funcionario) throws DaoException {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		if(funcionario != null && !funcionario.isNew()){
			Funcionario func = funcionarioDao.getFullPorId(funcionario.getId());
			funcionarios.add(func);
		}else{
			funcionarios = funcionarioDao.getAptosValeTransporte();
		}
		return funcionarios;
	}
	
	private void setFuncionarioPorTipoCalculo(
			final CalculoValeTransporte calculoValeTransporte,
			final List<Funcionario> funcionarios) {
		Boolean isCalculoPorFuncionario = calculoValeTransporte.getTipoCalculoEnum().equals(TipoCalculoValeTransporteEnum.FUNCIONARIO);
		if(isCalculoPorFuncionario){
			calculoValeTransporte.setFuncionario(funcionarios.get(0));
		}
	}
	
	@Override
	public List<PagamentoValeTransporte> getPagamentosPorCalculo(final Long idCalculo)
			throws PotiErpMensagensException, PotiErpException {
		try{
			return pagamentoValeTransporteDao.getPorCalculo(idCalculo);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}
}