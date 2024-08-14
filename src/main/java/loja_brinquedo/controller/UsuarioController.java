package loja_brinquedo.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import loja_brinquedo.usuario.dto.DadosAtualizacaoUsuario;
import loja_brinquedo.usuario.dto.DadosCadastroUsuario;
import loja_brinquedo.usuario.dto.DadosDetalhesUsuario;
import loja_brinquedo.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder criaUri) {
        try {
            var usuario = service.cadastrar(dados);
            URI uri = criaUri.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhesUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
        try {
            return ResponseEntity.ok(service.atualizar(dados));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhesUsuario>> consultar(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {
        try {
            return ResponseEntity.ok(service.consultar(paginacao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.detalhar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().header("Mensagem", "Usuário removido com sucesso!").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
