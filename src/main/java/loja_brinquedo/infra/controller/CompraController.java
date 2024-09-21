package loja_brinquedo.infra.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;

import loja_brinquedo.loja.compra.dto.DadosCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import loja_brinquedo.loja.compra.Compra;

import loja_brinquedo.loja.compra.CompraService;


@RestController
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @PostMapping("/{idUsuario}")
    @Transactional
    @Schema(description = "Finalizar compra")
    public ResponseEntity<DadosCompra> finalizarCompra(@PathVariable Long idUsuario) {
        Compra compra = compraService.criarCompra(idUsuario);
        DadosCompra dadosCompra = compraService.criarDadosCompra(compra);
        try {
            return ResponseEntity.ok(dadosCompra);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}