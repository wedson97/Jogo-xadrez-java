package Xadrez;

import BoardGame.Board;
import BoardGame.Peca;
import BoardGame.Posicao;

public class PartidaDeXadrez {
    private int turno;
    private Cor jogadorDaVez;
    private Board taboleiro;
    
    public PartidaDeXadrez(){
        this.taboleiro=new Board(8,8);
        this.turno=1;
        this.jogadorDaVez=Cor.BRANCO;
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
    
    public int getTurno(){
        return this.turno;
    }
    public Cor getJogadorDaVez(){
        return this.jogadorDaVez;
    }
    public boolean[][] movimentoPossiveis(XadrezPosicao source){
        Posicao posicao = source.toPosicao();
        ValidateSourcePosition(posicao);
        return taboleiro.pecas(posicao).possivelMovimentos();
    }
    
    public PecaDeXadrez Movimentacao(XadrezPosicao sourceposition, XadrezPosicao targetposition){
        Posicao source = sourceposition.toPosicao();
        Posicao target = targetposition.toPosicao();
        ValidateSourcePosition(source);
        validateTargetPosition(source, target);
        Peca capturaPeca = fazerMovimento(source,target);
        proximoTurno();
        return (PecaDeXadrez) capturaPeca;
    }
    private void  ValidateSourcePosition(Posicao posicao){
        if(!taboleiro.temUmaPeca(posicao)){
            throw new XadrezException("Tem um peca ne posicao validate");
        }
        if(jogadorDaVez != ((PecaDeXadrez)taboleiro.pecas(posicao)).getCor()){
            throw new XadrezException("Essa peca nao e sua");
        }
        if(!taboleiro.pecas(posicao).temAlgumMovimento()){
            throw new XadrezException("Nao foi possivel mover e");
        }
    }
    private void validateTargetPosition(Posicao source, Posicao target){
        if(!taboleiro.pecas(source).possivelMovimento(target)){
            throw new XadrezException("A peca nao pode mover para esse destino");
        }
    }
    private Peca fazerMovimento(Posicao source, Posicao target){
        Peca p = taboleiro.removePeca(source);
        Peca capturada = taboleiro.removePeca(target);
        taboleiro.colocarPeca(p,target);
        return capturada;
    }
    private void proximoTurno(){
        turno++;
        jogadorDaVez = (jogadorDaVez == Cor.BRANCO ) ? Cor.PRETO:Cor.BRANCO;
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
