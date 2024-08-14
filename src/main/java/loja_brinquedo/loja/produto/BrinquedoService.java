package loja_brinquedo.loja.produto;

import loja_brinquedo.loja.produto.dto.DadosAtualizacaoBrinquedo;
import loja_brinquedo.loja.produto.dto.DadosCadastroBrinquedo;
import loja_brinquedo.loja.produto.dto.DadosDetalhesBrinquedo;
import loja_brinquedo.loja.produto.dto.DadosDetalhesQuantidade;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrinquedoService {
    private final BrinquedoRepository repository;

    public Brinquedo cadastrar(DadosCadastroBrinquedo dados) {
        return repository.save(new Brinquedo(dados));
    }

    public List<Brinquedo> listar() {
        Sort ordenacao = Sort.by("categoria").descending().and(Sort.by("nome").ascending());
        return repository.findAll(ordenacao);
    }

    public Page<DadosDetalhesBrinquedo> consultar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhesBrinquedo::new);
    }


    public DadosDetalhesBrinquedo atualizar(DadosAtualizacaoBrinquedo dados) {
        var brinquedo = repository.getReferenceById(dados.id());
        brinquedo.atualizarDados(dados);
        return new DadosDetalhesBrinquedo(brinquedo);
    }
    public DadosDetalhesQuantidade atualizarQuantidade(Long idBrinquedo, int novaQuantidade) {
        var brinquedo = repository.findById(idBrinquedo)
                .orElseThrow(()-> new RuntimeException("Brinquedo não encontrado"));
        brinquedo.setQuantidade(novaQuantidade);
        repository.save(brinquedo);
        return new DadosDetalhesQuantidade(brinquedo.getId(),brinquedo.getQuantidade());

    }
    public DadosDetalhesQuantidade adicionarQuantidade(Long idBrinquedo, int quantidadeAdicionada) {
        var brinquedo = repository.findById(idBrinquedo)
                .orElseThrow(() -> new RuntimeException("Brinquedo não encontrado"));

        int novaQuantidade = brinquedo.getQuantidade() + quantidadeAdicionada;
        brinquedo.setQuantidade(novaQuantidade);
        repository.save(brinquedo);

        return new DadosDetalhesQuantidade(brinquedo.getId(), brinquedo.getQuantidade());
    }

    public DadosDetalhesBrinquedo detalhar(Long id) {
        var brinquedo = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Brinquedo com id: " + id + ", não encontrado"));
        return new DadosDetalhesBrinquedo(brinquedo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


}
