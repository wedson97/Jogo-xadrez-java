package Xadrez;

import BoardGame.Board;

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
    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca){
        taboleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
    }
    private void initialSetup(){
        colocarNovaPeca('b',6,new Rei(Cor.BRANCO,taboleiro));
        colocarNovaPeca('e',1,new Torre(Cor.BRANCO,taboleiro));
    }
}
