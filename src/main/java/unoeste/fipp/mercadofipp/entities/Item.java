package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "it_qtde")
    private int quatidade;

    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "ven_id")
    private Venda venda;

    public Item(Long id, int quatidade, Anuncio anuncio, Venda venda) {
        this.id = id;
        this.anuncio = anuncio;
        this.venda = venda;
        this.quatidade = quatidade;
    }

    public Item() {
        this(0L, 0, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(int quatidade) {
        this.quatidade = quatidade;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
