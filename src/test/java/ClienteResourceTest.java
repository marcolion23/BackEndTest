import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Dto.ClienteDto;
import org.example.entities.Cliente;
import org.example.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ClienteResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private ClienteDto clienteDto;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDto = new ClienteDto();
        clienteDto.setCliNome("GameOnTeste");
        clienteDto.setCliCpf("12332112332");
        clienteDto.setEndRua("Rua Sistema Gamer");
        clienteDto.setEndNumero("195");
        clienteDto.setEndCidade("Cidade Gamer");
        clienteDto.setEndCep("12345-122");
        clienteDto.setEndEstado("RJ");
        clienteDto.setConCelular("98457-2314");
        clienteDto.setConTelefoneComercial("3333-4444");
        clienteDto.setConEmail("GameOnTeste@email.com");

        cliente = new Cliente(1L, "GameOnTeste", "12332112332");
    }

    @Test
    void deveRetornarTodosClientes() throws Exception {
        when(clienteService.findAll()).thenReturn(Arrays.asList(cliente));
        when(clienteService.toNewDto(any())).thenReturn(clienteDto);

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cliNome").value("GameOntTeste"))
                .andExpect(jsonPath("$[0].cliCpf").value("12332112332"));
    }

    @Test
    void deveRetornarClientePorId() throws Exception {
        when(clienteService.findById(1L)).thenReturn(cliente);
        when(clienteService.toNewDto(any())).thenReturn(clienteDto);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliNome").value("GameOnTeste"));
    }

    @Test
    void deveInserirNovoCliente() throws Exception {
        when(clienteService.fromDTO(any())).thenReturn(cliente);
        when(clienteService.insert(any())).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveAtualizarCliente() throws Exception {
        doNothing().when(clienteService).update(any(), any());

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveDeletarCliente() throws Exception {
        doNothing().when(clienteService).deleteCliente(1L);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
