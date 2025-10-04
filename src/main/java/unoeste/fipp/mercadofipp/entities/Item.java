package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//OBSERVABLE -> quem de fato está sendo observado
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "it_qtde")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "ven_id")
    private Venda venda;

    @ManyToMany
    @JoinTable(name = "id_iten_usuario")  //essa tabela irá conter o muitos para muitos de usuario e item
    private List<Usuario> observers = new ArrayList<>();

    public Item(Long id, int quantidade, Anuncio anuncio, Venda venda) {
        this.id = id;
        this.anuncio = anuncio;
        this.venda = venda;
        this.quantidade = quantidade;
        this.observers = new ArrayList<>(); //inicializar a lista de observers
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade > 0) //se é uma quantidade válida
        {
            //notifico
            notificarObservers(quantidade);

            //atualiza a informação da quantidade
            this.quantidade = quantidade;
        }
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

    //preciso verificar se isso quebra a orientação a objetos
//    public List<Usuario> getObservers() {
//        return observers;
//    }
//
//    public void setObservers(List<Usuario> observers) {
//        this.observers = observers;
//    }

    //adicionar observers na minha lista
    public void addObserver(Usuario usuario) {
        if(!this.observers.contains(usuario)) {
            observers.add(usuario);
        }
//        boolean temUsuario = false;
//        for (int i = 0; i < observers.size(); i++) {
//            if(observers.get(i).getId().equals(usuario.getId()))
//                temUsuario = true;
//        }
//        if(temUsuario == false)
//            observers.add(usuario);
    }

    private void notificarObservers(int quantidade) {
        //avisa os usuários
        if(this.quantidade < quantidade) {
            //item foi icrementado
            //  comprei mais

            //aviso sobre compra de estoque, mais disponíveis para a venda
            for(Usuario u : this.observers) {
                u.atualizarChegadaProduto(quantidade);
            }
        }
        else
        {
            if(this.quantidade > quantidade)
            {
                //item foi decrementado
                //  vendi e/ou saiu do estoque

                //aviso sobre venda, estoque acabando
                for(Usuario u : this.observers) {
                    u.atualizarVendaProduto(quantidade);
                }
            }
        }
    }
}
