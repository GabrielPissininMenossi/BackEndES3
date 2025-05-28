package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.mercadofipp.entities.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

    @Query(value = "SELECT * FROM usuario WHERE usr_name = :nome", nativeQuery = true)
    public Usuario getUsuarioByNome(@Param("nome") String nome);
}
