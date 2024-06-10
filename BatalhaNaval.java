import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {

    public BatalhaNaval() {
        int TAMANHO_TABULEIRO = 8;
        int NUMERO_NAVIOS = 10;
        int MAX_TENTATIVAS = 30;
    
        int tentativas = 0;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String[][] tabuleiro = new String[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

        inicializarTabuleiro(TAMANHO_TABULEIRO, tabuleiro);
        posicionarNavios(NUMERO_NAVIOS, TAMANHO_TABULEIRO, random, tabuleiro);
        jogar(tentativas, MAX_TENTATIVAS, TAMANHO_TABULEIRO, scanner, tabuleiro);
    }

    public void inicializarTabuleiro(int TAMANHO_TABULEIRO, String[][] tabuleiro) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = "~"; // água
            }
        }
    }

    public void posicionarNavios(int NUMERO_NAVIOS, int TAMANHO_TABULEIRO, Random random, String[][] tabuleiro) {
        for (int i = 0; i < NUMERO_NAVIOS; i++) {
            int linha, coluna;
            do {
                linha = random.nextInt(TAMANHO_TABULEIRO);
                coluna = random.nextInt(TAMANHO_TABULEIRO);
            } while (tabuleiro[linha][coluna].equals("N"));

            tabuleiro[linha][coluna] = "N"; // navio
        }
    }

    public void jogar(int tentativas, int MAX_TENTATIVAS, int TAMANHO_TABULEIRO, Scanner scanner, String[][] tabuleiro) {
        while (tentativas < MAX_TENTATIVAS) {
            System.out.println("\nTentativa: " + (tentativas + 1));
            mostrarTabuleiro(TAMANHO_TABULEIRO, tabuleiro);

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
                if (todosNaviosDestruidos(TAMANHO_TABULEIRO, tabuleiro)) {
                    System.out.println("\nParabéns! Você destruiu todos os navios!");
                    mostrarTabuleiro(TAMANHO_TABULEIRO, tabuleiro);
                    return;
                }
            } else {
                System.out.println("Você errou!");
                tabuleiro[linha][coluna] = "O"; // erro
            }

            if (tentativas == MAX_TENTATIVAS) {
                System.out.println("\nFim de jogo! Você não conseguiu destruir todos os navios.");
                mostrarTabuleiro(TAMANHO_TABULEIRO, tabuleiro);
                return;
            }
        }
    }

    public boolean todosNaviosDestruidos(int TAMANHO_TABULEIRO, String[][] tabuleiro) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j].equals("N")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void mostrarTabuleiro(int TAMANHO_TABULEIRO, String[][] tabuleiro) {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
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
