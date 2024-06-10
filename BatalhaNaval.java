import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {

    public BatalhaNaval() {
        int tamanhoTabuleiro = 8;
        int qtdNavios = 10;
        int maximoTentivas = 30;
    
        int tentativas = 0;
        Random sorteador = new Random();
        Scanner scanner = new Scanner(System.in);
        String[][] tabuleiro = new String[tamanhoTabuleiro][tamanhoTabuleiro];

        inicializarTabuleiro(tamanhoTabuleiro, tabuleiro);
        posicionarNavios(qtdNavios, tamanhoTabuleiro, sorteador, tabuleiro);
        jogar(tentativas, maximoTentivas, tamanhoTabuleiro, scanner, tabuleiro);
    }

    public void inicializarTabuleiro(int tamanhoTabuleiro, String[][] tabuleiro) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = "~"; // água
            }
        }
    }

    public void posicionarNavios(int qtdNavios, int tamanhoTabuleiro, Random sorteador, String[][] tabuleiro) {
        for (int i = 0; i < qtdNavios; i++) {
            int linha, coluna;
            do {
                linha = sorteador.nextInt(tamanhoTabuleiro);
                coluna = sorteador.nextInt(tamanhoTabuleiro);
            } while (tabuleiro[linha][coluna].equals("N"));

            tabuleiro[linha][coluna] = "N"; // navio
        }
    }

    public void jogar(int tentativas, int maximoTentivas, int tamanhoTabuleiro, Scanner scanner, String[][] tabuleiro) {
        while (tentativas < maximoTentivas) {
            System.out.println("\nTentativa: " + (tentativas + 1));
            mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);

            int linha;
            do {
                System.out.print("Insira a linha (0-7): ");
                linha = scanner.nextInt();
            } while (linha < 0 || linha > 7);

            int coluna;
            do {
                System.out.print("Insira a coluna (0-7): ");
                coluna = scanner.nextInt();
            } while (coluna < 0 || coluna > 7);

            if (tabuleiro[linha][coluna].equals("X") || tabuleiro[linha][coluna].equals("O")) {
                System.out.println("Você já jogou nessa posição! Tente novamente.");
                continue;
            }

            tentativas++;

            if (tabuleiro[linha][coluna].equals("N")) {
                System.out.println("Você acertou um navio!");
                tabuleiro[linha][coluna] = "X"; // acerto
                if (todosNaviosDestruidos(tamanhoTabuleiro, tabuleiro)) {
                    System.out.println("\nParabéns! Você destruiu todos os navios!");
                    mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);
                    return;
                }
            } else {
                System.out.println("Você errou!");
                tabuleiro[linha][coluna] = "O"; // erro
            }

            if (tentativas == maximoTentivas) {
                System.out.println("\nFim de jogo! Você não conseguiu destruir todos os navios.");
                mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);
                return;
            }
        }
    }

    public boolean todosNaviosDestruidos(int tamanhoTabuleiro, String[][] tabuleiro) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j].equals("N")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void mostrarTabuleiro(int tamanhoTabuleiro, String[][] tabuleiro) {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j].equals("N") || tabuleiro[i][j].equals("~")) {
                    System.out.print("~ ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new BatalhaNaval();
    }
}
