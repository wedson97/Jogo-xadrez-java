package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Torre extends PecaDeXadrez{
    
    public Torre(Cor cor, Board board) {
        super(cor, board);
    }
    @Override
    public String toString(){
        return "T";
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
        return matriz;
    }
    
}
