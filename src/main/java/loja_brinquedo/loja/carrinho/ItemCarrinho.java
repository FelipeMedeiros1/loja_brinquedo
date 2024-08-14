package loja_brinquedo.loja.carrinho;

import jakarta.persistence.*;
import loja_brinquedo.loja.produto.Brinquedo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Brinquedo brinquedo;
    private int quantidade;
}
