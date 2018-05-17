package br.com.potierp.business.operacional.comparator;

import java.util.Comparator;

import br.com.potierp.model.Cliente;

/**
 * 
 * @author Andrey Oliveira
 *
 */
public class NomeClienteComparator implements Comparator<Cliente> {

	@Override
	public int compare(Cliente cliente, Cliente outro) {
		return cliente.getNomeFantasia().compareTo(outro.getNomeFantasia());
	}

}
