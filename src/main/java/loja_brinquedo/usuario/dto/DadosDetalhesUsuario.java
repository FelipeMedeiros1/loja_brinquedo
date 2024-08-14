package loja_brinquedo.usuario.dto;

import loja_brinquedo.usuario.Usuario;
import loja_brinquedo.loja.carrinho.CarrinhoDeCompra;

public record DadosDetalhesUsuario(Long id, String nome, String email, CarrinhoDeCompra carrinhoDeCompra) {
    public DadosDetalhesUsuario(Usuario usuario) {
      this(usuario.getId(), usuario.getNome(), usuario.getEmail(),usuario.getCarrinhoDeCompra());
    }


}
