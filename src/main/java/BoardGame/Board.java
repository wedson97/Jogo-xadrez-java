
package BoardGame;

public class Board {
    private int linhas, colunas;
    private Peca[][] pecas;

    public Board(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public Peca[][] getPecas() {
        return pecas;
    }

    public void setPecas(Peca[][] pecas) {
        this.pecas = pecas;
    }
    public Peca pecas(int linha, int coluna){
        return pecas[linha][coluna];
    }
    public Peca pecas(Posicao posicao){
        return pecas[posicao.getLinha()][posicao.getColuna()];   
    }
    
}
