package br.com.potierp.util.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.resource.PotiErpProperties;


public class JasperReportUtil {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(JasperReportUtil.class);

	/**
	 * jasper.
	 */
	public static final String JASPER = "jasper";

	/**
	 * Constante SUBREPORT_DIR.
	 */
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	
	/**
	 * Construtor.
	 */
	private JasperReportUtil() {}

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
	public static byte[] gerarPdf(final String origem, final Map<String, Object> parametros, final List<?> dataSource) throws JRException, PotiErpException {
		try {
			parametros.put(SUBREPORT_DIR, getCaminhoArquivosJasper());
			String caminho = getArquivoJasper(origem);
			JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataSource);
			return JasperRunManager.runReportToPdf(report, parametros, ds);
		} catch (JRException jre) {
			log.error("Erro ao gerarPdf com dataSource: "+jre.getMessage(), jre);
			throw jre;
		} catch (Exception e) {
			log.error("Erro ao gerarPdf ", e);
			throw new PotiErpException(e.getMessage());
		}
	}
	
	/**
	 * Gera o arquivo XLS .
	 * @param origem
	 * @param parametros
	 * @param dataSource
	 * @throws JRException
	 */
	public static byte[] gerarXls(final String origem, final Map<String, Object> parametros, final List<?> dataSource) throws JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	
		
		try{
			parametros.put(SUBREPORT_DIR, getCaminhoArquivosJasper());
			String caminho = getArquivoJasper(origem);
			JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, ds);
			JRXlsExporter exporter = new JRXlsExporter();			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.exportReport();
		} catch(JRException jre){
			log.error("Erro ao gerarXls com dataSource: " + jre.getMessage(), jre);
			throw jre;
		}
		return baos.toByteArray();
	}
	
	/**
	 * Gera o arquivo XLS usando a api JExcelApiExporter, devido a erro dados em alguns relatórios usando o JRXlsExporter.
	 * @param origem
	 * @param parametros
	 * @param dataSource
	 * @param destino
	 * @throws JRException
	 */
	public static byte[] gerarXlsJExcelApi(final String origem, final Map<String, Object> parametros, final List<?> dataSource) throws JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	
		try{
			parametros.put(SUBREPORT_DIR, getCaminhoArquivosJasper());
			String caminho = getArquivoJasper(origem);
			JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, ds);
			JExcelApiExporter exporter = new JExcelApiExporter();
			exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, baos);
			exporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.exportReport();
		} catch(JRException jre){
			log.error("Erro ao gerarXlsJExcelApi com dataSource : " + jre.getMessage(), jre);
			throw jre;
		}
		return baos.toByteArray();
	}

	/**
	 * Gera um PDF.
	 * 
	 * @param origem - Nome do arquivo jasper.
	 * @param parametros - Parametros esperados pelo arquivo.
	 * @throws SigaException
	 */
	public static byte[] gerarPdf(final String origem, final Map<String, Object> parametros) throws JRException {
		try {
			String caminho = getArquivoJasper(origem);
			JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 
			return JasperRunManager.runReportToPdf(report, parametros);
		} catch (JRException jre) {
			log.error("Erro ao gerarPdf: "+jre.getMessage(), jre);
			throw jre;
		}
	}
	
	/**
	 * Gera o arquivo XLS usando a api JExcelApiExporter, devido a erro dados em alguns relatórios usando o JRXlsExporter.
	 * @param origem
	 * @param parametros
	 * @param dataSource
	 * @param destino
	 * @throws JRException
	 */
	public static byte[] gerarCSV(final String origem, final Map<String, Object> parametros, final List<?> dataSource) throws JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	
		try{
			parametros.put(SUBREPORT_DIR, getCaminhoArquivosJasper());
			String caminho = getArquivoJasper(origem);
			JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, ds);
			JRCsvExporter exporter = new JRCsvExporter();
			exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
		} catch(JRException jre){
			log.error("Erro ao gerarXls com dataSource : " + jre.getMessage(), jre);
			throw jre;
		}
		return baos.toByteArray();
	}
	
	/**
	 * Gera arquivo PDF agrupando relatórios destintos contidos numa lista, 
	 * podendo estes terem orientação ou tamanho de páginas diferentes.
	 * @param listaRelatorios
	 * @param configFile
	 * @return
	 * @throws JRException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static byte[] gerarPdfMultiRel(final List<JasperReportUtilHelper> listaRelatorios) throws JRException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		try {
			for(JasperReportUtilHelper jruh : listaRelatorios) {
				Map temp = jruh.getParametros();
				//Pega o caminho fisico do arquivo .jasper
				temp.put(SUBREPORT_DIR, getCaminhoArquivosJasper());
				jruh.setParametros(temp);
				
				String caminho = getArquivoJasper(jruh.getOrigem());

				JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 			
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(jruh.getDataSource());//seta DataSource a partir do List
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, jruh.getParametros(), ds);
				jasperPrintList.add(jasperPrint);
			}
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
			
		} catch (JRException jre) {
			log.error("Erro ao gerarPdf com dataSource: "+jre.getMessage(), jre);
			throw jre;
		}
		return baos.toByteArray();
	}
	
	/**
	 * Gera arquivo PDF agrupando relatórios destintos contidos numa lista, 
	 * podendo estes terem orientação ou tamanho de páginas diferentes.
	 * @param listaRelatorios
	 * @param configFile
	 * @return
	 * @throws JRException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static byte[] gerarPdfMultiRelComDoisRelatorios(final List<JasperReportUtilHelper> listaRelatorios) throws JRException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		try {
			int rel = 0;
			int qtdPaginas = 0;
			for(JasperReportUtilHelper jruh : listaRelatorios) {
				Map temp = jruh.getParametros();
				//Pega o caminho fisico do arquivo .jasper
				temp.put(SUBREPORT_DIR, getCaminhoArquivosJasper());
				if(rel == 1) {
					temp.put("quantidadePaginas",qtdPaginas);
				}
				jruh.setParametros(temp);
				
				String caminho = getArquivoJasper(jruh.getOrigem());

				JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 			
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(jruh.getDataSource());//seta DataSource a partir do List
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, jruh.getParametros(), ds);
				if(rel == 0) {
					qtdPaginas = jasperPrint.getPages().size() + 1;
				}
				jasperPrintList.add(jasperPrint);
				
				rel = ++rel % 2;
			}
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
			
		} catch (JRException jre) {
			log.error("Erro ao gerarPdf com dataSource: "+jre.getMessage(), jre);
			throw jre;
		}
		return baos.toByteArray();
	}
	
	/**
	 * Gera arquivo PDF agrupando relatórios destintos contidos numa lista, 
	 * podendo estes terem orientação ou tamanho de páginas diferentes.
	 * @param listaRelatorios
	 * @param configFile
	 * @return
	 * @throws JRException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static byte[] gerarPdfMultiRelDuasVias(final List<JasperReportUtilHelper> listaRelatorios) throws JRException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		try {
			for(JasperReportUtilHelper jruh : listaRelatorios) {
				Map temp = jruh.getParametros();
				//Pega o caminho fisico do arquivo .jasper
				temp.put(SUBREPORT_DIR, getCaminhoArquivosJasper());
				jruh.setParametros(temp);
				
				String caminho = getArquivoJasper(jruh.getOrigem());

				JasperReport report = (JasperReport) JRLoader.loadObject(caminho); 			
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(jruh.getDataSource());//seta DataSource a partir do List
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, jruh.getParametros(), ds);
				
				jasperPrintList.add(jasperPrint);
				jasperPrintList.add(jasperPrint);
			}
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
			
		} catch (JRException jre) {
			log.error("Erro ao gerarPdf com dataSource: "+jre.getMessage(), jre);
			throw jre;
		}
		return baos.toByteArray();
	}

	/**
	 * Path completo do arquivo jasper.
	 * @param nomeJasper - Nome do Arquivo Jasper.
	 * @return Caso o parametro nomeJasper seja null retorna null, senao retorna o path completo do arquivo.
	 */
	public static String getArquivoJasper(final String nomeJasper) {
		if (nomeJasper == null)
			return null;
		File diretorio = new File(getCaminhoArquivosJasper());
		
		if(diretorio != null && diretorio.isDirectory()) {
			File[] arquivos = diretorio.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(final File dir, final String name) {
					return name.endsWith(".jasper");
				}
			});
			for(File file : arquivos) {
				if(file.getPath().endsWith(nomeJasper))
					return file.getPath();
			}
		}
		return null;
	}
	
	/**
	 * Busca o Caminho Físico dos arquivos .jasper.
	 * @return
	 */
	public static String getCaminhoArquivosJasper() {
		return PotiErpProperties.getInstance().getJasperPath();
	}

}