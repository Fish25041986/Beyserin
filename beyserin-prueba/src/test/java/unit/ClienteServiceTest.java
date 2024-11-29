package unit;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.beyserin.prueba.dto.ClienteDTO;
import com.beyserin.prueba.dto.ClienteDtoConverter;
import com.beyserin.prueba.exceptions.NoDataFoundException;
import com.beyserin.prueba.exceptions.ResourceNotFoundException;
import com.beyserin.prueba.service.ClienteService;

//Pruebas unitarias para probar la logica del negocio
class ClienteServiceTest {

    @Mock
    private ClienteDtoConverter clienteDtoConverter;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerClienteMockeado_tipoDocumentoInvalido() {
        // Ejecutar el método y verificar la excepción
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            clienteService.obtenerClienteMockeado("X", "123456");
        });

        // Verificar el mensaje de la excepción
        assertEquals("Verifica el tipo del documento", exception.getMessage());
    }

    @Test
    void obtenerClienteMockeado_clienteNoEncontrado() {
        // Ejecutar el método y verificar la excepción
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> {
            clienteService.obtenerClienteMockeado("C", "99999999");
        });

        // Verificar el mensaje de la excepción
        assertEquals("Cliente no encontrado", exception.getMessage());
    }

    @Test
    void obtenerClienteMockeado_clienteEncontrado() {
        // Preparar datos de prueba
        ClienteDTO clienteDTO = new ClienteDTO("Oscar", "Andres", "Aguirre", "Ducuara", "3214535946", "Cr 90 bis 3 76-51", "Bogotá");

        when(clienteDtoConverter.clienteDTO(any())).thenReturn(clienteDTO);

        // Ejecutar el método
        ClienteDTO resultado = clienteService.obtenerClienteMockeado("C", "23445322");

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals("Oscar", resultado.getPrimerNombre());
        assertEquals("Bogotá", resultado.getCiudad());

        // Verificar interacción con el mock
        verify(clienteDtoConverter, times(1)).clienteDTO(any());
    }
}
