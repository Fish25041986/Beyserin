package com.beyserin.prueba.dto;

import org.springframework.stereotype.Component;

import com.beyserin.prueba.entity.Cliente;

@Component
public class ClienteDtoConverter {
	
	public ClienteDTO clienteDTO(Cliente cliente) {
		
		return new ClienteDTO(
				cliente.getPrimerNombre(), cliente.getSegundoNombre(),
				cliente.getPrimerApellido(), cliente.getSegundoApellido(),
				cliente.getTelefono(), cliente.getDireccion(),
				cliente.getCiudad());
	}

}
