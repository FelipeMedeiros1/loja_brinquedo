package loja_brinquedo.loja.compra.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public record DadosCompra(
        BigDecimal valorTotal,
        BigDecimal valorTotalComDesconto,
        Map<Long, ItemCompra> itens,
        int quantidadeItens,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCompra
) {

    public record ItemCompra(
            String nome,
            int quantidade,
            BigDecimal preco
    ) {
    }
}