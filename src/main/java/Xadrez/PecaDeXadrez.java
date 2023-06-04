package Xadrez;

import BoardGame.Board;
import BoardGame.Peca;
import BoardGame.Posicao;

public abstract class PecaDeXadrez extends Peca{
    private Cor cor;

    public PecaDeXadrez(Cor cor, Board board) {
        super(board);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
    protected boolean eUmaPecaOponente(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getBoard().pecas(posicao);
        return p!=null && p.getCor()!=cor;
    }
}
