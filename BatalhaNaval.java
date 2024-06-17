import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {

    public BatalhaNaval() {
        //Cria as variaveis para o tamanho do tabuleiro
        int tamanhoTabuleiro = 8; //tamanho 8x8
        int qtdNavios = 10; //quantidade de navios que serão colocados
        int maximoTentativas = 30; //Quantidade de tentativas que o jogador pode fazer
    
        int tentativas = 0; //Tentativas já realizadas pelo jogador 
        Random sorteador = new Random(); //Chama a classe sorteador 
        Scanner scanner = new Scanner(System.in); //Chama a classe Scanner 
        String[][] tabuleiro = new String[tamanhoTabuleiro][tamanhoTabuleiro]; //Cria uma matriz de referência(String) e define tamanho de acordo com tamanhoTabuleiro, no caso 8
        int[][] posicaoNavios = new int[tamanhoTabuleiro][tamanhoTabuleiro]; //Cria uma matriz do tipo primitivo(Inteiro) e define tamanho 8x8
        //Foram criadas duas matrizes, A de refência será exibida para o usuario enquanto o jogo estará rodando 
        //Enquanto a outra será para armazenar os barcos colocados nas posições e exibir onde estavam após o termino do jogo

        //Chama o método passando paramêtros nescessários
        inicializarTabuleiro(tamanhoTabuleiro, tabuleiro); 
        inicializarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);
        posicionarNavios(qtdNavios, tamanhoTabuleiro, sorteador, posicaoNavios, tabuleiro);
        jogar(tentativas, maximoTentativas, tamanhoTabuleiro, scanner, tabuleiro, posicaoNavios);
    }

    //Método para inicializar o tabuleiro de String, colocando o simbolo de água em todas as posições
    public void inicializarTabuleiro(int tamanhoTabuleiro, String[][] tabuleiro) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = "~"; // água
            }
        }
    }
    //Método para inicializar o tabuleiro de inteiros, colocando 0 em todas as posições para representar que não há navio 
    public void inicializarPosicaoNavios(int tamanhoTabuleiro, int[][] posicaoNavios) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                posicaoNavios[i][j] = 0; // sem navio
            }
        }
    }
    //Método para posicionar navios
    public void posicionarNavios(int qtdNavios, int tamanhoTabuleiro, Random sorteador, int[][] posicaoNavios, String[][] tabuleiro) {
        int linha, coluna; 
        for (int i = 0; i < qtdNavios; i++) { //Para i igual a zero, i menor que quantidade de navios (10), i recebe um 
            do {
                linha = sorteador.nextInt(tamanhoTabuleiro); //Chama a classe sorteador para escolher qual posição de linha (entre 0 e 7)
                coluna = sorteador.nextInt(tamanhoTabuleiro); //Chama a classe sorteador para escolher qual posição de coluna (entre 0 e 7)
            } while (posicaoNavios[linha][coluna] == 1);

            posicaoNavios[linha][coluna] = 1; // navio
            tabuleiro[linha][coluna] = "N"; // navio
        }
    }

    public void jogar(int tentativas, int maximoTentativas, int tamanhoTabuleiro, Scanner scanner, String[][] tabuleiro, int[][] posicaoNavios) {
        while (tentativas < maximoTentativas) {
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
                    mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);
                    return;
                }
            } else {
                System.out.println("Você errou!");
                tabuleiro[linha][coluna] = "O"; // erro
            }

            if (tentativas == maximoTentativas) {
                System.out.println("\nFim de jogo! Você não conseguiu destruir todos os navios.");
                mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);
                mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);
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

    public void mostrarPosicaoNavios(int tamanhoTabuleiro, int[][] posicaoNavios) {
        System.out.println("\nPosição dos Navios:");
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                System.out.print(posicaoNavios[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new BatalhaNaval();
    }
}
