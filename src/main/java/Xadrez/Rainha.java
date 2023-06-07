package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Rainha extends PecaDeXadrez{
    
    public Rainha(Cor cor, Board board) {
        super(cor, board);
    }
    @Override
    public String toString(){
        return "Q";
    }

    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] matriz = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
        
        Posicao p = new Posicao(0,0);
        
        //cima
        p.setValor(position.getLinha()-1,position.getColuna());
        while((getBoard().posicaoExistente(p) && !getBoard().temUmaPeca(p))){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha()-1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //esquerda
        p.setValor(position.getLinha(),position.getColuna()-1);
        while(getBoard().posicaoExistente(p)&& !getBoard().temUmaPeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna()-1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        //direita
        p.setValor(position.getLinha(),position.getColuna()+1);
        while(getBoard().posicaoExistente(p)&& !getBoard().temUmaPeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna()+1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
        
        //baixo
        p.setValor(position.getLinha()+1,position.getColuna());
        while(getBoard().posicaoExistente(p)&& !getBoard().temUmaPeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha()+1);
        }
        if(getBoard().posicaoExistente(p)&& eUmaPecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()]=true;
        }
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
