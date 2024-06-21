import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class BatalhaNavalEntregue {
    public BatalhaNavalEntregue() {
        int tamanhoTabuleiro = 8;
        int qtdNavios = 10;
        int maximoTentativas = 30;

        int tentativas = 0;
        Random sorteador = new Random();
        Scanner scanner = new Scanner(System.in);
        String[][] tabuleiro = new String[tamanhoTabuleiro][tamanhoTabuleiro];
        int[][] posicaoNavios = new int[tamanhoTabuleiro][tamanhoTabuleiro];

        inicializarTabuleiro(tamanhoTabuleiro, tabuleiro);
        inicializarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);
        posicionarNavios(qtdNavios, tamanhoTabuleiro, sorteador, posicaoNavios, tabuleiro);
        jogar(tentativas, maximoTentativas, tamanhoTabuleiro, scanner, tabuleiro, posicaoNavios);
    }

    // Método sem retorno(void). Insere o simbolo ~ no tabuleiro visual para simbolizar a água. Passa como parametro o tamanho do tabuleiro e uma matriz visual
    public void inicializarTabuleiro(int tamanhoTabuleiro, String[][] tabuleiro) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = "~"; 
            }
        }
    }

    // Método sem retorno(void) para inicializar o tabuleiro de inteiros, colocando 0 em todas as posições para representar que não há navio. Passa como parametro tamanho do tabuleiro e uma matriz Integer
    public void inicializarPosicaoNavios(int tamanhoTabuleiro, int[][] posicaoNavios) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                posicaoNavios[i][j] = 0; 
            }
        }
    }

    // Método sem retorno(void) para posicionar navios. Recebe a quantidade de navios, classe Random, a matriz de numeros e visual.
    public void posicionarNavios(int qtdNavios, int tamanhoTabuleiro, Random sorteador, int[][] posicaoNavios,
            String[][] tabuleiro) {
        int linha, coluna;
        for (int i = 0; i < qtdNavios; i++) {
            do {
                linha = sorteador.nextInt(tamanhoTabuleiro);
                coluna = sorteador.nextInt(tamanhoTabuleiro);
            } while (posicaoNavios[linha][coluna] == 1);
            posicaoNavios[linha][coluna] = 1;
            tabuleiro[linha][coluna] = "N";
        }
    }

    // Método sem retorno(void), passa como parametro quantidade de tentativas e o maximo
    // delas, o tamanho do tabuleiro, classe Scanner, Uma matriz visual para o
    // jogador e uma de números.
    // Dentro dele acontece todo o jogo e ele chama métodos tambem, como de mostrar
    // o tabuleiro, mostrar os navios
    public void jogar(int tentativas, int maximoTentativas, int tamanhoTabuleiro, Scanner scanner, String[][] tabuleiro,
            int[][] posicaoNavios) {
        boolean jogoAtivo = true;

        while (tentativas < maximoTentativas && jogoAtivo) {
            System.out.println("\nTentativa: " + (tentativas + 1));
            mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);
            int linha = 0;
            int coluna = 0;
            boolean entradaValida;

            do {
                System.out.print("Insira a linha (0-7): ");
                entradaValida = true;
                try {
                    linha = scanner.nextInt();
                    if (linha < 0 || linha > 7) {
                        System.out.println("Por favor, insira uma linha válida");
                        entradaValida = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, insira um número inteiro.");
                    entradaValida = false;
                }
                scanner.nextLine();
            } while (!entradaValida);

            do {
                System.out.print("Insira a coluna (0-7): ");
                entradaValida = true;
                try {
                    coluna = scanner.nextInt();
                    if (coluna < 0 || coluna > 7) {
                        System.out.println("Por favor, insira uma coluna válida");
                        entradaValida = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, insira um número inteiro.");

                    entradaValida = false;
                }
                scanner.nextLine();
            } while (!entradaValida);

            if (tabuleiro[linha][coluna].equals("X") || tabuleiro[linha][coluna].equals("O")) {
                System.out.println("Você já jogou nessa posição! Tente novamente.");
            } else {
                tentativas++;

                if (tabuleiro[linha][coluna].equals("N")) {
                    System.out.println("Você acertou um navio!");
                    tabuleiro[linha][coluna] = "X";
                    if (todosNaviosDestruidos(tamanhoTabuleiro, tabuleiro)) {
                        System.out.println("\nParabéns! Você destruiu todos os navios!");
                        mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);
                        mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);
                        jogoAtivo = false;
                    }
                } else {
                    System.out.println("\nVocê errou!");
                    tabuleiro[linha][coluna] = "O";
                }

                if (tentativas == maximoTentativas) {
                    System.out.println("\nFim de jogo! Você não conseguiu destruir todos os navios.");
                    mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);
                    mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);
                    jogoAtivo = false;
                }
            }
        }
    }

    // Método com retorno booleano. Que passa como parametro o tamanho do tabuleiro e uma matriz visual.
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

    // Método sem retorno(void), apenas mostra o tabuleiro e recebe o tabuleiro e o seu
    // tamanho como parametro
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

    // Método sem retorno(void) exibido após o termino de todas as jogadas, vitória ou
    // derrota do jogado.
    // Passa uma variavel int para ser o tamamho do tabuleiro
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
        new BatalhaNavalEntregue();
    }
}