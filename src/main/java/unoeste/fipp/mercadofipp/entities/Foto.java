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
    private byte[] arquivo;
    @Transient // Atributo não será persistido
    private String img64;
    @Column(name = "fot_ext")
    private String extensao;
    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    public Foto(long id, byte[] arquivo, Anuncio anuncio) {
        this.id = id;
        this.arquivo = arquivo;
        this.anuncio = anuncio;
    }

    public Foto(byte[] arquivo, Anuncio anuncio) {
        this.anuncio = anuncio;
        this.anuncio = anuncio;
    }

    public Foto() {
        this(0L, null, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public String getImg64() {
        return img64;
    }

    public void setImg64(String img64) {
        this.img64 = img64;
    }
}
