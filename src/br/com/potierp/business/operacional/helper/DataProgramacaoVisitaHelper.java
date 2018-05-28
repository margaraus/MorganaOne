package br.com.potierp.business.operacional.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.potierp.model.DataProgramacaoVisita;
import br.com.potierp.model.ProgramacaoVisita;

public class DataProgramacaoVisitaHelper {
	
	private static Map<Integer, List<DataProgramacaoVisitaHelper>> mapaDataProgramacaoVisitaHelper;
	
	private boolean selecionado;
	
	private DataProgramacaoVisita dataProgramacaoVisita;
	
	public DataProgramacaoVisitaHelper() {
		super();
	}
	
	DataProgramacaoVisitaHelper(final boolean selecionado, final DataProgramacaoVisita dataProgramacaoVisita) {
		this.selecionado = selecionado;
		this.dataProgramacaoVisita = dataProgramacaoVisita;
	}
	
	public static Map<Integer, List<DataProgramacaoVisitaHelper>> gerarListaDatasHelper(final ProgramacaoVisita programacaoVisita) {
		List<DataProgramacaoVisita> listaDataProgramacaoVisitas = new ArrayList<DataProgramacaoVisita>(programacaoVisita.getDatasProgramacaoVisitas());
		
		mapaDataProgramacaoVisitaHelper = new HashMap<Integer, List<DataProgramacaoVisitaHelper>>();
		
		for(int mes = 0; mes < 12; mes++) {
			List<DataProgramacaoVisitaHelper>listaDatasHelper = new ArrayList<DataProgramacaoVisitaHelper>();
			List<Date> diasDoMes = getListaDiasDoMes(mes);
			for(Date diaDoMes : diasDoMes) {
				DataProgramacaoVisitaHelper helper = null;
				if(listaDataProgramacaoVisitas.isEmpty()) {
					helper = new DataProgramacaoVisitaHelper(false, geraDataProgramacaoVisitaVazio(programacaoVisita, diaDoMes));
					listaDatasHelper.add(helper);
				} else {
					boolean dataImportada = false;
					for(DataProgramacaoVisita dataProgVis : listaDataProgramacaoVisitas) {
						if(diaDoMes.equals(dataProgVis.getDataProgramada())) {
							if(dataProgVis.isNew()){
								dataProgVis.setIsVisitado(false);
								dataProgVis.setIsAguardando(true);
							}
							helper = new DataProgramacaoVisitaHelper(true, dataProgVis);
							dataImportada = true;
							break;
						}
					}
					if(!dataImportada) {
						helper = new DataProgramacaoVisitaHelper(false, geraDataProgramacaoVisitaVazio(programacaoVisita, diaDoMes));
					}
					
					listaDatasHelper.add(helper);
				}
			}
			mapaDataProgramacaoVisitaHelper.put(new Integer(mes), listaDatasHelper);
		}
		return mapaDataProgramacaoVisitaHelper;
	}
	
	private static List<Date> getListaDiasDoMes(final int mesSelecionado) {
		List<Date> diasDoDoMes = new ArrayList<Date>(); 
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, mesSelecionado);
		int ano = calendar.get(Calendar.YEAR);
		int ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for(int dia=1; dia<=ultimoDia; dia++) {
			calendar = new GregorianCalendar(ano, mesSelecionado, dia);
			diasDoDoMes.add(calendar.getTime());
		}
		
		return diasDoDoMes;
	}
	
	private static DataProgramacaoVisita geraDataProgramacaoVisitaVazio(final ProgramacaoVisita programacaoVisita, final Date diaDoMes) {
		DataProgramacaoVisita dataVazia = new DataProgramacaoVisita();
		dataVazia.setProgramacaoVisita(programacaoVisita);
		dataVazia.setDataProgramada(diaDoMes);
		dataVazia.setVisitado(false);
		dataVazia.setIsVisitado(false);
		dataVazia.setIsAguardando(true);
		return dataVazia;
	}
	
	public static List<DataProgramacaoVisita> recuperaListaDataProgramacaoVisita() {
		List<DataProgramacaoVisita> listaDatasProgramacaoVisitas = new ArrayList<DataProgramacaoVisita>();
		
		for(int mes=0; mes<12; mes++) {
			for(DataProgramacaoVisitaHelper helper : mapaDataProgramacaoVisitaHelper.get(mes)) {
				if(helper.selecionado) {
					if(helper.dataProgramacaoVisita.isNew()){
						helper.dataProgramacaoVisita.setIsVisitado(false);
						helper.dataProgramacaoVisita.setIsAguardando(true);
					}
					listaDatasProgramacaoVisitas.add(helper.dataProgramacaoVisita);
				}
			}
		}
		return listaDatasProgramacaoVisitas;
	}
	
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(final boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	public String getDataFormatada() {
		return dataProgramacaoVisita.getDataFormatada();
	}
	
	public String getVisitado() {
		return dataProgramacaoVisita.getVisitado() ? "SIM" : "NÃO"; 
	}
}