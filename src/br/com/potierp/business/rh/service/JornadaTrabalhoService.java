package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.HorariosJornada;
import br.com.potierp.model.JornadaTrabalho;

public interface JornadaTrabalhoService {
	
	JornadaTrabalho salvarJornadaTrabalho(JornadaTrabalho jornadaTrabalho)throws PotiErpException;
	
	void excluirJornadaTrabalho(JornadaTrabalho jornadaTrabalho)throws PotiErpException;
	
	void excluirListaJornadaTrabalho(List<JornadaTrabalho> jornadasTrabalho)throws PotiErpMensagensException, PotiErpException;
	
	List<JornadaTrabalho> consultarJornadaTrabalho(JornadaTrabalho jornadaTrabalho)throws PotiErpException;
	
	List<JornadaTrabalho> consultarTodasJornadasTrabalho() throws PotiErpException;
	
	HorariosJornada salvarHorariosJornada(HorariosJornada horariosJornada)throws PotiErpException;
	
	void excluirHorariosJornada(HorariosJornada horariosJornada)throws PotiErpException;
	
	List<HorariosJornada> consultarHorariosJornada(HorariosJornada horariosJornada)throws PotiErpException;
	
	JornadaTrabalho consultarPorIdComIntervalos(Long id) throws PotiErpException;
}