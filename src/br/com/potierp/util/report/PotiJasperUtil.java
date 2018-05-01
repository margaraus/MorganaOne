/**
 * 
 */
package br.com.potierp.util.report;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.resource.PotiErpProperties;
import br.com.potierp.util.file.ComplementaNomeArquivo;
import br.com.potierp.util.file.GerenciadorArquivos;
import br.com.potierp.util.file.GerenciadorArquivosCommons;

/**
 * Classe utilitaria do Praxis, que cria aciona o Jasper para gerar um pdf e guarda o conteudo em uma pasta temporaria
 * configurada no arquivo de propriedades do projeto.
 * @author felipe.affonso
 */
public final class PotiJasperUtil {

	/**
	 * Extensao pdf.
	 */
	private static final String PDF = ".pdf";
	
	/**
	 * Extensão xls.
	 */
	private static final String XLS = ".xls";
	
	/**
	 * Extensão csv.
	 */
	private static final String CSV = ".csv";

    /**
	 * ": ".
	 */
	private static final String SEPARADOR = ": ";

	/**
	 * Log da classe.
	 */
	private static Logger log = Logger.getLogger(PotiJasperUtil.class);
	
	/**
	 * Construtor default privado.
	 */
	private PotiJasperUtil(){}

	/**
	 * Cria o arquivo pdf e retorna o nome do arquivo.
	 * @param relatorioEnum
	 * @param dataSource
	 * @return
	 * @throws PotiErpException 
	 */
	public static String gerarRelatorioPdf(final RelatorioEnum relatorioEnum, final Map<String, Object> params, final List<?> dataSource) throws PotiErpException {
		try {
			byte[] data = JasperReportUtil.gerarPdf(relatorioEnum.getNomeArquivoJasper(), params, dataSource);
			ComplementaNomeArquivo ca = new ComplementaNomeArquivo();
			String pdf = ca.withMilliSeconds(relatorioEnum.getNomeArquivoSaida()).concat(PDF);
			GerenciadorArquivos ga = new GerenciadorArquivosCommons();
			ga.gravarArquivo(PotiErpProperties.getInstance().getReportPath(), pdf, data);
			return pdf;
		} catch (Exception e) {
			log.error("Erro ao gerar Relatorio PDF "+relatorioEnum.getNomeArquivoSaida()+SEPARADOR+e.getMessage(),e);
			throw new PotiErpException(e);
		}
	}
	
	/**
	 * Cria o arquivo pdf e retorna o nome do arquivo.
	 * @param relatorioEnum
	 * @param dataSource
	 * @return
	 * @throws PotiErpException 
	 */
	public static String gerarRelatorioPdf(final RelatorioIText relatorio) throws PotiErpException {
		try {
			byte[] data = relatorio.getRelatorio();
			ComplementaNomeArquivo ca = new ComplementaNomeArquivo();
			String pdf = ca.withMilliSeconds(relatorio.getTitulo()).concat(PDF);
			GerenciadorArquivos ga = new GerenciadorArquivosCommons();
			ga.gravarArquivo(PotiErpProperties.getInstance().getReportPath(), pdf, data);
			return pdf;
		} catch (Exception e) {
			log.error("Erro ao gerar Relatorio IText PDF "+relatorio.getTitulo()+SEPARADOR+e.getMessage(),e);
			throw new PotiErpException(e);
		}
	}

	/**
	 * Cria o arquivo xls e retorna o nome do arquivo.
	 * @param relatorioEnum
	 * @param params
	 * @param dataSource
	 * @return
	 * @throws PotiErpException 
	 */
	public static String gerarRelatorioXls(final RelatorioEnum relatorioEnum, final Map<String, Object> params, final List<?> dataSource) throws PotiErpException{
		try{
			byte[] data = JasperReportUtil.gerarXls(relatorioEnum.getNomeArquivoJasper(), params, dataSource);
			ComplementaNomeArquivo ca = new ComplementaNomeArquivo();
			String pdf = ca.withMilliSeconds(relatorioEnum.getNomeArquivoSaida()).concat(XLS);
			GerenciadorArquivos ga = new GerenciadorArquivosCommons();
			ga.gravarArquivo(PotiErpProperties.getInstance().getReportPath(), pdf, data);
			return pdf;
		} catch (Exception e) {
			log.error("Erro ao gerar Relatorio "+relatorioEnum.getNomeArquivoSaida()+SEPARADOR+e.getMessage(),e);
			throw new PotiErpException(e);
		}
	}
	
	/**
	 * Cria o arquivo xls e retorna o nome do arquivo.
	 * @param relatorioEnum
	 * @param params
	 * @param dataSource
	 * @return
	 * @throws PotiErpException 
	 */
	public static String gerarRelatorioXlsJExcelApi(final RelatorioEnum relatorioEnum, final Map<String, Object> params, final List<?> dataSource) throws PotiErpException{
		try{
			byte[] data = JasperReportUtil.gerarXlsJExcelApi(relatorioEnum.getNomeArquivoJasper(), params, dataSource);
			ComplementaNomeArquivo ca = new ComplementaNomeArquivo();
			String pdf = ca.withMilliSeconds(relatorioEnum.getNomeArquivoSaida()).concat(XLS);
			GerenciadorArquivos ga = new GerenciadorArquivosCommons();
			ga.gravarArquivo(PotiErpProperties.getInstance().getReportPath(), pdf, data);
			return pdf;
		} catch (Exception e) {
			log.error("Erro ao gerar Relatorio "+relatorioEnum.getNomeArquivoSaida()+SEPARADOR+e.getMessage(),e);
			throw new PotiErpException(e);
		}
	}
	
	/**
	 * Cria o arquivo xls e retorna o nome do arquivo.
	 * @param relatorioEnum
	 * @param params
	 * @param dataSource
	 * @return
	 * @throws PotiErpException 
	 */
	public static String gerarRelatorioCSV(final RelatorioEnum relatorioEnum, final Map<String, Object> params, final List<?> dataSource) throws PotiErpException{
		try{
			byte[] data = JasperReportUtil.gerarCSV(relatorioEnum.getNomeArquivoJasper(), params, dataSource);
			ComplementaNomeArquivo ca = new ComplementaNomeArquivo();
			String csv = ca.withMilliSeconds(relatorioEnum.getNomeArquivoSaida()).concat(CSV);
			GerenciadorArquivos ga = new GerenciadorArquivosCommons();
			ga.gravarArquivo(PotiErpProperties.getInstance().getReportPath(), csv, data);
			return csv;
		} catch (Exception e) {
			log.error("Erro ao gerar Relatorio "+relatorioEnum.getNomeArquivoSaida()+SEPARADOR+e.getMessage(),e);
			throw new PotiErpException(e);
		}
	}
	
	/**
	 * Gera um PDF a partir de um List de Objetos.
	 * 
	 * @param origem - Nome do arquivo jasper.
	 * @param parametros - Parametros esperados pelo arquivo.
	 * @param dataSource - Datasource.
	 * @return
	 * @throws PotiErpException
	 * @throws SigaException
	 */
	public static byte[] gerarPdf(final String origem,
			final Map<String, Object> parametros, final List<?> dataSource)
			throws PotiErpException {
		try {
			return JasperReportUtil.gerarPdf(origem, parametros, dataSource);
		} catch (JRException se) {
			log.error(se.getMessage(), se);
			throw new PotiErpException(se.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e.getMessage());
		}
	}
	
	/**
	 * Gera um PDF.
	 * 
	 * @param origem - Nome do arquivo jasper.
	 * @param parametros - Parametros esperados pelo arquivo.
	 * @throws PotiErpException
	 * @throws SigaException
	 */
	public static byte[] gerarPdf(final String origem, final Map<String, Object> parametros) throws PotiErpException {
		try {
			return JasperReportUtil.gerarPdf(origem, parametros);
		} catch (JRException se) {
			log.error(se.getMessage(), se);
			throw new PotiErpException(se.getMessage());
		}
	}
	
	/**
	 * Gera uma lista de PDF a partir de um List de Objetos.
	 * 
	 * @param listaRelatorios
	 * @return
	 * @throws PotiErpException
	 */
	public static byte[] gerarMultiPdf(final List<JasperReportUtilHelper> listaRelatorios)
			throws PotiErpException {
		try {
			return JasperReportUtil.gerarPdfMultiRel(listaRelatorios);
		} catch (JRException se) {
			log.error(se.getMessage(), se);
			throw new PotiErpException(se.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e.getMessage());
		}
	}
	
	/**
	 * Gera uma lista de PDF a partir de um List de Objetos.
	 * 
	 * @param listaRelatorios
	 * @return
	 * @throws PotiErpException
	 */
	public static byte[] gerarMultiPdfDuasVias(final List<JasperReportUtilHelper> listaRelatorios)
			throws PotiErpException {
		try {
			return JasperReportUtil.gerarPdfMultiRelDuasVias(listaRelatorios);
		} catch (JRException se) {
			log.error(se.getMessage(), se);
			throw new PotiErpException(se.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e.getMessage());
		}
	}
	
	/**
	 * Gera uma lista de PDF com dois relatorios a partir de um List de Objetos.
	 * 
	 * @param listaRelatorios
	 * @return
	 * @throws PotiErpException
	 */
	public static byte[] gerarMultiPdfComDoisRelatorios(final List<JasperReportUtilHelper> listaRelatorios)
			throws PotiErpException {
		try {
			return JasperReportUtil.gerarPdfMultiRelComDoisRelatorios(listaRelatorios);
		} catch (JRException se) {
			log.error(se.getMessage(), se);
			throw new PotiErpException(se.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e.getMessage());
		}
	}
	
	/**
	 * Procura o arquivo Jasper Report.
	 * @param nomeJasper - Nome do Arquivo Jasper.
	 * @return
	 */
	public static String getArquivoJasper(final String nomeJasper) {
		String caminho = PotiErpProperties.getInstance().getReportPath();
		File diretorio = new File(caminho);
		
		if(diretorio != null && diretorio.isDirectory()) {
			 // Busca os arquivos .jasper dentro do diretorio.
			File[] arquivos = diretorio.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(final File dir, final String name) {
					return name.endsWith(".jasper");
				}
			});
			
			// Procura pelo nome do arquivo.
			for(File file : arquivos) {
				if(file.getPath().endsWith(nomeJasper))
					return file.getPath();
			}
		}
		
		return null;
	}
	
}
