package br.com.potierp.business.rh.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.potierp.model.Cliente;
import br.com.potierp.model.PagamentoValeTransporte;
import br.com.potierp.model.RumoTransporteEnum;
import br.com.potierp.model.TipoValeTransporte;

public class TipoValeTransporteHelper implements Serializable {

	private static final long serialVersionUID = -7322794317395978248L;

	private TipoValeTransporte tipoValeTransporte;
	
	private RumoTransporteEnum rumo;
	
	private Cliente cliente;
	
	private BigDecimal valor;
	
	private Integer quantidade;
	
	private List<PagamentoValeTransporte> pagamentosValeTransporte;

	/**
	 * @return the tipoValeTransporte
	 */
	public TipoValeTransporte getTipoValeTransporte() {
		return tipoValeTransporte;
	}

	/**
	 * @param tipoValeTransporte the tipoValeTransporte to set
	 */
	public void setTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) {
		this.tipoValeTransporte = tipoValeTransporte;
	}

	/**
	 * @return the rumo
	 */
	public RumoTransporteEnum getRumo() {
		return rumo;
	}

	/**
	 * @param rumo the rumo to set
	 */
	public void setRumo(final RumoTransporteEnum rumo) {
		this.rumo = rumo;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
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
	 * @return the pagamentosValeTransporte
	 */
	public List<PagamentoValeTransporte> getPagamentosValeTransporte() {
		return pagamentosValeTransporte;
	}

	/**
	 * @param pagamentosValeTransporte the pagamentosValeTransporte to set
	 */
	public void setPagamentosValeTransporte(
			final List<PagamentoValeTransporte> pagamentosValeTransporte) {
		this.pagamentosValeTransporte = pagamentosValeTransporte;
	}
}