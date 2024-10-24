

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AtaquesLista {

    public static List<Ataque> criarAtaquesDeArquivo() {
        List<Ataque> ataques = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("ataques.txt"))) {
            String linha;
            // Pular o cabeçalho

            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");

                if (partes.length == 6) {
                    String nome = partes[1];
                    int poder = Integer.parseInt(partes[2]);
                    String tipo = partes[3];
                    double ChanceAcerto = Double.parseDouble(partes[4]);
                    double critico = Double.parseDouble(partes[5]);

                    ataques.add(new Ataque(nome, poder, tipo, ChanceAcerto, critico));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ataques;
    }
}