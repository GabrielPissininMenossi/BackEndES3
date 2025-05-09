package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "foto_anuncio")
public class Foto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fot_id")
    private long id;
    @Column(name = "fot_file")
    private String nomeArquivo;
    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    public Foto(long id, String nomeArquivo, Anuncio anuncio) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.anuncio = anuncio;
    }

    public Foto(String nomeArquivo, Anuncio anuncio) {
        this.nomeArquivo = nomeArquivo;
        this.anuncio = anuncio;
    }

    public Foto() {
        this(0L, "", null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
