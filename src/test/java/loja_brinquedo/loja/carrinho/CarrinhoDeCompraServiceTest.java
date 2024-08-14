package loja_brinquedo.loja.carrinho;

import loja_brinquedo.loja.produto.Brinquedo;
import loja_brinquedo.usuario.Usuario;
import loja_brinquedo.usuario.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;


import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CarrinhoDeCompraServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private CarrinhoDeCompraRepository carrinhoRepository;

    @InjectMocks
    private CarrinhoDeCompraService carrinhoDeCompraService;

    @Test
    void obterCarrinho_usuarioExisteComCarrinho() {

        Usuario usuarioMock = new Usuario();
        CarrinhoDeCompra carrinhoMock = new CarrinhoDeCompra();
        usuarioMock.setCarrinhoDeCompra(carrinhoMock);

        // Configurar o retorno do usuarioRepository.findById()
        when(usuarioRepository.findById(any())).thenReturn(java.util.Optional.of(usuarioMock));

        // Chamar o método obterCarrinho()
        CarrinhoDeCompra carrinhoObtido = carrinhoDeCompraService.obterCarrinho(1L);

        // Verificar se o carrinho retornado é o mesmo do mock
        assert carrinhoObtido == carrinhoMock;


    }

    @Test
    void adicionar_itemAoCarrinho() {
        // Mock do usuário e do carrinho
        Usuario usuarioMock = new Usuario();
        CarrinhoDeCompra carrinhoMock =new CarrinhoDeCompra(); // Usar mock para verificar interações
        usuarioMock.setCarrinhoDeCompra(carrinhoMock);

        // Mock do item a ser adicionado
        Brinquedo brinquedoMock = new Brinquedo();
        brinquedoMock.setId(1L);
        ItemCarrinho itemMock = new ItemCarrinho();
        itemMock.setBrinquedo(brinquedoMock);
        itemMock.setQuantidade(2);

        // Configurar o retorno do usuarioRepository.findById()
        when(usuarioRepository.findById(anyLong())).thenReturn(java.util.Optional.of(usuarioMock));
        when(carrinhoRepository.save(any(CarrinhoDeCompra.class))).thenReturn(carrinhoMock);

        // Chamar o método adicionar()
        carrinhoDeCompraService.adicionar(1L, itemMock);

        // Verificar se o método adicionar foi chamado no carrinho com os parâmetros corretos
        verify(carrinhoMock).adicionar(brinquedoMock.getId(), itemMock.getQuantidade());

        // Verificar se o carrinhoRepository.save() foi chamado com o carrinho
        verify(carrinhoRepository).save(carrinhoMock);
    }

}