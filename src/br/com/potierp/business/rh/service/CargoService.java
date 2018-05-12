package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Cargo;

public interface CargoService {
	
	Cargo salvarCargo(Cargo cargo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirCargo(Cargo cargo)throws PotiErpException;
	
	void excluirListaCargo(List<Cargo> cargos) throws PotiErpException;
	
	Cargo consultarCargo(Cargo cargo)throws PotiErpException;
	
	List<Cargo> consultarTodosCargos() throws PotiErpException;
}