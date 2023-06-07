package Xadrez;

import BoardGame.Board;
import BoardGame.Peca;
import BoardGame.Posicao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaDeXadrez {
    private int turno;
    private Cor jogadorDaVez;
    private Board taboleiro;
    private boolean check;
    private boolean checkMate;
    
    private List<Peca> pecaNoTaboleiro = new ArrayList<>();
    private List<Peca> pecaCapturada = new ArrayList<>();
    
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
    public boolean getCheck(){
        return this.check;
    }
    public boolean getChekMate(){
        return this.checkMate;
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
        if(testeCheck(jogadorDaVez)){
            desfazerMovimento(source,target,capturaPeca);
            throw new XadrezException("Se colocando em check");
        }
        check = (testeCheck(oponente(jogadorDaVez)))? true:false;
        if(testeCheck(oponente(jogadorDaVez))){
            checkMate = true;
        }else{
           proximoTurno(); 
        }
        
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
        PecaDeXadrez p = (PecaDeXadrez)taboleiro.removePeca(source);
        p.incrementoMovimento();
        Peca capturada = taboleiro.removePeca(target);
        if(capturada!=null){
            pecaNoTaboleiro.remove(capturada);
            pecaCapturada.add(capturada);
        }
        taboleiro.colocarPeca(p,target);
        return capturada;
    }
    private void desfazerMovimento(Posicao source, Posicao target, Peca capturada){
        PecaDeXadrez p = (PecaDeXadrez)taboleiro.removePeca(target);
        p.decrementoMovimento();
        taboleiro.colocarPeca(p, source);
        if(capturada!=null){
            taboleiro.colocarPeca(capturada, target);
            pecaCapturada.remove(capturada);
            pecaNoTaboleiro.add(capturada);
        }
    }
        
    private void proximoTurno(){
        turno++;
        jogadorDaVez = (jogadorDaVez == Cor.BRANCO ) ? Cor.PRETO:Cor.BRANCO;
    }
    private Cor oponente(Cor cor){
        return (cor == Cor.BRANCO)? Cor.PRETO : Cor.BRANCO;
    }
    private PecaDeXadrez rei(Cor cor){
        List<Peca> lista = pecaNoTaboleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor()==cor).collect(Collectors.toList());
        for(Peca p:lista){
            if(p instanceof Rei){
                return (PecaDeXadrez)p;
            }
        }
        throw new IllegalStateException("Nao existe a cor "+ cor + "no taboleiro");
    }
    private boolean testeCheck(Cor cor){
        Posicao posicaoRei = rei(cor).getXadrezPosicao().toPosicao();
        List<Peca> pecaOponente = pecaNoTaboleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor()==oponente(cor)).collect(Collectors.toList());
        for(Peca p: pecaOponente){
            boolean[][] matriz =  p.possivelMovimentos();
            if(matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]){
                return true;
            }
        }
        return false;
    }
    private boolean testeCheckMate(Cor cor){
        if(!testeCheck(cor)){
            return false;
        }
        List<Peca> pecas = pecaNoTaboleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor()==cor).collect(Collectors.toList());
        for(Peca p: pecas){
            boolean[][] matriz =  p.possivelMovimentos();
            for(int i=0;i<taboleiro.getLinhas();i++){
                for(int j=0;j<taboleiro.getColunas();j++){
                    if(matriz[i][j]){
                        Posicao source = ((PecaDeXadrez)p).getXadrezPosicao().toPosicao();
                        Posicao target =  new Posicao(i,j);
                        Peca capturada =  fazerMovimento(source,target);
                        boolean testeCheck =  testeCheck(cor);
                        desfazerMovimento(source,target,capturada);
                        if(!testeCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }    
    private void colocarNovaPeca(char coluna, int linha, PecaDeXadrez peca){
        taboleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
        pecaNoTaboleiro.add(peca);
    }
    private void initialSetup(){
        colocarNovaPeca('a', 1, new Torre(Cor.BRANCO,taboleiro));
        //colocarNovaPeca('b', 1, new Knight(Cor.BRANCO,taboleiro));
        //colocarNovaPeca('c', 1, new Bishop(Cor.BRANCO,taboleiro));
        //colocarNovaPeca('d', 1, new Queen(Cor.BRANCO,taboleiro));
        colocarNovaPeca('e', 1, new Rei(Cor.BRANCO,taboleiro));
        //colocarNovaPeca('f', 1, new Bishop(Cor.BRANCO,taboleiro));
        //colocarNovaPeca('g', 1, new Knight(Cor.BRANCO,taboleiro));
        colocarNovaPeca('h', 1, new Torre(Cor.BRANCO,taboleiro));
        colocarNovaPeca('a', 2, new Peao(Cor.BRANCO,taboleiro));
        colocarNovaPeca('b', 2, new Peao(Cor.BRANCO,taboleiro));
        colocarNovaPeca('c', 2, new Peao(Cor.BRANCO,taboleiro));
        colocarNovaPeca('d', 2, new Peao(Cor.BRANCO,taboleiro));
        colocarNovaPeca('e', 2, new Peao(Cor.BRANCO,taboleiro));
        colocarNovaPeca('f', 2, new Peao(Cor.BRANCO,taboleiro));
        colocarNovaPeca('g', 2, new Peao(Cor.BRANCO,taboleiro));
        colocarNovaPeca('h', 2, new Peao(Cor.BRANCO,taboleiro));

        colocarNovaPeca('a', 8, new Torre(Cor.PRETO,taboleiro));
       //colocarNovaPeca('b', 8, new Knight(Cor.PRETO,taboleiro));
       // colocarNovaPeca('c', 8, new Bishop(Cor.PRETO,taboleiro));
       // colocarNovaPeca('d', 8, new Queen(Cor.PRETO,taboleiro));
        colocarNovaPeca('e', 8, new Rei(Cor.PRETO,taboleiro));
        //colocarNovaPeca('f', 8, new Bishop(Cor.PRETO,taboleiro));
       // colocarNovaPeca('g', 8, new Knight(Cor.PRETO,taboleiro));
        colocarNovaPeca('h', 8, new Torre(Cor.PRETO,taboleiro));
        colocarNovaPeca('a', 7, new Peao(Cor.PRETO,taboleiro));
        colocarNovaPeca('b', 7, new Peao(Cor.PRETO,taboleiro));
        colocarNovaPeca('c', 7, new Peao(Cor.PRETO,taboleiro));
        colocarNovaPeca('d', 7, new Peao(Cor.PRETO,taboleiro));
        colocarNovaPeca('e', 7, new Peao(Cor.PRETO,taboleiro));
        colocarNovaPeca('f', 7, new Peao(Cor.PRETO,taboleiro));
        colocarNovaPeca('g', 7, new Peao(Cor.PRETO,taboleiro));
        colocarNovaPeca('h', 7, new Peao(Cor.PRETO,taboleiro));
    }
}
