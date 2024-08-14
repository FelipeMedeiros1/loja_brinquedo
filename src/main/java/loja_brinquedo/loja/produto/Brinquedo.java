package loja_brinquedo.loja.produto;

import jakarta.persistence.*;
import loja_brinquedo.loja.produto.dto.DadosAtualizacaoBrinquedo;
import loja_brinquedo.loja.produto.dto.DadosCadastroBrinquedo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Brinquedo")
@Table(name = "brinquedo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Brinquedo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String corPredominante;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private Material material;
    private String peso;
    private BigDecimal preco;
    private int quantidade;


    public Brinquedo(DadosCadastroBrinquedo dados) {
        this.nome = dados.nome();
        this.corPredominante = dados.corPredominante();
        this.categoria = dados.categoria();
        this.material = dados.material();
        this.peso = dados.peso();
        this.preco = dados.preco();
        this.quantidade = dados.quantidade();

    }


    public void atualizarDados(DadosAtualizacaoBrinquedo dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.corPredominante() != null) {
            this.corPredominante = dados.corPredominante();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
        if (dados.material() != null) {
            this.material = dados.material();
        }
        if (dados.peso() != null) {
            this.peso = dados.peso();
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
        if (dados.quantidade() > 0){
            this.quantidade= dados.quantidade();
        }
    }
}
