

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonLista {

    public static List<Pokemon> criarPokemonsDeArquivo(String caminhoArquivo) {
        List<Ataque> ataques = Ataque.criarAtaques();

        List<Pokemon> pokemons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            // Pular o cabeçalho, se houver
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                if (partes.length == 5) {
                    String nome = partes[0];
                    int hp = Integer.parseInt(partes[1]);
                    String tipo = partes[2];
                    int nivel = Integer.parseInt(partes[3]);
//                    elementos estao sendo repetidos WHY??????


//                    percorre a lista(como um for each) separa os indices de ataques e transforma
                    List<Integer> indicesAtaques = Arrays.stream(partes[4].split(","))
                            .map(Integer::parseInt)
                            .toList();



                    List<Ataque> listaAtaques = indicesAtaques.stream()
                            .map(ataques::get)
                            .toList();

                    pokemons.add(new Pokemon(nome, hp, tipo, nivel, listaAtaques));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemons;
    }
}