package loja_brinquedo.usuario;


import jakarta.persistence.*;
import loja_brinquedo.usuario.dto.DadosAtualizacaoUsuario;
import loja_brinquedo.usuario.dto.DadosCadastroUsuario;
import loja_brinquedo.loja.carrinho.CarrinhoDeCompra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CarrinhoDeCompra carrinhoDeCompra;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();

    }

    public void atualizarDados(DadosAtualizacaoUsuario dados) {
        if (dados.nome() != null)
            this.nome = dados.nome();
        if (dados.email() != null)
            this.email = dados.email();
    }
}