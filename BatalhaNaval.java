import java.util.Scanner;
import java.util.InputMismatchException;
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
                posicaoNavios[i][j] = 0;
            }
        }
    }
 // Método sem retorno(void) para posicionar navios. Recebe a quantidade de
    // navios, classe Random, a matriz de posição e visual para jogador.
    public void posicionarNavios(int qtdNavios, int tamanhoTabuleiro, Random sorteador, int[][] posicaoNavios,
            String[][] tabuleiro) {
        int linha, coluna;
        for (int i = 0; i < qtdNavios; i++) {
            do {
                linha = sorteador.nextInt(tamanhoTabuleiro);
                coluna = sorteador.nextInt(tamanhoTabuleiro);
            } while (posicaoNavios[linha][coluna] == 1);
            posicaoNavios[linha][coluna] = 1;
        }
    }

    //Método para jogar, passa como parametro quantidade de tentativas e o maximo delas, o tamanho do tabuleiro, leitor, Uma matriz de tabuleiro em String e uma matriz do mapeamento dos navios em Integer   
public void jogar(int tentativas, int maximoTentativas, int tamanhoTabuleiro, Scanner scanner, String[][] tabuleiro, int[][] posicaoNavios) {
    boolean jogoAtivo = true;

    while (tentativas < maximoTentativas && jogoAtivo) { //Enquanto tentativa atual for menor que o máximo(30), ele executa
        System.out.println("\nTentativa: " + (tentativas + 1));//Exibe o número da tentativa atual
        mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);//Método para exibir tabuleiro 
        int linha=0;
        int coluna=0;
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

        if (tabuleiro[linha][coluna].equals("X") || tabuleiro[linha][coluna].equals("O")) { //Verifica se o jogador já atacou essa posição
            System.out.println("Você já jogou nessa posição! Tente novamente.");
        } else {
            tentativas++;

            if (posicaoNavios[linha][coluna] == 1) { // Verifica se o jogador acerta o navio
                System.out.println("Você acertou um navio!");
                tabuleiro[linha][coluna] = "X"; // acerto
                if (todosNaviosDestruidos(tamanhoTabuleiro, tabuleiro)) { //Passa como parametro o tamanho do tabuleiro e o tabuleiro em forma de matriz para o jogador visualizar
                    System.out.println("\nParabéns! Você destruiu todos os navios!");
                    mostrarTabuleiro(tamanhoTabuleiro, tabuleiro); //Chama o método para mostrar tabuleiro visual 
                    mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);  //Chama o método para mostrar tabuleiro de números
                    jogoAtivo = false; // Indica que o jogo terminou
                }
            } else {
                System.out.println("\nVocê errou!");
                tabuleiro[linha][coluna] = "O"; 
            }

            if (tentativas == maximoTentativas) { 
                System.out.println("\nFim de jogo! Você não conseguiu destruir todos os navios."); 
                mostrarTabuleiro(tamanhoTabuleiro, tabuleiro); 
                mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios); 
                jogoAtivo = false; // Indica que o jogo terminou
            }
        }
    }
}
    // Método com retorno booleano. Que passa como parametro o tamanho do tabuleiro e uma matriz visual. Nele é verificado se todos os navios foram destruidos.
    public boolean todosNaviosDestruidos(int tamanhoTabuleiro, String[][] tabuleiro) {
        int contador = 0;
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j].equals("X")) {
                    contador++;
                }
            }
        }
        return contador == 10;
    }

 //Passa o tamanho do tabuleiro e uma Matriz visual(String) como parametros 
    public void mostrarTabuleiro(int tamanhoTabuleiro, String[][] tabuleiro) { 
        System.out.println("\n  0 1 2 3 4 5 6 7"); // imprime o cabeçalho das colunas, indicando os índices das colunas de 0 a 7.
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            System.out.print(i + " "); // imprime o índice da linha seguido de um espaço, para indicar a linha atual do tabuleiro.
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j].equals("N") || tabuleiro[i][j].equals("~")) {  
                    //Se o conteúdo for "N" (indicando um navio) ou "~" (indicando água), imprime "~ " para representar a água.
                    System.out.print("~ ");
                } else {
                    //Caso contrário, imprime o conteúdo atual da posição [i][j] seguido de um espaço. 
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
 //Método exibido após o termino de todas as jogadas, vitória ou derrota do jogado.
 //Passa uma variavel int para ser o tamamho do tabuleiro 
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