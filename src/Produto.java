import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private boolean disponivel;

    public Produto(int id,String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
    }

    public void setDisponivel(){
        this.disponivel = true;
    }
    public void setIndisponivel(){
        this.disponivel = false;
    }

    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getPreco() {
        return preco;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }



}
