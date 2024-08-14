package loja_brinquedo.loja.compra;

import loja_brinquedo.loja.produto.BrinquedoService;
import loja_brinquedo.loja.produto.dto.DadosDetalhesBrinquedo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class DescontoService {

    @Autowired
    private BrinquedoService brinquedoService;

    public BigDecimal calcularDescontoValorTotal(BigDecimal valorTotal) {
        if (valorTotal == null || valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O carrinho está vazio.");
        }
        if (valorTotal.compareTo(BigDecimal.valueOf(1.000)) > 0) {
            BigDecimal desconto = valorTotal.multiply(BigDecimal.valueOf(0.05));
            return valorTotal.subtract(desconto);
        }
        return valorTotal;
    }

    public BigDecimal calcularDescontoQuantidade(Map<Long, Integer> itensCarrinho) {
        if (itensCarrinho == null){
            throw new IllegalArgumentException("Não há itens no carrinho");
        }
        BigDecimal valorTotalComDesconto = BigDecimal.ZERO;
        for (Map.Entry<Long, Integer> entrada : itensCarrinho.entrySet()) {
            Long idBrinquedo = entrada.getKey();
            int quantidade = entrada.getValue();

            DadosDetalhesBrinquedo brinquedo = brinquedoService.detalhar(idBrinquedo);

            if (brinquedo != null) {
                if (quantidade >= 3) {
                    BigDecimal desconto = brinquedo.preco().multiply(BigDecimal.valueOf(0.1));
                    BigDecimal precoComDesconto = brinquedo.preco().subtract(desconto);
                    valorTotalComDesconto = valorTotalComDesconto.add(precoComDesconto.multiply(BigDecimal.valueOf(quantidade)));
                } else {
                    valorTotalComDesconto = valorTotalComDesconto.add(brinquedo.preco().multiply(BigDecimal.valueOf(quantidade)));
                }
            } else {
                throw new IllegalArgumentException("Item: " + itensCarrinho + ", não foi encontrado");
            }
        }
        return valorTotalComDesconto;
    }

}
