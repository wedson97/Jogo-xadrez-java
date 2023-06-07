package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Cavalo extends PecaDeXadrez{
    
    public Cavalo(Cor cor, Board board) {
        super(cor, board);
    }
    @Override
    public String toString(){
        return "C";
    }

    public boolean podeMover(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getBoard().pecas(posicao);
        return p==null || p.getCor()!= getCor();
    }
    
    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] matriz = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
        Posicao p = new Posicao(0,0);
        
        p.setValor(position.getLinha()-1, position.getColuna()-2);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        p.setValor(position.getLinha()-2, position.getColuna()-1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        p.setValor(position.getLinha()-2, position.getColuna()+1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        p.setValor(position.getLinha()-1, position.getColuna()+2);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        p.setValor(position.getLinha()+1, position.getColuna()+2);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
       
        p.setValor(position.getLinha()+2, position.getColuna()+1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        p.setValor(position.getLinha()+2, position.getColuna()-1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        p.setValor(position.getLinha()+1, position.getColuna()-2);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        return matriz;
    }
}
