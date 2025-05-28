package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.mercadofipp.entities.Anuncio;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>
{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pergunta_anuncio (per_text, anu_id) VALUES (:texto, :id_anuncio)", nativeQuery = true) // : para inserir variavel
    public void addPergunta(@Param("texto") String texto,@Param("id_anuncio") long id_anuncio); // por estar em uma interface, não é possivel ter corpo

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO foto_anuncio (fot_file, anu_id, fot_ext) VALUES (:file, :id_anuncio, :extensao)", nativeQuery = true)
    public void addFoto(@Param("file") byte[] file, @Param("id_anuncio") long id_anuncio, @Param("extensao") String extensao);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM anuncio WHERE usr_id = :id_usuario", nativeQuery = true)
    public List<Anuncio> getIdUsuario(@Param("id_usuario") long id_usuario);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM anuncio WHERE anu_title ILIKE :filtro", nativeQuery = true)
    public List<Anuncio> getAnuncioFiltro(@Param("filtro") String filtro);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pergunta_anuncio set per_resp = :resposta where per_id = :idPergunta", nativeQuery = true)
    public void addResposta(@Param("resposta") String resposta,@Param("idPergunta") Long idPergunta);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM foto_anuncio WHERE anu_id = :idAnuncio", nativeQuery = true)
    public void deleteFoto(@Param("idAnuncio") Long idAnuncio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pergunta_anuncio WHERE anu_id = :idAnuncio", nativeQuery = true)
    public void deletePergunta(@Param("idAnuncio") Long idAnuncio);
}
