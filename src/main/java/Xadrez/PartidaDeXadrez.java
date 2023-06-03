package Xadrez;

import BoardGame.Board;
import BoardGame.Peca;
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
    public PecaDeXadrez Movimentacao(XadrezPosicao sourceposition, XadrezPosicao targetposition){
        Posicao source = sourceposition.toPosicao();
        Posicao target = targetposition.toPosicao();
        ValidateSourcePosition(source);
        Peca capturaPeca = fazerMovimento(source,target);
        return (PecaDeXadrez) capturaPeca;
    }
    private void  ValidateSourcePosition(Posicao posicao){
        if(!taboleiro.temUmaPeca(posicao)){
            throw new XadrezException("Tem um peca ne posicao validate");
        }
        if(!taboleiro.pecas(posicao).temAlgumMovimento()){
            throw new XadrezException("Nao foi possivel mover");
        }
    }
    private Peca fazerMovimento(Posicao source, Posicao target){
        Peca p = taboleiro.removePeca(source);
        Peca capturada = taboleiro.removePeca(target);
        taboleiro.colocarPeca(p,target);
        return capturada;
    }
    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca){
        taboleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
    }
    private void initialSetup(){
        colocarNovaPeca('c', 1, new Torre(Cor.BRANCO, taboleiro));
        colocarNovaPeca('c', 2, new Torre(Cor.BRANCO, taboleiro));
        colocarNovaPeca('d', 2, new Torre(Cor.BRANCO, taboleiro));
        colocarNovaPeca('e', 2, new Torre(Cor.BRANCO, taboleiro));
        colocarNovaPeca('e', 1, new Torre(Cor.BRANCO, taboleiro));
        colocarNovaPeca('d', 1, new Rei(Cor.BRANCO, taboleiro));

        colocarNovaPeca('c', 7, new Torre(Cor.PRETO, taboleiro));
        colocarNovaPeca('c', 8, new Torre(Cor.PRETO, taboleiro));
        colocarNovaPeca('d', 7, new Torre(Cor.PRETO, taboleiro));
        colocarNovaPeca('e', 7, new Torre(Cor.PRETO, taboleiro));
        colocarNovaPeca('e', 8, new Torre(Cor.PRETO, taboleiro));
        colocarNovaPeca('d', 8, new Rei(Cor.PRETO, taboleiro));
    }
}
