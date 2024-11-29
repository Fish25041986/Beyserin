package com.beyserin.prueba.entity;

import lombok.Data;

@Data
public class Cliente {
	
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String telefono;
	private String direccion;
	private String ciudad;
	
	public Cliente(String primerNombre, String segundoNombre, String primerApellido, 
             String segundoApellido, String telefono, String direccion, String ciudad) {
		  this.primerNombre = primerNombre;
		  this.segundoNombre = segundoNombre;
		  this.primerApellido = primerApellido;
		  this.segundoApellido = segundoApellido;
		  this.telefono = telefono;
		  this.direccion = direccion;
		  this.ciudad = ciudad;
    }
	

}

