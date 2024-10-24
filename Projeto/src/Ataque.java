
import java.util.ArrayList;
import java.util.List;

public class Ataque {
    private String nome;
    private int  dano;
    private String tipo;
    private double chanceAcerto;
    private double velocidade_de_ataque;


    public Ataque(String nome,int dano,String tipo, double chanceAcerto, double velocidade_de_ataque){
        this.nome = nome;
        this.dano = dano;
        this.tipo = tipo;
        this.chanceAcerto = chanceAcerto;
        this.velocidade_de_ataque = velocidade_de_ataque;
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    public String getTipo() {
        return tipo;
    }

    public double getChanceAcerto() {
        return chanceAcerto;
    }



    @Override

    public String toString(){
        return "ataque "+ nome + ", dano: "+  dano;
    }

    public static List<Ataque> criarAtaques() {
        return AtaquesLista.criarAtaquesDeArquivo();
    }
}