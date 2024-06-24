import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class BatalhaNavalEntregue {
    public BatalhaNavalEntregue() {
        int tamanhoTabuleiro = 8;
        int qtdNavios = 10;
        int maximoTentativas = 30;
        int op=0;
        int tentativas = 0;
        Random sorteador = new Random();
        Scanner scanner = new Scanner(System.in);
        String[][] tabuleiro = new String[tamanhoTabuleiro][tamanhoTabuleiro];
        char[][] posicaoNavios = new char[tamanhoTabuleiro][tamanhoTabuleiro];
    
        do{
            System.out.println("Menu \n1-Iniciar jogo \n2-Regras do jogo \n3-Sair do jogo");
             op=scanner.nextInt();
            switch (op) {
                case 1:
                inicializarTabuleiro(tamanhoTabuleiro, tabuleiro);
                inicializarPosicaoNavios(tamanhoTabuleiro, posicaoNavios);
                posicionarNavios(qtdNavios, tamanhoTabuleiro, sorteador, posicaoNavios, tabuleiro);
                jogar(tentativas, maximoTentativas, tamanhoTabuleiro, scanner, tabuleiro, posicaoNavios);
                break;

                case 2:
                exibirRegras();
                break;

                case 3:
                op=3;
                System.out.println("Até a próxima...");
                break;

                default: 
                System.out.println("Escolha inválida");
                break;
                
            }
        }while(op!=3);

    }
    // Método sem retorno para exibir as regras do jogo. Não recebe nenhum parâmetro.
    public void exibirRegras(){
        System.out.println("Regras: ");
        System.out.println("No tabuleiro existe 10 embarações e o jogador deve acertar todos para vencer");
        System.out.println("O jogador possui 30 tentativas para acertar os navios");
        System.out.println("O Jogador deve inserir a linha e a coluna entre 0 a 7 para acertar um navio");
        System.out.println("Se o jogador acertar um navio, é marcado um (X) no local, Caso contrário, será marcado um (O) no local. \nO jogador não poderá atacar novamente no mesmo local");
    }

    // Método sem retorno(void). Insere o simbolo ~ no tabuleiro visual para simbolizar a água.
    //É passado int tamanhoTabuleiro para servir de tamanho que o tabuleiro possui. no caso 8x8,para ser usado no (for)
    //Uma Matriz String tabuleiro, para ter seus seus indices preenchidos com (~) para representar água 
    public void inicializarTabuleiro(int tamanhoTabuleiro, String[][] tabuleiro) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = "~";
            }
        }
    }

    // Método sem retorno(void), usado para preencher os indices com simbolo (~) no tabuleiro visual para simbolizar a água.
    // Passa como parametro tamanho do tabuleiro e uma matriz para armazenar a posição dos navios.
    // Utiliza int tamanhoTabuleiro para servir de tamanho que o tabuleiro possui, para ser usado no (for)
    // Utiliza Matriz char posicaoNavios, para ter seus seus indices preenchidos com (~) para representar água 
    public void inicializarPosicaoNavios(int tamanhoTabuleiro, char[][] posicaoNavios) {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                posicaoNavios[i][j] = '~';
            }
        }
    }

    // Método sem retorno(void), usado para posicionar navios no tabuleiro que apenas o jogo consegue ver.
    //  int qtdNavios para se usado no loop for para comparar quantos navios foram postos.
    //  int tamanhoTabuleiro para ser sorteado um número entre 0 e 7(Limite do tabuleiro)
    //  Random sorteador, uma classe que vai sortear o número de acordo com o padrão, no caso 0 a 7
    //  Matriz char posicaoNavio, é usado para colocar a letra(N) e representar o navio 
    public void posicionarNavios(int qtdNavios, int tamanhoTabuleiro, Random sorteador, char[][] posicaoNavios,
            String[][] tabuleiro) {
        int linha, coluna;
        for (int i = 0; i < qtdNavios; i++) {
            do {
                linha = sorteador.nextInt(tamanhoTabuleiro);
                coluna = sorteador.nextInt(tamanhoTabuleiro);
            } while (posicaoNavios[linha][coluna] == 'N');
            posicaoNavios[linha][coluna] = 'N';
        }
    }

    // Método sem retorno(void), passa como parametro quantidade de tentativas e o maximo
    // delas, o tamanho do tabuleiro, classe Scanner, Uma matriz visual para o jogador e uma para posição dos navios.
    // Dentro dele acontece todo o jogo e ele chama métodos tambem, como de mostrar o tabuleiro para o jogador.
    public void jogar(int tentativas, int maximoTentativas, int tamanhoTabuleiro, Scanner scanner, String[][] tabuleiro,
            char[][] posicaoNavios) {
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

                if (posicaoNavios[linha][coluna] == 'N') {
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

    // Método com retorno booleano. Nele é feita a verificação de quantos navios destruidos (X) aparece no tabuleiro
    // Utiliza int tamanhoTabuleiro para servir de tamanho que o tabuleiro possui, para ser usado no (for)
    // Matriz String tabuleiro para olhar cada indice se possui algum (X). 
    public boolean todosNaviosDestruidos(int tamanhoTabuleiro, String[][] tabuleiro) {
        int contador = 0;
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j].equals("X")) {
                    contador++;
                }
            }
        }
        if(contador==10){
            return true;
        }
        else{
            return false;
        }
      
    }

    // Método sem retorno(void), mostra o tabuleiro ao jogador, indicando a posições de navios destruidos e tiros na água.
    // Utiliza int tamanhoTabuleiro para servir de tamanho que o tabuleiro possui, para ser usado no (for)
    // Utiliza Matriz String tabuleiro para mostrar o que possui no indice. Seja mar, X ou O. 
    public void mostrarTabuleiro(int tamanhoTabuleiro, String[][] tabuleiro) {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j].equals("~")) {

                    System.out.print("~ ");
                } else {

                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Método sem retorno(void). Nele mostra a posição de todos os barcos assim que o jogo acaba.
    // Passa como parâmetro o tamanho do tabuleiro e uma matriz que contêm a posição dos navios.
    // Utiliza int tamanhoTabuleiro para servir de tamanho que o tabuleiro possui, para ser usado no (for)
    // Matriz char posição navio para exibir ao jogador aonde estão os navios(N). 
    public void mostrarPosicaoNavios(int tamanhoTabuleiro, char[][] posicaoNavios) {
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