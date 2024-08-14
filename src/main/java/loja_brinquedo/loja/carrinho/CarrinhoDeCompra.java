package loja_brinquedo.loja.carrinho;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "itens_carrinho", joinColumns = @JoinColumn(name = "carrinho_id"))
    @MapKeyColumn(name = "brinquedo_id")
    @Column(name = "quantidade")
    private Map<Long, Integer> itens = new HashMap<>();

    public void adicionar(Long id, int quantidade) {
        if (id == null) {
            throw new IllegalArgumentException("ID do brinquedo não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser um número positivo.");
        }
        itens.put(id, itens.getOrDefault(id, 0) + quantidade);
    }

    public void remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID: " + id + ", não pode ser nulo.");
        }
        itens.remove(id);
    }
}