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
    }

    public static void main(String[] args) {
        new Jogo();

    }
}
