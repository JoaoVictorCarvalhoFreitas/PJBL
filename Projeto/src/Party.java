
import java.util.*;

public class Party {
    private ArrayList<Pokemon> party = new ArrayList<>();
//    pokemon*6


    //    adiciona um pokemon na party
    public void adPokemonParty(Pokemon pokemon) {
        if(party.size() < 6) {
            party.add(pokemon);
        }else{
            System.out.println("party esta cheia");
        }
    }

    public void removerPokemon(int ID) {
        this.party.remove(this.party.get(ID-1));
    }

    public boolean estaVivo(Pokemon pokemon) {
        System.out.println(pokemon.getNome());
        System.out.println(pokemon.getHp());
        return pokemon.getHp() > 0;
    }

    public boolean partyEstaViva() {
        if(!this.party.isEmpty()){
            for (Pokemon pokemon : this.party) {
                if ((pokemon.getHp() > 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void escolherPokemon(boolean randomico) {
        Random rand = new Random();
        int tamanhoParty = 5;
        if (randomico) {
            for (int i = 0; i < tamanhoParty; i++) {
                int numRandom = rand.nextInt();
                this.adPokemonParty(Pokemon.getPokemon().get(numRandom));
            }
        }
    }

    public void escolherPokemon(boolean randomico, int tamanhoParty) {
        Random rand = new Random();
        if (randomico) {
            for (int i = 0; i < tamanhoParty; i++) {
                int numRandom = rand.nextInt(149);
                this.adPokemonParty(Pokemon.getPokemon().get(numRandom));
            }
        }
    }

    public void trocarPokemon(){
        Scanner input = new Scanner(System.in);
        for(int i = 0;i < this.party.size();i++){
            System.out.println(i+1 +" --- " + this.party.get(i).getNome());
        }
        int indice = input.nextInt()-1;
        input.nextLine();
        System.out.println("sua escolha: ");

        System.out.println("voce trocou " + this.party.getFirst().getNome() + " por " + this.party.get(indice).getNome());
        ;
        Collections.swap(this.party, 0, indice);
    }

    public void trocarPokemon(int indice){
        Collections.swap(this.party, 0, indice);
    }

    public ArrayList<Pokemon> getParty(){
        return this.party;
    }


    public void TrocaPokemon(int ind,int ind2){
        Pokemon pokeTroca = Pokemon.getPokemon().get(ind);
        Pokemon pokeTroca2 = Pokemon.getPokemon().get(ind2);
        this.party.set(ind, pokeTroca2);
        this.party.set(ind2, pokeTroca);
    }


    public void escolherPokemon() {
        int tamanhoParty = 6;
        Scanner input = new Scanner(System.in);

        List<Pokemon> pokemons = Pokemon.getPokemon();
        for (int i = 0; i < pokemons.size(); i++) {
            System.out.println(i + 1 + " --- " + pokemons.get(i).getNome()  );
        }

        for (int i = 0; i < tamanhoParty; i++) {

            System.out.println("Escolha o pokemon " + i);
            System.out.println("Digite 0 para sair.");
            int escolha = input.nextInt() -1;
            input.nextLine();
            if (escolha == -1) {
                break;
            }

            this.adPokemonParty(pokemons.get(escolha));

            System.out.println("pokemon " + pokemons.get(escolha).getNome() + " adicionado a party " );
            System.out.println();
            System.out.println(this.party.size() +" pokemons escolhidos de 6");
            System.out.println("Pokemons escolhidos: ");
            for (Pokemon pokemon2 : this.party) {
                System.out.println(pokemon2.getNome());
            }
        }
    }
}