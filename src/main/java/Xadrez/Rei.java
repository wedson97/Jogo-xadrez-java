package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Rei extends PecaDeXadrez{
    
    public Rei(Cor cor, Board board) {
        super(cor, board);
    }
    @Override
    public String toString(){
        return "R";
    }

    public boolean podeMover(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getBoard().pecas(posicao);
        return p==null || p.getCor()!= getCor();
    }
    
    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] matriz = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
        Posicao p = new Posicao(0,0);
        //cima
        p.setValor(position.getLinha()-1, position.getColuna());
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //baixo
        p.setValor(position.getLinha()+1, position.getColuna());
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //esquerda
        p.setValor(position.getLinha(), position.getColuna()-1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //direita
        p.setValor(position.getLinha(), position.getColuna()+1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //nw
        p.setValor(position.getLinha()-1, position.getColuna()-1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //nordeste
        p.setValor(position.getLinha()-1, position.getColuna()+1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //sulOeste
        p.setValor(position.getLinha()+1, position.getColuna()-1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //sulDeste
        p.setValor(position.getLinha()+1, position.getColuna()+1);
        if(getBoard().posicaoExistente(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        return matriz;
    }
}
