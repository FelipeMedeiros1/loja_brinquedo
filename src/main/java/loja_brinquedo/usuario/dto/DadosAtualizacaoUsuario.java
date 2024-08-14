package loja_brinquedo.usuario.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(@NotNull Long id, String nome, String email) {
}
