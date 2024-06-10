import java.util.Random;

public class Jogo {
    public Jogo() {
        String TabuleiroVisual[][] = new String[8][8];
        int TabuleiroNumeros[][] = new int[8][8];

        for (int linha = 0; linha < TabuleiroVisual.length; linha++) {
            for (int coluna = 0; coluna < TabuleiroVisual.length; coluna++) {
                TabuleiroVisual[linha][coluna] = "~";
            }
        }
        System.out.print("  ");
        for (int coluna = 0; coluna < TabuleiroVisual[0].length; coluna++) {
            System.out.print(coluna + " ");
        }
        System.out.println("");

        for (int linha = 0; linha < TabuleiroVisual.length; linha++) {
            // Exibir número da linha
            System.out.print(linha + " ");
            for (int coluna = 0; coluna < TabuleiroVisual[linha].length; coluna++) {
                System.out.print(TabuleiroVisual[linha][coluna] + " ");
            }
            System.out.println("");
        }
        //Método para inserir 
        /*
                Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int linha, coluna;
            do {
                linha = random.nextInt(8);
                coluna = random.nextInt(8);
            } while (TabuleiroVisual[linha][coluna].equals("S")); // Verifica se a posição já está ocupada

            TabuleiroVisual[linha][coluna] = "S";
        }
        */
    }

    public static void main(String[] args) {
        new Jogo();

    }
}
