package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unoeste.fipp.mercadofipp.entities.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
