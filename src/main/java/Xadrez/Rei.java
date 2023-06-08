package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class Rei extends PecaDeXadrez{
    
    private PartidaDeXadrez partida;
    
    public Rei(Cor cor, Board board,PartidaDeXadrez partida) {
        super(cor, board);
        this.partida=partida;
    }
    @Override
    public String toString(){
        return "R";
    }

    public boolean podeMover(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getBoard().pecas(posicao);
        return p==null || p.getCor()!= getCor();
    }
    private boolean testRoque(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getBoard().pecas(posicao);
        return p!= null && p instanceof Torre && p.getCor() == getCor() && p.getContadorDeMovimento()==0;
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
        //movimento especial
        if(getContadorDeMovimento()==0 && !partida.getCheck()){
            Posicao torre1 = new Posicao(position.getLinha(),position.getColuna()+3);
            if(testRoque(torre1)){
                Posicao p1 = new Posicao(position.getLinha(),position.getColuna()+1);
                Posicao p2 = new Posicao(position.getLinha(),position.getColuna()+2);
                if(getBoard().pecas(p1)==null && getBoard().pecas(p2)==null){
                    matriz[position.getLinha()][position.getColuna()+2]=true;
                }
            }
            Posicao torre2 = new Posicao(position.getLinha(),position.getColuna()-4);
            if(testRoque(torre2)){
                Posicao p1 = new Posicao(position.getLinha(),position.getColuna()-1);
                Posicao p2 = new Posicao(position.getLinha(),position.getColuna()-2);
                Posicao p3 = new Posicao(position.getLinha(),position.getColuna()-3);
                if(getBoard().pecas(p1)==null && getBoard().pecas(p2)==null && getBoard().pecas(p3)==null){
                    matriz[position.getLinha()][position.getColuna()-2]=true;
                }
            }
        }
        
        return matriz;
    }
}
