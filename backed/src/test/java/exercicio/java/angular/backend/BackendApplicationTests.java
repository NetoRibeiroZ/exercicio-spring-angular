package exercicio.java.angular.backend;
import exercicio.java.angular.backend.pastas.controller.PastaController;
import exercicio.java.angular.backend.pastas.model.Pasta;
import exercicio.java.angular.backend.pastas.service.IPastaService;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PastaController.class)
public class BackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPastaService pastaService;

    @Test
    public void testListAll() throws Exception {


        Pasta pasta1 = new Pasta();
        pasta1.setId(1L);
        pasta1.setNome("Pasta 1");

        Pasta pasta2 = new Pasta();
        pasta2.setId(2L);
        pasta2.setNome("Pasta 2");

        List<Pasta> pastas = Arrays.asList(pasta1, pasta2);

        when(pastaService.listAll(1L, "")).thenReturn(pastas);

        mockMvc.perform(MockMvcRequestBuilders.get("/setores/1/pastas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nome").value("Pasta 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].setorId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nome").value("Pasta 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].setorId").value(1L));
    }

    @Test
    public void testFindById() throws Exception {
        MockitoAnnotations.initMocks(this);

        Pasta pasta = new Pasta();
        pasta.setId(1L);
        pasta.setNome("Pasta 1");


        when(pastaService.findById(1L, 1L)).thenReturn(Optional.of(pasta));

        mockMvc.perform(MockMvcRequestBuilders.get("/setores/1/pastas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Pasta 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.setorId").value(1L));
    }
}