package loja_brinquedo.loja.produto.dto;

import jakarta.validation.constraints.NotNull;
import loja_brinquedo.loja.produto.Brinquedo;
import loja_brinquedo.loja.produto.Categoria;
import loja_brinquedo.loja.produto.Material;

import java.math.BigDecimal;

public record DadosDetalhesBrinquedo(
        @NotNull
        Long id,
        String nome,
        String corPredominante,
        Categoria categoria,
        Material material,
        String peso,
        BigDecimal preco,
        int quantidade
) {
    public DadosDetalhesBrinquedo(Brinquedo brinquedo) {
        this(brinquedo.getId(),
                brinquedo.getNome(),
                brinquedo.getCorPredominante(),
                brinquedo.getCategoria(),
                brinquedo.getMaterial(),
                brinquedo.getPeso(),
                brinquedo.getPreco(),
                brinquedo.getQuantidade());
    }

}
