package Xadrez;

import BoardGame.Board;
import BoardGame.Peca;

public abstract class PecaDeXadrez extends Peca{
    private Cor cor;

    public PecaDeXadrez(Cor cor, Board board) {
        super(board);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
    
}
