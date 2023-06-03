package Xadrez;

import BoardGame.Board;

public class Rei extends PecaDeXadrez{
    
    public Rei(Cor cor, Board board) {
        super(cor, board);
    }
    @Override
    public String toString(){
        return "R";
    }

    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] matriz = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
        return matriz;
    }
}
