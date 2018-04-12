package br.com.potierp.business.rh.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.potierp.model.Funcionario;

public class FuncionarioValeTransporteHelper implements Serializable {

	private static final long serialVersionUID = 1263102457365113924L;
	
	private Funcionario funcionario;
	
	private BigDecimal valor;
	
	private Integer quantidade;
	
	private List<TipoValeTransporteHelper> tiposValeTransporte;
	
	public void somarQuantidades(){
		quantidade = 0;
		for(TipoValeTransporteHelper tipoValeTransporteHelper : tiposValeTransporte){
			quantidade += tipoValeTransporteHelper.getQuantidade();
		}
	}
	
	public void somarValores(){
		valor = BigDecimal.ZERO;
		for(TipoValeTransporteHelper tipoValeTransporteHelper : tiposValeTransporte){
			valor = valor.add(tipoValeTransporteHelper.getValor());
		}
	}

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
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
	 * @return the tiposValeTransporte
	 */
	public List<TipoValeTransporteHelper> getTiposValeTransporte() {
		return tiposValeTransporte;
	}

	/**
	 * @param tiposValeTransporte the tiposValeTransporte to set
	 */
	public void setTiposValeTransporte(
			final List<TipoValeTransporteHelper> tiposValeTransporte) {
		this.tiposValeTransporte = tiposValeTransporte;
	}
}