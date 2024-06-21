import java.util.Scanner;
import java.util.Random;


public class BatalhaNavalEntregue {


    public BatalhaNavalEntregue() {
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


            posicaoNavios[linha][coluna] = 1; 
            tabuleiro[linha][coluna] = "N"; 
        }
    }
    //Método para jogar, passa como parametro quantidade de tentativas e o maximo delas, o tamanho do tabuleiro, leitor, Uma matriz de tabuleiro em String e uma matriz do mapeamento dos navios em Integer   
    public void jogar(int tentativas, int maximoTentativas, int tamanhoTabuleiro, Scanner scanner, String[][] tabuleiro, int[][] posicaoNavios) {
        while (tentativas < maximoTentativas) { //Enquanto tentativa atual for menor que o máximo(30), ele executa
            System.out.println("\nTentativa: " + (tentativas + 1));//Exibe o número da tentativa atual
            mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);//Método para exibir tabuleiro 
            int linha;


            do {    //Laço para o usuario informar o a linha que deseja atacar 
                System.out.print("Insira a linha (0-7): ");
                linha = scanner.nextInt();
            } while (linha < 0 || linha > 7);


            int coluna;
            do {    //Laço para o usuario informar o a coluna que deseja atacar 
                System.out.print("Insira a coluna (0-7): ");
                coluna = scanner.nextInt();
            } while (coluna < 0 || coluna > 7); 


            if (tabuleiro[linha][coluna].equals("X") || tabuleiro[linha][coluna].equals("O")) { //Enquanto o usuário 
                System.out.println("Você já jogou nessa posição! Tente novamente.");
                continue;
            }


            tentativas++;


            if (tabuleiro[linha][coluna].equals("N")) { // Verifica se o jogador acerta o navio
                System.out.println("Você acertou um navio!");
                tabuleiro[linha][coluna] = "X"; // acerto
                if (todosNaviosDestruidos(tamanhoTabuleiro, tabuleiro)) { //Passa como parametro o tamanho do tabuleiro e o tabuleiro em forma de matriz para o jogador visualizar
                    System.out.println("\nParabéns! Você destruiu todos os navios!");
                    mostrarTabuleiro(tamanhoTabuleiro, tabuleiro); //Chama o método para mostrar tabuleiro visual 
                    mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);  //Chama o método para mostrar tabuleiro de números
                    return; //Sai do método caso o jogador vença
                }
            } else {
                System.out.println("Você errou!");
                tabuleiro[linha][coluna] = "O"; // erro
            }


            if (tentativas == maximoTentativas) { //Caso a quantidade de vezes que o jogador jogou sejá igual ao limite de jogadas, ele perde.
                System.out.println("\nFim de jogo! Você não conseguiu destruir todos os navios."); 
                mostrarTabuleiro(tamanhoTabuleiro, tabuleiro);  //Chama o método para mostrar tabuleiro visual 
                mostrarPosicaoNavios(tamanhoTabuleiro, posicaoNavios); //Chama o método para mostrar tabuleiro de números
                return; // Sai do método quaso o jogador perca
            }
        }
    }


    
    public boolean todosNaviosDestruidos(int tamanhoTabuleiro, String[][] tabuleiro) { //Método que passa como parametro o valor integer do tamanho do tabuleiro e uma matriz visual(String) que é o tabuleiro  
        for (int i = 0; i < tamanhoTabuleiro; i++) { 
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j].equals("N")) { //Se no tabuleiro aparece algum navio ainda presente o jogo continua 
                    return false; //Retorna falso 
                }
            }
        }
        return true; // Retorna verdadeiro caso todos forem destruidos
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
        new BatalhaNavalEntregue();
    }
}