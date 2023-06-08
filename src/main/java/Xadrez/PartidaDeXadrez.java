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
    private PecaDeXadrez enpassant;
    
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
    public PecaDeXadrez getEnPassant(){
        return this.enpassant;
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
        PecaDeXadrez movimentoPeca = (PecaDeXadrez)taboleiro.pecas(target);
        
        check = (testeCheck(oponente(jogadorDaVez)))? true:false;
        if(testeCheckMate(oponente(jogadorDaVez))){
            checkMate = true;
        }else{
           proximoTurno(); 
        }
        //enpassant
        if(movimentoPeca instanceof Peao && (target.getLinha() == source.getLinha()-2 || target.getLinha() == source.getLinha()+2)){
            this.enpassant = movimentoPeca;
        }else{
            this.enpassant = null;
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
        //movimento especial
        if(p instanceof Rei && target.getColuna() == source.getColuna()+2){
            Posicao sourceRoque = new Posicao(source.getLinha(),source.getColuna()+3);
            Posicao targetRoque = new Posicao(source.getLinha(),source.getColuna()+1);
            PecaDeXadrez torre = (PecaDeXadrez)taboleiro.removePeca(sourceRoque);
            taboleiro.colocarPeca(torre, targetRoque);
            torre.incrementoMovimento();
        }
        if(p instanceof Rei && target.getColuna() == source.getColuna()-2){
            Posicao sourceRoque = new Posicao(source.getLinha(),source.getColuna()-4);
            Posicao targetRoque = new Posicao(source.getLinha(),source.getColuna()-1);
            PecaDeXadrez torre = (PecaDeXadrez)taboleiro.removePeca(sourceRoque);
            taboleiro.colocarPeca(torre, targetRoque);
            torre.incrementoMovimento();
        }
        //movimento enpassant
        if(p instanceof Peao){
            if(source.getColuna() !=target.getColuna() && capturada == null){
                Posicao posicaoPeao;
                if(p.getCor() == Cor.BRANCO){
                    posicaoPeao = new Posicao(target.getLinha()+1,target.getColuna());
                }else{
                    posicaoPeao = new Posicao(target.getLinha()-1,target.getColuna());
                }
                capturada = taboleiro.removePeca(posicaoPeao);
                pecaCapturada.add(capturada);
                pecaNoTaboleiro.remove(capturada);
            }   
        }
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
        if(p instanceof Rei && target.getColuna() == source.getColuna()+2){
            Posicao sourceRoque = new Posicao(source.getLinha(),source.getColuna()-4);
            Posicao targetRoque = new Posicao(source.getLinha(),source.getColuna()-1);
            PecaDeXadrez torre = (PecaDeXadrez)taboleiro.removePeca(targetRoque);
            taboleiro.colocarPeca(torre, sourceRoque);
            torre.decrementoMovimento();
        }
        if(p instanceof Rei && target.getColuna() == source.getColuna()-2){
            Posicao sourceRoque = new Posicao(source.getLinha(),source.getColuna()-4);
            Posicao targetRoque = new Posicao(source.getLinha(),source.getColuna()-1);
            PecaDeXadrez torre = (PecaDeXadrez)taboleiro.removePeca(targetRoque);
            taboleiro.colocarPeca(torre, sourceRoque);
            torre.decrementoMovimento();
        }
        if(p instanceof Peao){
            if(source.getColuna()!=target.getColuna() && pecaCapturada == enpassant){
                PecaDeXadrez peao = (PecaDeXadrez)taboleiro.removePeca(target);
                Posicao posicaoPeao;
                if(p.getCor() == Cor.BRANCO){
                    posicaoPeao = new Posicao(3,target.getColuna());
                }else{
                    posicaoPeao = new Posicao(4,target.getColuna());
                }
                taboleiro.colocarPeca(peao, posicaoPeao);
               
            }   
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
        colocarNovaPeca('b', 1, new Cavalo(Cor.BRANCO,taboleiro));
        colocarNovaPeca('c', 1, new Bispo(Cor.BRANCO,taboleiro));
        colocarNovaPeca('d', 1, new Rainha(Cor.BRANCO,taboleiro));
        colocarNovaPeca('e', 1, new Rei(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('f', 1, new Bispo(Cor.BRANCO,taboleiro));
        colocarNovaPeca('g', 1, new Cavalo(Cor.BRANCO,taboleiro));
        colocarNovaPeca('h', 1, new Torre(Cor.BRANCO,taboleiro));
        colocarNovaPeca('a', 2, new Peao(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('b', 2, new Peao(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('c', 2, new Peao(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('d', 2, new Peao(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('e', 2, new Peao(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('f', 2, new Peao(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('g', 2, new Peao(Cor.BRANCO,taboleiro,this));
        colocarNovaPeca('h', 2, new Peao(Cor.BRANCO,taboleiro,this));

        colocarNovaPeca('a', 8, new Torre(Cor.PRETO,taboleiro));
        colocarNovaPeca('b', 8, new Cavalo(Cor.PRETO,taboleiro));
        colocarNovaPeca('c', 8, new Bispo(Cor.PRETO,taboleiro));
        colocarNovaPeca('d', 8, new Rainha(Cor.PRETO,taboleiro));
        colocarNovaPeca('e', 8, new Rei(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('f', 8, new Bispo(Cor.PRETO,taboleiro));
        colocarNovaPeca('g', 8, new Cavalo(Cor.PRETO,taboleiro));
        colocarNovaPeca('h', 8, new Torre(Cor.PRETO,taboleiro));
        colocarNovaPeca('a', 7, new Peao(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('b', 7, new Peao(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('c', 7, new Peao(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('d', 7, new Peao(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('e', 7, new Peao(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('f', 7, new Peao(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('g', 7, new Peao(Cor.PRETO,taboleiro,this));
        colocarNovaPeca('h', 7, new Peao(Cor.PRETO,taboleiro,this));
    }
}
