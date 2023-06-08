package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Peao extends PecaDeXadrez{
    private PartidaDeXadrez partida;

    public Peao(Cor cor, Board board,PartidaDeXadrez partida) {
        super(cor, board);
        this.partida=partida;
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
           //enpassant
           if(position.getLinha()==3){
               Posicao left = new Posicao(position.getLinha(),position.getColuna()-1);
               if(getBoard().posicaoExistente(left) && eUmaPecaOponente(left) && getBoard().pecas(left)==partida.getEnPassant()){
                   matriz[left.getLinha()-1][left.getColuna()]=true;
               }
               Posicao right = new Posicao(position.getLinha(),position.getColuna()+1);
               if(getBoard().posicaoExistente(right) && eUmaPecaOponente(right) && getBoard().pecas(right)==partida.getEnPassant()){
                   matriz[right.getLinha()-1][right.getColuna()]=true;
               }
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
           //enpassant
           if(position.getLinha()==4){
               Posicao left = new Posicao(position.getLinha(),position.getColuna()-1);
               if(getBoard().posicaoExistente(left) && eUmaPecaOponente(left) && getBoard().pecas(left)==partida.getEnPassant()){
                   matriz[left.getLinha()+1][left.getColuna()]=true;
               }
               Posicao right = new Posicao(position.getLinha(),position.getColuna()+1);
               if(getBoard().posicaoExistente(right) && eUmaPecaOponente(right) && getBoard().pecas(left)==partida.getEnPassant()){
                   matriz[right.getLinha()+1][right.getColuna()]=true;
               }
           }
       }       
       return matriz;
    }
    public String toString(){
        return "P";
    }
}
