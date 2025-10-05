package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

import java.util.List;

/**
 * Essa classe será avisada sobre uma mudança nos itens que a mesma está observando
 *    OBSERVER -> ele observa o item
 * */
@Entity
@Table(name = "usuario")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;
    @Column(name = "usr_name")
    private String nome;
    @Column(name = "usr_pass")
    private String senha;
    @Column(name = "usr_level")
    private String  nivel;

    //Construtores
    public Usuario(Long id, String nome, String senha, String nivel) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario(Long id) {
        this(0L,"","","");
        this.id = id;
    }

    public Usuario() {
        this(0L, "", "", "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    //será notificado a todo momento que o estoque for atualizado
    public void atualizarChegadaProduto(int quantidade) {
        //essa quantidade é o novo estoque do meu produto
        System.out.println("Usuario: "+this.id);
        System.out.print("Produto com mais estoque. ");
        System.out.println(quantidade+" em estoque do produto!");
    }

    public void atualizarVendaProduto(int quantidade){
        //essa quantidade é o novo estoque do meu produto
        System.out.println("Usuario: "+this.id);
        System.out.print("Produto com menos estoque. ");
        System.out.println(quantidade+" em estoque do produto!!");
    }


}
