package bugalha;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo(scanner); 

        boolean continuar = true;

        while (continuar) {
            jogo.executarPartida(); 

            System.out.print("Deseja jogar novamente? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (!resposta.equals("s")) {
                continuar = false;
                System.out.println("Jogo encerrado. Obrigado por jogar!");
            }
        }

        scanner.close();
    }
}
