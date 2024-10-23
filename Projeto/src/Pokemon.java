import java.util.ArrayList;

public class Pokemon {
    private String nome;
    private String elemento;
    private ArrayList<Skills> ataques;

    public Pokemon(String nome, String elemento, ArrayList<Skills> ataques) {
        this.nome = nome;
        this.elemento = elemento;
        this.ataques = new ArrayList<Skills>();
    }
}
