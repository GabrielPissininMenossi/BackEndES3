package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;
import unoeste.fipp.mercadofipp.entities.abstratas.Comercio;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ven_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "venda")//essa tabela irá conter o muitos para muitos de usuario e item
    private List<Item> item;

    public Venda(Long id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.item = new ArrayList<>();
    }

    public Venda() {
        this(0L,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Item> getItens() {
        return item;
    }

    public void setItens(List<Item> itens) {
        this.item = itens;
    }
}
