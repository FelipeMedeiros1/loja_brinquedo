package loja_brinquedo.usuario;

import loja_brinquedo.usuario.dto.DadosAtualizacaoUsuario;
import loja_brinquedo.usuario.dto.DadosDetalhesUsuario;
import loja_brinquedo.usuario.dto.DadosCadastroUsuario;
import loja_brinquedo.infra.exception.LojaException;
import loja_brinquedo.loja.carrinho.CarrinhoDeCompra;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario cadastrar(DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);
        usuario.setCarrinhoDeCompra(new CarrinhoDeCompra());

        boolean jaCadastrado = repository.existsByEmail(dados.email());
        if (jaCadastrado) {
            throw new LojaException("Email já cadastrados no sistema!");
        }
        return repository.save(usuario);
    }

    public Page<DadosDetalhesUsuario> consultar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDetalhesUsuario::new);
    }

    public DadosDetalhesUsuario atualizar(DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarDados(dados);
        return new DadosDetalhesUsuario(usuario);
    }

    public DadosDetalhesUsuario detalhar(Long id) {
        var usuario = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário com id: " + id + ", não encontrado"));
        return new DadosDetalhesUsuario(usuario);
    }

    public void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário com id: " + id + ", não encontrado");
        }
    }

}
