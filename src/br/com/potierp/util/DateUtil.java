package br.com.potierp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.DateValidator;
/**
 * Classe utilit�ria para manipula��o de datas.
 *
 * @author 
 *  	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public class DateUtil {

	/**
	 * Representa Uma Hora em milisegundos.
	 */
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	
	/**
	 * Formato: dd/MM/yyyy.
	 */
	public static final String dd_MM_yyyy = "dd/MM/yyyy";

	/**
	 * Formato: dd/MM/yyyy HH:mm:ss.
	 */
	public static final String dd_MM_yyyy_HH_mm_ss = dd_MM_yyyy+" HH:mm:ss";
	
	/**
	 * Formato: yyyyMMdd.
	 */
	public static final String yyyyMMdd = "yyyyMMdd";

	/**
	 * Formato: yyyyMMddHHmm.
	 */
	public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

    /**
     * Formato: yyyyMMddHHmmss.
     */
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    
    /**
     * Formato: dd/MM/yyyy - EEEE.
     */
    public static final String dd_MM_yyyy_EEEE = "dd/MM/yyyy - EEEE";
    
	/**
	 * O formatador com o padr�o ptBr.
	 */
	private final SimpleDateFormat ptBrFormat = new SimpleDateFormat(dd_MM_yyyy);
	
	/**
	 * DateUtil.
	 */
	private static final DateUtil instance;
	
	static {
		instance = new DateUtil();
	}

	/**
	 * Construtor.
	 */
	private DateUtil() {
	}

	/**
	 * Metodo respons�vel pela valida��o e parse das datas.
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getValidDate(final String date) throws ParseException {
		if(!"".equals(date) && (date != null)) {
			DateValidator dateValidator = DateValidator.getInstance();
			// verifica se a data � valida antes de converter
			boolean isDataValida = dateValidator.isValid(date, new Locale("pt", "BR"));
			if(isDataValida)
				return DateUtils.parseDate(date, new String[]{dd_MM_yyyy});
		}
		return null;
	}
	
	/**
	 * Adiciona uma quantidade de dias na data informada.
	 * 
	 * @param date
	 * @param qtd
	 * @return
	 */
	public static Date addDias(final Date date, final int qtd){
		if(date != null){
			Calendar cal = DateUtil.convertDateToCalendar(date);
			cal.add(Calendar.DAY_OF_MONTH, qtd);
			return cal.getTime();
		}
		return date;
	}

	/**
	 * Converte date para extenso.
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToExtenso(final Date date){
		DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
        return dfmt.format(date); 
	}
	
	/**
	 * Converte Date para o formato ptBr com dia da semana.
	 * @param date
	 * @return
	 */
	public static String convertDateParaPtBrComDiaDaSemana(final Date date) {
		DateFormat dfmt = new SimpleDateFormat(dd_MM_yyyy_EEEE);
		return dfmt.format(date);
	}

	/**
	 * Obt�m a quantidade de dias entre duas datas.
	 *
	 * @param initDate
	 * @param finalDate
	 * @return
	 */
	public static long daysBetween(final Date initDate, final Date finalDate) {
		return (finalDate.getTime() - initDate.getTime() + ONE_HOUR)
				/ (ONE_HOUR * 24);
	}
	
	/**
	 * Retorna data de hoje.
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public static Date hoje() throws ParseException{
		Calendar hoje = Calendar.getInstance();
		hoje.setTime(new Date());
		hoje.set(Calendar.HOUR_OF_DAY,00);
		hoje.set(Calendar.MINUTE, 00);
		hoje.set(Calendar.SECOND, 00);
		hoje.set(Calendar.MILLISECOND, 000);
		return hoje.getTime();
	}
	
	/**
	 * Transforma um <code>Date</code> em <code>String</code>.
	 * 
	 * @param date
	 *            o <code>Date</code> a ser transformada em
	 *            <code>String</code>.
	 * @return a <code>String</code> correspondente ao <code>Date</code>.
	 */
	public static String dateToPtBrString(final Date date) {
		if (date == null || instance == null)
			return null;
		return instance.ptBrFormat.format(date);
	}
	
	/**
	 * Verifica se a data � hoje.
	 * 
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	public static Boolean isHoje(final Date data) throws ParseException{
		return DateUtilService.compararDatasSemHoras(hoje(), data) == 0;
	}
	
	/**
	 * Retorno o range de refer�ncias no formato yyyy/mm.
	 * @param refInicial formato yyyy/mm
	 * @param refFinal formato yyyy/mm
	 * @return
	 */
	public static List<String> getRangeReferencia(final String refInicial, final String refFinal){
		List<String> referencias = new ArrayList<String>();
		Integer mesInicial = new Integer(refInicial.substring(4, refInicial.length()));
		Integer mesFinal = new Integer(refFinal.substring(4, refInicial.length()));
		Integer anoInicial = new Integer(refInicial.substring(0, 4));
		Integer anoFinal = new Integer(refFinal.substring(0, 4));
		
		boolean isFirstRef = true;
		while(anoInicial <= anoFinal){
			Integer mesIni = 1;
			Integer mesFin = 12;
			if(isFirstRef){
				mesIni = mesInicial;
				isFirstRef = false;
			}
			if(!anoInicial.equals(anoFinal)){
				referencias.addAll(getRefs(mesIni, mesFin, anoInicial));
				anoInicial++;
			}else {
				referencias.addAll(getRefs(mesIni, mesFinal, anoInicial));
				anoInicial++;
			}
		}
		return referencias;
	}
	
	/**
	 * Retorna as refer�ncias.
	 * 
	 * @param mesInicial
	 * @param mesFinal
	 * @param ano
	 * @return
	 */
	private static List<String> getRefs(final Integer mesInicial,
			final Integer mesFinal, final Integer ano) {
		List<String> referencias = new ArrayList<String>();
		Integer mes = mesInicial;
		while(mes <= mesFinal){
			referencias.add(ano.toString().concat(mes < 10 ? "0" : "").concat(mes.toString()));
			mes++;
		}
		return referencias;
	}
	
	/**
	 * Converte Date para Calendar.
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar convertDateToCalendar(final Date date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static String convertDateToReferencia(final Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer mes = cal.get(Calendar.MONTH)+1;
		Integer ano = cal.get(Calendar.YEAR);
		String mesAno = mes + "/" + ano;
		return mesAno;
	}
	
	public static Boolean isSabado(final Calendar cal) {
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return true;
		}
		return false;
	}

	public static Boolean isDomingo(final Calendar cal) {
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return true;
		}
		return false;
	}
}