package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;
import unoeste.fipp.mercadofipp.entities.interfaces.Observable;
import unoeste.fipp.mercadofipp.entities.interfaces.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa será a minha classe que irá implementar o Observer
 *  OBSERVABLE
 */
@Entity
@Table(name = "anuncio")
public class Anuncio implements Observable {
    //chave primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anu_id")
    private Long id;

    //colunas comuns
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
    private double peso;

    //colunas de chaves estrangeiras
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario; //quem faz o anúncio
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Categoria categoria;

    //chaves estrangeiras que NÃO estão nessa tabela
    @OneToMany(mappedBy = "anuncio")
    private List<Pergunta> perguntas; //tabela de perguntas
    @OneToMany(mappedBy = "anuncio")
    private List<Foto> foto; //tabela de fotos

    @ManyToMany
    @JoinTable(
            name = "anuncio_observer", // nome da tabela intermediária
            joinColumns = @JoinColumn(name = "anu_id"), // coluna que representa este anúncio
            inverseJoinColumns = @JoinColumn(name = "usr_id") // coluna que representa o usuário
    )
    private List<Usuario> observers;

    public Anuncio(Long id, String titulo, LocalDate data, String descricao, double preco, int estoque, double peso, Categoria categoria, Usuario usuario) {
        //chave primária
        this.id = id;

        //outros atributos
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.peso = peso;

        //chaves estrangeiras
        this.usuario = usuario;
        this.categoria = categoria;

        //inicialização das listas
        this.observers = new ArrayList<>();
        this.perguntas = new ArrayList<>();
        this.foto = new ArrayList<>();
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
            notificar(estoque);

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

    public List<Usuario> getObservers() {
        return observers;
    }

    public void setObservers(List<Usuario> observers) {
        this.observers = observers;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!this.observers.contains((Usuario) observer)) {
            observers.add((Usuario) observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (this.observers.contains(observer)) {
           this.observers.remove(observer);
        }
    }

    @Override
    public void notificar(int quantidade) {
        if (this.estoque != quantidade) {
            //aviso sobre mudança no estoque
            for (Usuario u : this.observers) {
                u.atualizarEstoque(quantidade);
            }
        }
    }
}
