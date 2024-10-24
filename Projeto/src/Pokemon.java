import java.util.ArrayList;
import java.util.List;



public class Pokemon {
    private String nome;
    private int hp;
    private String tipo;
    private int nivel;
    private List<Ataque> ataques;

    public Pokemon(String nome, int hp, String tipo, int nivel,List<Ataque> ataques){
        this.nome = nome;
        this.hp = hp;
        this.tipo = tipo;
        this.nivel = nivel;
        this.ataques = ataques;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHp() {
        return hp;
    }

    public void diminuirHP(int hp) {
        this.hp -=hp;
    }

    public void aumentarHP(int hp) {
        this.hp += hp;
    }

    public String getTipo() {
        return tipo;

    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public static List<Pokemon> getPokemon(){
        return PokemonLista.criarPokemonsDeArquivo("Pokemons.txt");
    }

}

