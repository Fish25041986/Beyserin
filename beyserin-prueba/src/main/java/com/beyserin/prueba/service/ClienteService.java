package com.beyserin.prueba.service;

import org.springframework.stereotype.Service;

import com.beyserin.prueba.dto.ClienteDTO;
import com.beyserin.prueba.dto.ClienteDtoConverter;
import com.beyserin.prueba.entity.Cliente;
import com.beyserin.prueba.exceptions.InternalServerException;
import com.beyserin.prueba.exceptions.NoDataFoundException;
import com.beyserin.prueba.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {
	
	 private final ClienteDtoConverter clienteDtoConverter;
	 
	    /**
	     * Constructor de este servicio.
	     *
	     * Este constructor inyecta el convertidor de DTOs, permitiendo la conversión de entidades a objetos DTO.
	     * Segun las buenas parcticas y estandares de la industria.
	     *
	     * @param clienteDtoConverter El convertidor que se utilizará para transformar la entidad Cliente a ClienteDTO.
	     */
	    public ClienteService(ClienteDtoConverter clienteDtoConverter) {
	        this.clienteDtoConverter = clienteDtoConverter;
	    }

	    /**
	     * Obtiener el cliente simulado basado en el tipo y número de documento que llegan.
	     *
	     * Este metodo simula la busqueda de un cliente y devuelve un objeto tipo ClienteDTO
	     * si se encuentra el cliente correspondiente dado a los criterios de búsqueda.
	     *
	     * **Pasos:**
	     * 1. **Verificar tipo de documento:** Se valida que el tipo de documento sea "C" decedula  o "P" de pasaporte. 
	     *    Si no es así, lanza una excepción `ResourceNotFoundException` mostrando el metodo y el error 400.
	     * 2. **Lógica para obtener el cliente:** Se verifica si el numero de documento es "23445322" 
	     *    y el tipo de documento es "C". Si se cumple esta condición, se crea un objeto `Cliente`.
	     * 3. **Conversión a DTO:** El objeto `Cliente` se convierte en un objeto `ClienteDTO` 
	     *    utilizando el convertidor `clienteDtoConverter`.
	     * 4. **Manejo de errores:** Si no se encuentra un cliente, se lanza una excepción `NoDataFoundException` 
	     *    mostrando el metodo y el error  (error 404).
	     * 5. **Manejo de excepciones generales:** Cualquier excepción inesperada que ocurra durante 
	     *    el proceso de obtenerClienteMockeado se lanza como `InternalServerException` (error 500).
	     *
	     * @param tipoDocumento El tipo de documento del cliente (C es para cédula, P es para pasaporte).
	     * @param numeroDocumento El número de documento del cliente.
	     * @return Un objeto `ClienteDTO` que representa al cliente encontrado.
	     * @throws ResourceNotFoundException Si el tipo de documento es inválido.
	     * @throws NoDataFoundException Si no se encuentra un cliente correspondiente.
	     * @throws InternalServerException Si ocurre un error inesperado durante la ejecución.
	     */
	    public ClienteDTO obtenerClienteMockeado(String tipoDocumento, String numeroDocumento) {

	            // Verificación de tipo de documento
	            if (!tipoDocumento.equals("C") && !tipoDocumento.equals("P")) {
	                throw new ResourceNotFoundException("Verifica el tipo del documento"); // Error 400
	            }

	            // Aqui obtengo el cliente
	            if (numeroDocumento.equals("23445322") && tipoDocumento.equals("C")) {
	                Cliente cliente = new Cliente("Oscar", "Andres", "Aguirre", "Ducuara", "3214535946", "Cr 90 bis 3 76-51", "Bogotá");
	                return clienteDtoConverter.clienteDTO(cliente); // Convierto a ClienteDTO
	            } else {
	                throw new NoDataFoundException("Cliente no encontrado"); // Error 404
	            }


	    }
}
