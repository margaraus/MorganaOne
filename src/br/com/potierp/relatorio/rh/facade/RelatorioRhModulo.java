package br.com.potierp.relatorio.rh.facade;

import java.util.Date;
import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.model.CalculoValeTransporte;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.Responsavel;

/**
 * @author Doug
 *
 */
public interface RelatorioRhModulo {

	byte[] getFichaRegistro(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getReciboValeTransporte(List<CalculoValeTransporte> listCalculoValeTransporte) throws PotiErpException;
	
	byte[] getMapaValeTransporte(List<CalculoValeTransporte> listCalculoValeTransporte) throws PotiErpException;
	
	byte[] getReciboValeRefeicao(List<CalculoValeRefeicao> listCalculoValeRefeicao) throws PotiErpException;
	
	byte[] getMapaValeRefeicao(List<CalculoValeRefeicao> listCalculoValeRefeicao) throws PotiErpException;
	
	byte[] getContratoExperiencia(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getSalarioFamilia(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getSolicitacaoValeTransporte(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getVencimentoExperiencia(List<Funcionario> listFuncionario,
			Date dataInicio, Date dataFim, Cidade cidade,
			Responsavel responsavel) throws PotiErpException;
	
	byte[] getDeclaracaoEncargos(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getDesvioFuncao(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getAutorizacaoDescontoRefeicaoCesari(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getAutorizacaoDescontoRefeicaoMundial(final List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getAutorizacaoDescontoRefeicao(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getAutorizacaoDescontoUniformes(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getAutorizacaoDescContribAssitencialFamiliar(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getTermoResponsabilidadeCracha(List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getReciboDevolucaoCTPS(final List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getProtocoloEntregaAtestadoMedico(final List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getTermoAutorizacaoDescontoEquipamento(final List<Funcionario> listFuncionario) throws PotiErpException;
	
	byte[] getCartaoPonto(final List<Funcionario> listFuncionario, final String mes, final String ano) throws PotiErpException;
}
