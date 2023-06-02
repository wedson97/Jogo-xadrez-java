package Xadrez;

import BoardGame.Board;

public class Torre extends PecaDeXadrez{
    
    public Torre(Cor cor, Board board) {
        super(cor, board);
    }
    @Override
    public String toString(){
        return "T";
    }
}
