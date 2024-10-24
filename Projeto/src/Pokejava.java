import java.util.Scanner;


public class Pokejava {

    public static void main(String[] args) {

        boolean jogoAtivo = true;

        Scanner input = new Scanner(System.in);


        Usuario player1 = new Usuario("Joao");
        Usuario player2 = new Usuario("Maria");
        player1.party.escolherPokemon(true,6);
        player2.party.escolherPokemon(true, 6);

        while (player1.party.partyEstaViva() && player2.party.partyEstaViva()){
            Batalha player1Batalha = new Batalha(player1,player2.party);
            player1Batalha.interfaceBatalha();
        }
    }
}