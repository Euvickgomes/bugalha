package bugalha;

import java.util.Scanner;
import java.util.ArrayList;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private Scanner scanner;

    public Jogo(Scanner scanner) {
        setScanner(scanner);
    }

    public Scanner getScanner() {
        return scanner;
    }

    private void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void executarPartida() {
        // Entrada dos nomes
        System.out.print("Nome do Jogador 1: ");
        jogador1 = new Jogador(scanner.nextLine());

        System.out.print("Nome do Jogador 2: ");
        jogador2 = new Jogador(scanner.nextLine());

        // 2. Começa pelos dois jogadores, alternando até um tabuleiro encher
        Jogador vez = jogador1;
        Jogador adversario = jogador2;

        while (!tabuleiroCompleto(jogador1) && !tabuleiroCompleto(jogador2)) {
            System.out.println("\n--- Turno de " + vez.getNome() + " ---");
            jogador1.mostrarTabuleiroBonito();
            jogador2.mostrarTabuleiroBonito();

            // 3. Rola e joga
            int valor = vez.rolarDado();
            System.out.println(vez.getNome() + " rolou: " + valor);

            int coluna;
            while (true) {
                System.out.print("Escolha a coluna (0, 1 ou 2): ");
                if (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Entrada inválida! Digite 0, 1 ou 2.");
                    continue;
                }
                coluna = scanner.nextInt();
                scanner.nextLine(); // limpa o \n
                if (vez.adicionarDado(coluna, valor, adversario)) {
                    break;
                }
                
                // se falhar, repete até escolher coluna válida
           
            }

            // Alterna jogadores
            // estava dando erro na função
            Jogador temp = vez;
            vez = adversario;
            adversario = temp; 
        }

        // Fim de jogo
        System.out.println("\n=== Fim de Jogo ===");
        jogador1.mostrarTabuleiroBonito();
        jogador2.mostrarTabuleiroBonito();

        int pontos1 = jogador1.calcularPontuacao();
        int pontos2 = jogador2.calcularPontuacao();

        System.out.println(jogador1.getNome() + ": " + pontos1 + " pontos");
        System.out.println(jogador2.getNome() + ": " + pontos2 + " pontos");

        if (pontos1 > pontos2) {
            System.out.println(jogador1.getNome() + " venceu!");
        } else if (pontos2 > pontos1) {
            System.out.println(jogador2.getNome() + " venceu!");
        } else {
            System.out.println("Empate!");
        }
    }   


    // Retorna true se o jogador tiver 3 dados em cada uma das 3 colunas
    private boolean tabuleiroCompleto(Jogador jogador) {
        for (int i = 0; i < 3; i++) {
            if (jogador.getColunas()[i].size() < 3) {
                return false;
            }
        }
        return true;
    }
    
    
    /*public void alternarJogadores(Jogador vez, Jogador adversario){
        Jogador temp = vez;
        vez = adversario;
        adversario = temp;
    }*/
}