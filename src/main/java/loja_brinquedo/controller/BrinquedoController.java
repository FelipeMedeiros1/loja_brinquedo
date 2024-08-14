package loja_brinquedo.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import loja_brinquedo.loja.produto.dto.DadosAtualizacaoBrinquedo;
import loja_brinquedo.loja.produto.dto.DadosCadastroBrinquedo;
import loja_brinquedo.loja.produto.dto.DadosDetalhesBrinquedo;
import loja_brinquedo.loja.produto.BrinquedoService;
import loja_brinquedo.loja.produto.dto.DadosDetalhesQuantidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("brinquedo")
public class BrinquedoController {
    @Autowired
    private BrinquedoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroBrinquedo dados, UriComponentsBuilder criaUri) {
        try {
            var brinquedo = service.cadastrar(dados);
            URI uri = criaUri.path("/brinquedo/{id}").buildAndExpand(brinquedo.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhesBrinquedo(brinquedo));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{id}/quantidade")
    public ResponseEntity<DadosDetalhesQuantidade> adicionarQuantidade(@PathVariable Long id, @RequestBody DadosDetalhesQuantidade dados) {
        try {
            var dadosAtualizados = service.adicionarQuantidade(id, dados.quantidade());
            return ResponseEntity.ok(dadosAtualizados);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoBrinquedo dados) {
        try {
            return ResponseEntity.ok(service.atualizar(dados));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/quantidade")
    @Transactional
    public ResponseEntity<DadosDetalhesQuantidade> atualizarQuantidade(@PathVariable Long id, @RequestBody DadosDetalhesQuantidade dados ) {
        try {
           var dadosAtualizados  = service.atualizarQuantidade(id, dados.quantidade());
            return ResponseEntity.ok(dadosAtualizados );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping
    public ResponseEntity<Page<DadosDetalhesBrinquedo>> consultar(@PageableDefault(size = 20, sort = {"categoria"}) Pageable paginacao) {
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
            return ResponseEntity.noContent().header("Mensagem", "Brinquedo removido com sucesso!").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
