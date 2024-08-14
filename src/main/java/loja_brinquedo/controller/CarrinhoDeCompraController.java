package loja_brinquedo.controller;

import loja_brinquedo.loja.carrinho.CarrinhoDeCompra;
import loja_brinquedo.loja.carrinho.ItemCarrinho;
import loja_brinquedo.loja.carrinho.CarrinhoDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoDeCompraController {

    @Autowired
    private CarrinhoDeCompraService service;

    @PostMapping("/adicionar/{idUsuario}/item")
    public ResponseEntity adicionarItem(@PathVariable Long idUsuario, @RequestBody ItemCarrinho item) {
        try {
            service.adicionar(idUsuario, item);
            return ResponseEntity.ok(service.obterCarrinho(idUsuario).getItens());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/usuario/{idUsuario}/item/{idItem}")
    public ResponseEntity removerItem(@PathVariable Long idUsuario, @PathVariable Long idItem) {
        try {
            service.remover(idUsuario, idItem);
            return ResponseEntity.noContent().header("Mensagem", "Item removido com sucesso!").build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<CarrinhoDeCompra> obterCarrinho(@PathVariable Long idUsuario) {
        try {
            return ResponseEntity.ok(service.obterCarrinho(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}