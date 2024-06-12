import java.util.Scanner;

public class Battleship {
    private static int[][] tab1 = new int[10][10];
    private static int[][] tab2 = new int[10][10];
    private static String nomeJogador1, nomeJogador2;
    private static String[][] tab_Batalha1 = new String[10][10];
    private static String[][] tab_Batalha2 = new String[10][10];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        iniciarJogo();
    }

    public static void iniciarJogo() {
        posicionarPecas();
        iniciarBatalha();
    }

    public static void posicionarPecas() {
        int embarcacao = 5;
        boolean checagem;
        
        nomeJogador();
        limpa();
        criarTabela();

        for (int i = 1; i < 3; i++) {
            while (embarcacao > 0) {
                String nomePeca = "";
                switch (embarcacao) {
                    case 5: nomePeca = "do seu Porta-Aviões (5 casas): "; break;
                    case 4: nomePeca = "do seu Contratorpedeiro (4 casas): "; break;
                    case 3: nomePeca = "do seu Fragata (3 casas): "; break;
                    case 2: nomePeca = "do seu Submarino (2 casas): "; break;
                    case 1: nomePeca = "do seu Rebocador (1 casa): "; break;
                }
                System.out.println(layoutJogo(i));
                int disp = disposicao(nomePeca);
                int colunaPeca = coluna(nomePeca);
                int linhaPeca = linha(nomePeca);
                limpa();

                checagem = checagemTab(disp, linhaPeca, colunaPeca, embarcacao, i);

                if (checagem) {
                    posicionar(disp, linhaPeca, colunaPeca, embarcacao, i);
                    embarcacao--;
                } else {
                    System.out.println("Não foi possível posicionar o barco!");
                }
            }
            embarcacao = 5;
        }
    }

    public static int disposicao(String embarcacao) {
        int disp;
        do {
            System.out.print("Qual posição " + embarcacao + " (1 = Para baixo, 2 = Para a direita): ");
            disp = scanner.nextInt();
        } while (disp != 1 && disp != 2);
        return disp;
    }

    public static void nomeJogador() {
        System.out.println("Sejam Bem-Vindos(as) ao jogo de Batalha Naval!");
        System.out.print("\nInforme o nome do 1° jogador: ");
        nomeJogador1 = scanner.next();
        System.out.print("\nInforme o nome do 2° jogador: ");
        nomeJogador2 = scanner.next();
    }

    public static int coluna(String embarcacao) {
        char colunaLetra;
        int posicao = 0;
        do {
            System.out.print("Informe qual a coluna " + embarcacao);
            colunaLetra = scanner.next().charAt(0);
        } while (colunaLetra < 'A' || colunaLetra > 'J' && colunaLetra < 'a' || colunaLetra > 'j');

        switch (Character.toUpperCase(colunaLetra)) {
            case 'A': posicao = 0; break;
            case 'B': posicao = 1; break;
            case 'C': posicao = 2; break;
            case 'D': posicao = 3; break;
            case 'E': posicao = 4; break;
            case 'F': posicao = 5; break;
            case 'G': posicao = 6; break;
            case 'H': posicao = 7; break;
            case 'I': posicao = 8; break;
            case 'J': posicao = 9; break;
        }
        return posicao;
    }

    public static int linha(String embarcacao) {
        int linhaPeca;
        do {
            System.out.print("Informe qual a linha " + embarcacao);
            linhaPeca = scanner.nextInt();
        } while (linhaPeca < 1 || linhaPeca > 10);
        return linhaPeca - 1;
    }

    public static void criarTabela() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tab1[i][j] = 0;
                tab2[i][j] = 0;
                tab_Batalha1[i][j] = "O";
                tab_Batalha2[i][j] = "O";
            }
        }
    }

    public static boolean checagemTab(int disp, int linhas, int colunas, int embarcacao, int jogador) {
        boolean validar = true;
        int[] comp = new int[5];
        for (int i = 0; i < embarcacao; i++) comp[i] = 1;

        if (disp == 1) {
            int[] pos = new int[5];
            for (int i = 0; i < embarcacao; i++) pos[i] = linhas + i;

            for (int i = 0; i < embarcacao; i++) {
                if (pos[i] > 9) validar = false;
                if (jogador == 1 && tab1[pos[i]][colunas] == 1) validar = false;
                if (jogador == 2 && tab2[pos[i]][colunas] == 1) validar = false;
            }
        } else {
            int[] pos = new int[5];
            for (int i = 0; i < embarcacao; i++) pos[i] = colunas + i;

            for (int i = 0; i < embarcacao; i++) {
                if (pos[i] > 9) validar = false;
                if (jogador == 1 && tab1[linhas][pos[i]] == 1) validar = false;
                if (jogador == 2 && tab2[linhas][pos[i]] == 1) validar = false;
            }
        }
        return validar;
    }

    public static void posicionar(int disp, int linhas, int colunas, int embarcacao, int jogador) {
        int[] comp = new int[5];
        for (int i = 0; i < embarcacao; i++) comp[i] = 1;

        if (disp == 1) {
            for (int i = 0; i < embarcacao; i++) {
                if (jogador == 1) {
                    tab1[linhas + i][colunas] = 1;
                } else {
                    tab2[linhas + i][colunas] = 1;
                }
            }
        } else {
            for (int i = 0; i < embarcacao; i++) {
                if (jogador == 1) {
                    tab1[linhas][colunas + i] = 1;
                } else {
                    tab2[linhas][colunas + i] = 1;
                }
            }
        }
    }

    public static void iniciarBatalha() {
        // Implementação da lógica da batalha
    }

    public static String layoutJogo(int jogador) {
        StringBuilder layout = new StringBuilder();
        layout.append("Jogador ").append(jogador).append("\n");
        layout.append("  A B C D E F G H I J\n");
        String[][] tab = (jogador == 1) ? tab_Batalha1 : tab_Batalha2;
        for (int i = 0; i < 10; i++) {
            layout.append(i + 1).append(" ");
            for (int j = 0; j < 10; j++) {
                layout.append(tab[i][j]).append(" ");
            }
            layout.append("\n");
        }
        return layout.toString();
    }

    public static void limpa() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
