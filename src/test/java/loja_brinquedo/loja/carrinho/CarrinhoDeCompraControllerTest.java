package loja_brinquedo.loja.carrinho;

import com.fasterxml.jackson.databind.ObjectMapper;

import loja_brinquedo.infra.controller.CarrinhoDeCompraController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarrinhoDeCompraController.class)
public class CarrinhoDeCompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarrinhoDeCompraService carrinhoDeCompraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obterCarrinho_deveRetornarStatus200EItensDoCarrinho() throws Exception {
        // Arrange
        Long idUsuario = 1L;
        CarrinhoDeCompra carrinhoMock = new CarrinhoDeCompra();
        carrinhoMock.adicionar(1L, 2);
        carrinhoMock.adicionar(2L, 1);

        when(carrinhoDeCompraService.obterCarrinho(idUsuario)).thenReturn(carrinhoMock);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/carrinho/{idUsuario}", idUsuario)
                        .accept(MediaType.APPLICATION_JSON))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'itens': {'1': 2, '2': 1}}"));
    }
}