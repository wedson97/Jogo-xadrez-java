package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Peao extends PecaDeXadrez{

    public Peao(Cor cor, Board board) {
        super(cor, board);
    }

    @Override
    public boolean[][] possivelMovimentos() {
       boolean[][] matriz = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
       Posicao p = new Posicao(0,0);
       if(getCor() == Cor.BRANCO){
           p.setValor(position.getLinha()-1, position.getColuna());
           if(getBoard().posicaoExistente(p) && !getBoard().temUmaPeca(p)){
               matriz[p.getLinha()][p.getColuna()]=true;
           }
           p.setValor(position.getLinha()-2, position.getColuna());
           Posicao p2 = new Posicao(position.getLinha()-1, position.getColuna());
           if(getBoard().posicaoExistente(p) && !getBoard().temUmaPeca(p)&& getBoard().posicaoExistente(p2) && !getBoard().temUmaPeca(p2) && getContadorDeMovimento() == 0){
               matriz[p.getLinha()][p.getColuna()]=true;
           }
           p.setValor(position.getLinha()-1, position.getColuna()-1);
           if(getBoard().posicaoExistente(p) && eUmaPecaOponente(p)){
               matriz[p.getLinha()][p.getColuna()]=true;
           }
           p.setValor(position.getLinha()-1, position.getColuna()+1);
           if(getBoard().posicaoExistente(p) && eUmaPecaOponente(p)){
               matriz[p.getLinha()][p.getColuna()]=true;
           }
       }else{
          p.setValor(position.getLinha()+1, position.getColuna());
           if(getBoard().posicaoExistente(p) && !getBoard().temUmaPeca(p)){
               matriz[p.getLinha()][p.getColuna()]=true;
           }
           p.setValor(position.getLinha()+2, position.getColuna());
           Posicao p2 = new Posicao(position.getLinha()+1, position.getColuna());
           if(getBoard().posicaoExistente(p) && !getBoard().temUmaPeca(p)&& getBoard().posicaoExistente(p2) && !getBoard().temUmaPeca(p2) && getContadorDeMovimento() == 0){
               matriz[p.getLinha()][p.getColuna()]=true;
           }
           p.setValor(position.getLinha()+1, position.getColuna()-1);
           if(getBoard().posicaoExistente(p) && eUmaPecaOponente(p)){
               matriz[p.getLinha()][p.getColuna()]=true;
           }
           p.setValor(position.getLinha()+1, position.getColuna()+1);
           if(getBoard().posicaoExistente(p) && eUmaPecaOponente(p)){
               matriz[p.getLinha()][p.getColuna()]=true;
           } 
       }       
       return matriz;
    }
    public String toString(){
        return "P";
    }
}
