import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {

    int TAMANHO_TABULEIRO = 8;
    int NUMERO_NAVIOS = 10;
    int MAX_TENTATIVAS = 30;

     char[][] tabuleiro;
     int tentativas;
     Random random;
     Scanner scanner;

    public BatalhaNaval() {
        tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        tentativas = 0;
        random = new Random();
        scanner = new Scanner(System.in);

        inicializarTabuleiro();
        posicionarNavios();
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = '~'; // água
            }
        }
    }

    public void posicionarNavios() {
        for (int i = 0; i < NUMERO_NAVIOS; i++) {
            int linha, coluna;
            do {
                linha = random.nextInt(TAMANHO_TABULEIRO);
                coluna = random.nextInt(TAMANHO_TABULEIRO);
            } while (tabuleiro[linha][coluna] == 'N');

            tabuleiro[linha][coluna] = 'N'; // navio
        }
    }

    public void jogar() {
        while (tentativas < MAX_TENTATIVAS) {
            System.out.println("\nTentativa: " + (tentativas + 1));
            mostrarTabuleiro();

            System.out.print("Insira a linha (0-7): ");
            int linha = scanner.nextInt();
            System.out.print("Insira a coluna (0-7): ");
            int coluna = scanner.nextInt();

            if (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO) {
                System.out.println("Posição inválida! Tente novamente.");
                continue;
            }

            if (tabuleiro[linha][coluna] == 'X' || tabuleiro[linha][coluna] == 'O') {
                System.out.println("Você já jogou nessa posição! Tente novamente.");
                continue;
            }

            tentativas++;

            if (tabuleiro[linha][coluna] == 'N') {
                System.out.println("Você acertou um navio!");
                tabuleiro[linha][coluna] = 'X'; // acerto
                if (todosNaviosDestruidos()) {
                    System.out.println("\nParabéns! Você destruiu todos os navios!");
                    mostrarTabuleiro();
                    return;
                }
            } else {
                System.out.println("Você errou!");
                tabuleiro[linha][coluna] = 'O'; // erro
            }

            if (tentativas == MAX_TENTATIVAS) {
                System.out.println("\nFim de jogo! Você não conseguiu destruir todos os navios.");
                mostrarTabuleiro();
                return;
            }
        }
    }

    public boolean todosNaviosDestruidos() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j] == 'N') {
                    return false;
                }
            }
        }
        return true;
    }

    public void mostrarTabuleiro() {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j] == 'N' || tabuleiro[i][j] == '~') {
                    System.out.print("~ ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BatalhaNaval jogo = new BatalhaNaval();
        jogo.jogar();
    }
}