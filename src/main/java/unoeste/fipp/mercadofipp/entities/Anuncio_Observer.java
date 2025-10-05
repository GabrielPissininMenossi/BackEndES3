package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "anuncio_observer")

public class Anuncio_Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anu_obs_id")
    private Long id_anuncio_observer;

    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario;

    public Anuncio_Observer(Long id_anuncio_observer, Anuncio anuncio, Usuario usuario) {
        this.id_anuncio_observer = id_anuncio_observer;
        this.anuncio = anuncio;
        this.usuario = usuario;
    }

    public Anuncio_Observer() {
        this(0L, null, null);
    }

    public Long getId_anuncio_observer() //chave primária
    {
        return id_anuncio_observer;
    }

    public void setId_anuncio_observer(Long id_anuncio_observer) {
        this.id_anuncio_observer = id_anuncio_observer;
    }

    public Usuario getUsuario() { // estrangeira
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Anuncio getAnuncio()  // estrangeira
    {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

}
