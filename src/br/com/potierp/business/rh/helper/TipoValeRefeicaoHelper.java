package br.com.potierp.business.rh.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.potierp.model.PagamentoValeRefeicao;
import br.com.potierp.model.TipoValeRefeicao;

public class TipoValeRefeicaoHelper implements Serializable {

	private static final long serialVersionUID = -7322794317395978248L;

	private TipoValeRefeicao tipoValeRefeicao;
	
	private BigDecimal valor;
	
	private Integer quantidade;
	
	private List<PagamentoValeRefeicao> pagamentosValeRefeicao;

	/**
	 * @return the tipoValeRefeicao
	 */
	public TipoValeRefeicao getTipoValeRefeicao() {
		return tipoValeRefeicao;
	}

	/**
	 * @param tipoValeRefeicao the tipoValeRefeicao to set
	 */
	public void setTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) {
		this.tipoValeRefeicao = tipoValeRefeicao;
	}
	
	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the pagamentosValeRefeicao
	 */
	public List<PagamentoValeRefeicao> getPagamentosValeRefeicao() {
		return pagamentosValeRefeicao;
	}

	/**
	 * @param pagamentosValeRefeicao the pagamentosValeRefeicao to set
	 */
	public void setPagamentosValeRefeicao(
			final List<PagamentoValeRefeicao> pagamentosValeRefeicao) {
		this.pagamentosValeRefeicao = pagamentosValeRefeicao;
	}
}