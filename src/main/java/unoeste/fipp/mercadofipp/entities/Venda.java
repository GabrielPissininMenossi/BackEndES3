package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ven_id")
    private Long id;
    @Column(name = "ven_peso")
    private Double peso;
    @Column(name = "ven_preco")
    private double preco;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario;

    public Venda(Long id, double peso, double preco, Usuario usuario) {
        this.id = id;
        this.peso = peso;
        this.preco = preco;
        this.usuario = usuario;
    }

    public Venda() {
        this(0L,0,0,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
