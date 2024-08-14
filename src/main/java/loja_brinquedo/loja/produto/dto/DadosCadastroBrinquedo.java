package loja_brinquedo.loja.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import loja_brinquedo.loja.produto.Categoria;
import loja_brinquedo.loja.produto.Material;

import java.math.BigDecimal;

public record DadosCadastroBrinquedo(

        @NotBlank
        String nome,
        @NotNull
        String corPredominante,

        Categoria categoria,

        Material material,
        @NotNull
        String peso,
        @NotNull
        BigDecimal preco,
        @NotNull
        int quantidade

) {

}
