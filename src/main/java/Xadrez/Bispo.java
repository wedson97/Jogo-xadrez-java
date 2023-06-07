package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Bispo extends PecaDeXadrez{
    public Bispo(Cor cor,Board taboleiro){
        super(cor, taboleiro);
    }
    public String toString(){
        return "B";
    }
    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] matriz = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
        
        Posicao p = new Posicao(0,0);
        
        //nw
        p.setValor(position.getLinha()-1,position.getColuna()-1);
        while((getBoard().posicaoExistente(p) && !getBoard().temUmaPeca(p))){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha()-1,p.getColuna()-1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //nordeste
        p.setValor(position.getLinha()-1,position.getColuna()+1);
        while(getBoard().posicaoExistente(p)&& !getBoard().temUmaPeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha()-1,p.getColuna()+1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //sudeste
        p.setValor(position.getLinha()+1,position.getColuna()+1);
        while(getBoard().posicaoExistente(p)&& !getBoard().temUmaPeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha()+1,p.getColuna()+1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        //sw
        p.setValor(position.getLinha()+1,position.getColuna()-1);
        while(getBoard().posicaoExistente(p)&& !getBoard().temUmaPeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValor(p.getLinha()+1,p.getColuna()-1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        return matriz;
    }
}
