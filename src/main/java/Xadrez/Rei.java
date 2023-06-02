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
}
