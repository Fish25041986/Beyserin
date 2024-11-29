package unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.beyserin.prueba.BeyserinPruebaApplication;
import com.beyserin.prueba.dto.ClienteDTO;
import com.beyserin.prueba.exceptions.NoDataFoundException;
import com.beyserin.prueba.exceptions.ResourceNotFoundException;
import com.beyserin.prueba.service.ClienteService;


//pruebas de integracion carga toda la aplicacion
@SpringBootTest(classes = BeyserinPruebaApplication.class)
@AutoConfigureMockMvc
public class ClienteControllerTest {

	// Inyecto MockMvc para simular peticiones HTTP
    @Autowired
    private MockMvc mockMvc;  

    // Uso @MockBean para simular el servicio
    @MockBean
    private ClienteService clienteService;  

    @Test
    void obtenerCliente_tipoDocumentoInvalido() throws Exception {
        // Configuro el comportamiento del mock de clienteService
        when(clienteService.obtenerClienteMockeado("X", "12345678"))
            .thenThrow(new ResourceNotFoundException("Verifica el tipo del documento"));

        // Realizo una solicitud GET y verifico el error 400
        mockMvc.perform(get("/api/v1/clientes/X/12345678"))
             // Verific el código de estado que deberia ser 400
            .andExpect(status().isBadRequest())
             // Verifico el mensaje dentro del JSON el cual debe ser igual al del error
            .andExpect(jsonPath("$.error").value("Verifica el tipo del documento"))
            // Verifico el código de estado dentro del JSON
            .andExpect(jsonPath("$.status").value(400))
            // Verifico el valor de 'path' (con "uri=")
            .andExpect(jsonPath("$.path").value("uri=/api/v1/clientes/X/12345678"));
    }

    @Test
    void obtenerCliente_clienteNoEncontrado() throws Exception {
        when(clienteService.obtenerClienteMockeado("C", "99999999"))
            .thenThrow(new NoDataFoundException("Cliente no encontrado"));

        mockMvc.perform(get("/api/v1/clientes/C/99999999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").value("Cliente no encontrado"))
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.path").value("uri=/api/v1/clientes/C/99999999"));
    }

    @Test
    void obtenerCliente_clienteEncontrado() throws Exception {
        // Preparar datos de prueba
        ClienteDTO clienteDTO = new ClienteDTO("Oscar", "Andres", "Aguirre", "Ducuara", "3214535946", "Cr 90 bis 3 76-51", "Bogotá");

        // Configura el comportamiento del mock de clienteService con datos requeridos
        when(clienteService.obtenerClienteMockeado("C", "23445322"))
            .thenReturn(clienteDTO);

        // Realiza una solicitud GET y verifico el éxito
        mockMvc.perform(get("/api/v1/clientes/C/23445322"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.primerNombre").value("Oscar"))
            .andExpect(jsonPath("$.ciudad").value("Bogotá"));
    }
}