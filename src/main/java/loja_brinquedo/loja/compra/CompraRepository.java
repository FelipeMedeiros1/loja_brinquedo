package loja_brinquedo.loja.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
    List<Compra> findByUsuarioId(Long idUsuario);
}
