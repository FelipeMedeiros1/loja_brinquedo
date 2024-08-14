package loja_brinquedo.loja.compra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.*;
import loja_brinquedo.usuario.Usuario;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ElementCollection
    @CollectionTable(name = "itens_compra", joinColumns = @JoinColumn(name = "compra_id"))
    @MapKeyColumn(name = "brinquedo_id")
    @Column(name = "quantidade")
    private Map<Long, Integer> itens = new HashMap<>();

    private BigDecimal valorTotal;

    private BigDecimal valorTotalComDesconto;

    private LocalDateTime dataCompra;

    @PrePersist
    public void prePersist() {
        this.dataCompra = LocalDateTime.now();
    }
}