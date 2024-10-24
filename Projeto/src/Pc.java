
import java.util.ArrayList;

public class Pc {
    private final ArrayList<Pokemon> PC = new ArrayList<>();


    public void mostrarPokemonsPC(){
        for (Pokemon pokes : PC){
            System.out.println("Pokemon: " + pokes.getNome());
            System.out.println("hp = " + pokes.getHp());
            for(Ataque ataque : pokes.getAtaques()){
                System.out.println("ataques" + ataque.getNome());
            }
        }
    }

    public ArrayList<Pokemon> getPC(){
        return PC;
    }

    public void adicionaPoke(Pokemon poke){
        this.PC.add(poke);
    }

    public void removerPokemon(int id){
        PC.remove(PC.get(id-1));
    }
}

