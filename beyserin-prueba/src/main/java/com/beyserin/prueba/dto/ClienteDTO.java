package com.beyserin.prueba.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
	
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String telefono;
	private String direccion;
	private String ciudad;
	
	public  ClienteDTO(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
			String telefono, String direccion, String ciudad) {
		super();
		
		this.primerNombre=primerNombre;
		this.segundoNombre=segundoNombre;
		this.primerApellido=primerApellido;
		this.segundoApellido=segundoApellido;
		this.telefono=telefono;
		this.direccion=direccion;
		this.ciudad=ciudad;
	}
	
}
