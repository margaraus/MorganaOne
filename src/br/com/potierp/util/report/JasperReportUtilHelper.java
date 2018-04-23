package br.com.potierp.util.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Classe utilitária para criar relatórios com que possuam outros relatórios agregados.
 * 
 * @author
 *		<p>
 *		$LastChangedBy: $
 *		<p>
 *		$LastChangedDate: $
 */
public class JasperReportUtilHelper implements Serializable {
	
	/**
	 * SerialVersionID.
	 */
	private static final long serialVersionUID = -3009070545466182720L;

	/**
	 * Representa a origem descrita no RelatorioEnum
	 */
	private String origem;
	
	/**
	 * Parâmetros do relatório.
	 */
	@SuppressWarnings("rawtypes")
	private Map parametros = new HashMap();
	
	/**
	 * DataSource.
	 */
	@SuppressWarnings("rawtypes")
	private List dataSource = new ArrayList();
	
	/**
	 * Construtor default.
	 */
	public JasperReportUtilHelper() {}
	
	/**
	 * Construtor paramentrizado.
	 * @param origem
	 * @param parametros
	 * @param dataSource
	 */
	public JasperReportUtilHelper(final String origem, final Map<?,?> parametros, final List<?> dataSource) {
		this.origem = origem;
		this.parametros = parametros;
		this.dataSource = dataSource;
	}

	/**
	 * @return the origem
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * @param origem the origem to set
	 */
	public void setOrigem(final String origem) {
		this.origem = origem;
	}

	/**
	 * @return the parametros
	 */
	public Map<?, ?> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(final Map<?, ?> parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the dataSource
	 */
	public List<?> getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(final List<?> dataSource) {
		this.dataSource = dataSource;
	}
}
