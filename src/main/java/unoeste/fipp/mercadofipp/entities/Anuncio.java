package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa será a minha classe que irá implementar o Observer
 *  OBSERVABLE
 */
@Entity
@Table(name = "anuncio")
public class Anuncio {
    //realizando o teste
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anu_id")
    private Long id;
    @Column(name = "anu_title")
    private String titulo;
    @Column(name = "anu_date")
    private LocalDate data;
    @Column(name = "anu_desc")
    private String descricao;
    @Column(name = "anu_price")
    private double preco;

    @Column(name = "anu_est")
    private int estoque;
    @Column(name = "anu_peso")
    private double peso; //peso único

    //observers
    @OneToMany(mappedBy = "anuncio")//essa tabela irá conter o muitos para muitos de usuario e item
    private List<Anuncio_Observer> observers;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario; //quem faz o anúncio

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Categoria categoria;
    @OneToMany(mappedBy = "anuncio")
    private List<Pergunta> perguntas;
    @OneToMany(mappedBy = "anuncio")
    private List<Foto> foto;

    public Anuncio(Long id, String titulo, LocalDate data, String descricao, double preco, int estoque, double peso, Categoria categoria, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.preco = preco;
        this.usuario = usuario;
        this.categoria = categoria;
        this.observers = new ArrayList<>();
        this.peso = peso;
        this.estoque = estoque;
    }

    public Anuncio() {
        this(0L, "", null, "", 0, 0, 0, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque > -1) //se é uma quantidade válida
        {
            //notifico
            notificarObservers(estoque);

            //atualiza a informação da quantidade
            this.estoque = estoque;
        }
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    //adicionar observers na minha lista
    public void addObserver(Anuncio_Observer anuncioObserver) {
        if (!this.observers.contains(anuncioObserver)) {
            observers.add(anuncioObserver);
        }
    }

    public void removeObserver(Anuncio_Observer anuncioObserver) {
        if (this.observers.contains(anuncioObserver)) {
            this.observers.remove(anuncioObserver);
        }
    }

    private void notificarObservers(int quantidade) {
        //avisa os usuários
        if (this.estoque < quantidade) {
            //item foi icrementado
            //  comprei mais

            //aviso sobre compra de estoque, mais disponíveis para a venda
            for (Anuncio_Observer u : this.observers) {
                u.getUsuario().atualizarChegadaProduto(quantidade);
            }
        } else {
            if (this.estoque > quantidade) {
                //item foi decrementado
                //  vendi e/ou saiu do estoque

                //aviso sobre venda, estoque acabando
                for (Anuncio_Observer u : this.observers) {
                    u.getUsuario().atualizarVendaProduto(quantidade);
                }
            }
        }
    }
}
