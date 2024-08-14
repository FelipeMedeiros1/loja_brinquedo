package loja_brinquedo.loja.compra;

import loja_brinquedo.loja.compra.dto.DadosCompra;
import loja_brinquedo.infra.exception.LojaException;
import loja_brinquedo.loja.produto.BrinquedoService;
import loja_brinquedo.loja.produto.dto.DadosDetalhesBrinquedo;
import loja_brinquedo.loja.carrinho.CarrinhoDeCompraRepository;
import loja_brinquedo.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CompraService {
    private final CarrinhoDeCompraRepository carrinhoRepository;
    private final CompraRepository compraRepository;
    private final BrinquedoService brinquedoService;
    private final DescontoService descontoService;
    private final UsuarioRepository usuarioRepository;


    public Compra criarCompra(Long idUsuario) {
        var usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var carrinho = usuario.getCarrinhoDeCompra();

        Map<Long, Integer> itensCompra = new HashMap<>(carrinho.getItens());

        for (Map.Entry<Long, Integer> item : itensCompra.entrySet()) {
            Long idBrinquedo = item.getKey();
            int quantidadeCarrinho = item.getValue();

            var brinquedo = brinquedoService.detalhar(idBrinquedo);
            if (brinquedo.quantidade() < quantidadeCarrinho) {
                throw new LojaException("O brinquedo com ID " + idBrinquedo + " está indisponível na quantidade desejada. Estoque[" + brinquedo.quantidade() + "]");
            }
        }
        var compra = Compra.builder()
                .usuario(usuario)
                .itens(itensCompra)
                .valorTotal(calcularValorTotal(itensCompra))
                .valorTotalComDesconto(calcularValorTotalComDesconto(itensCompra))
                .build();
        compraRepository.save(compra);

        for (Map.Entry<Long, Integer> item : itensCompra.entrySet()) {
            Long idBrinquedo = item.getKey();
            int quantidadeCarrinho = item.getValue();

            var brinquedo = brinquedoService.detalhar(idBrinquedo);

            brinquedoService.atualizarQuantidade(idBrinquedo, brinquedo.quantidade() - quantidadeCarrinho);
        }
        carrinho.getItens().clear();
        carrinhoRepository.save(carrinho);

        return compra;
    }

    public DadosCompra criarDadosCompra(Compra compra) {
        Map<Long, DadosDetalhesBrinquedo> itensComDetalhes = new HashMap<>();
        Map<Long, Integer> itensCompraQuantidades = compra.getItens();

        for (Map.Entry<Long, Integer> entrada : itensCompraQuantidades.entrySet()) {
            Long idBrinquedo = entrada.getKey();
            int quantidadeCarrinho = entrada.getValue();
            DadosDetalhesBrinquedo brinquedo = brinquedoService.detalhar(idBrinquedo);
            itensComDetalhes.put(idBrinquedo, brinquedo);
        }

        Map<Long, DadosCompra.ItemCompra> itensCompra = new HashMap<>();
        int quantidadeTotalEstoque = 0;
        int quantidadeTotalCarrinho = 0;

        for (Map.Entry<Long, DadosDetalhesBrinquedo> entrada : itensComDetalhes.entrySet()) {
            Long idBrinquedo = entrada.getKey();
            DadosDetalhesBrinquedo brinquedo = entrada.getValue();
            int quantidadeCarrinho = itensCompraQuantidades.get(idBrinquedo);

            quantidadeTotalEstoque += brinquedo.quantidade();
            quantidadeTotalCarrinho += quantidadeCarrinho;

            itensCompra.put(idBrinquedo, new DadosCompra.ItemCompra(brinquedo.nome(), quantidadeCarrinho, brinquedo.preco()));
        }

        return new DadosCompra(
                compra.getValorTotal(),
                compra.getValorTotalComDesconto(),
                itensCompra,
                quantidadeTotalCarrinho,
                compra.getDataCompra()
        );
    }


    public List<Compra> obterComprasUsuario(Long idUsuario) {
        List<Compra> compras = compraRepository.findByUsuarioId(idUsuario);
        return compras;
    }

    private BigDecimal calcularValorTotalComDesconto(Map<Long, Integer> itensCarrinho) {
        BigDecimal valorTotalComDescontoQuantidade = descontoService.calcularDescontoQuantidade(itensCarrinho);
        return descontoService.calcularDescontoValorTotal(valorTotalComDescontoQuantidade);
    }

    private BigDecimal calcularValorTotal(Map<Long, Integer> itensCarrinho) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Map.Entry<Long, Integer> item : itensCarrinho.entrySet()) {
            Long idBrinquedo = item.getKey();
            int quantidade = item.getValue();
            DadosDetalhesBrinquedo brinquedo = brinquedoService.detalhar(idBrinquedo);
            if (brinquedo != null) {
                valorTotal = valorTotal.add(brinquedo.preco().multiply(BigDecimal.valueOf(quantidade)));
            } else {
                throw new IllegalArgumentException("O brinquedo não foi encontrado");
            }
        }
        return valorTotal;
    }
}
