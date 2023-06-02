package Xadrez;

import BoardGame.Board;
import BoardGame.Posicao;

public class PartidaDeXadrez {
    private Board taboleiro;
    
    public PartidaDeXadrez(){
        this.taboleiro=new Board(8,8);
        initialSetup();
    }
    public PecaDeXadrez[][] getPecas(){
        PecaDeXadrez[][] matriz = new PecaDeXadrez[taboleiro.getLinhas()][taboleiro.getColunas()];
        for(int i=0;i<taboleiro.getLinhas();i++){
            for(int j=0; j<taboleiro.getColunas();j++){
                matriz[i][j]=(PecaDeXadrez) taboleiro.pecas(i, j);
            }
        }
        return matriz;
    }
    private void initialSetup(){
        taboleiro.colocarPeca(new Rei(Cor.BRANCO,taboleiro), new Posicao(2,1));
        taboleiro.colocarPeca(new Torre(Cor.BRANCO,taboleiro), new Posicao(0,4));
    }
}
