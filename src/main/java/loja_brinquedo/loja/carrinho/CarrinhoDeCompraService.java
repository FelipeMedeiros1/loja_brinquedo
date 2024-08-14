package loja_brinquedo.loja.carrinho;

import loja_brinquedo.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarrinhoDeCompraService {

    private final CarrinhoDeCompraRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoDeCompra obterCarrinho(Long idUsuario) {
        var usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        CarrinhoDeCompra carrinho = usuario.getCarrinhoDeCompra();
        if (carrinho == null) {
            carrinho = new CarrinhoDeCompra();
            usuario.setCarrinhoDeCompra(carrinho);
            carrinho = carrinhoRepository.save(carrinho);
        }
        return carrinho;
    }

    public void adicionar(Long idUsuario, ItemCarrinho item) {
        CarrinhoDeCompra carrinho = obterCarrinho(idUsuario);
        carrinho.adicionar(item.getBrinquedo().getId(), item.getQuantidade());
        carrinhoRepository.save(carrinho);
    }

    public void remover(Long idUsuario, Long idItem) {
        CarrinhoDeCompra carrinho = obterCarrinho(idUsuario);
        carrinho.remover(idItem);
        carrinhoRepository.save(carrinho);
    }

}