package Xadrez;

import BoardGame.Board;
import BoardGame.Peca;
import BoardGame.Posicao;

public abstract class PecaDeXadrez extends Peca{
    private Cor cor;
    private int contadorMovimento;
    
    public PecaDeXadrez(Cor cor, Board board) {
        super(board);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
    public int getContadorDeMovimento(){
        return this.contadorMovimento;
    }
    public void incrementoMovimento(){
        this.contadorMovimento++;
    }public void decrementoMovimento(){
        this.contadorMovimento--;
    }
    
    public XadrezPosicao getXadrezPosicao(){
        return XadrezPosicao.ParaPosicao(position);
    }
    protected boolean eUmaPecaOponente(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getBoard().pecas(posicao);
        return p!=null && p.getCor()!=cor;
    }
}
