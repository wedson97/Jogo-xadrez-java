
package BoardGame;

public class Board {
    private int linhas, colunas;
    private Peca[][] pecas;

    public Board(int linhas, int colunas) {
        if(linhas<1 || colunas<1){
            throw new BoardException("Erro ao criar o taboleiro, ele deve conter pelo menos uma linha e uma coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        this.pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca[][] getPecas() {
        return pecas;
    }

    public Peca pecas(int linha, int coluna){
        if(!posicaoExistente(linha, coluna)){
            throw new BoardException("Posicao invalida!");
        }
        return pecas[linha][coluna];
    }
    public Peca pecas(Posicao posicao){
        if(!posicaoExistente(posicao)){
            throw new BoardException("Posicao invalida!");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];   
    }
    public void colocarPeca(Peca peca, Posicao posicao){
        if(temUmaPeca(posicao)){
            throw new BoardException("Tem uma peca nesse lugar");
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.position = posicao;
    }
    public boolean posicaoExistente(int linha, int coluna){
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }
    public boolean posicaoExistente(Posicao posicao){
        return posicaoExistente(posicao.getLinha(),posicao.getColuna());
    }
    public boolean temUmaPeca(Posicao posicao){
        if(!posicaoExistente(posicao)){
            throw new BoardException("Posicao invalida!");
        }
       return pecas(posicao)!=null;
    }
}
