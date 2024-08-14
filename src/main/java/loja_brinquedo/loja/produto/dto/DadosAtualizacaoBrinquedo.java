package loja_brinquedo.loja.produto.dto;

import jakarta.validation.constraints.NotNull;
import loja_brinquedo.loja.produto.Categoria;
import loja_brinquedo.loja.produto.Material;

import java.math.BigDecimal;

public record DadosAtualizacaoBrinquedo(
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
}
