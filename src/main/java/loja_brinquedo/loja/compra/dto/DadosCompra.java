package loja_brinquedo.loja.compra.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public record DadosCompra(
        BigDecimal valorTotal,
        BigDecimal valorTotalComDesconto,
        Map<Long, ItemCompra> itens,
        int quantidadeItens,
        LocalDateTime dataCompra
) {

    public record ItemCompra(
            String nome,
            int quantidade,
            BigDecimal preco
    ) {
    }
}