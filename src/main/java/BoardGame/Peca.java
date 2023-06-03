package BoardGame;

public abstract class Peca {
    protected Posicao position;
    private Board board;

    public Peca(Board board) {
        this.board = board;
        position=null;
    }

   protected Board getBoard() {
        return board;
    }
    public abstract boolean[][] possivelMovimentos();
    public boolean possivelMovimento(Posicao posicao){
        return possivelMovimentos()[posicao.getLinha()][posicao.getColuna()];
    }
    public boolean temAlgumMovimento(){
        boolean[][] matriz = possivelMovimentos();
        for(int i = 0 ; i<matriz.length;i++){
            for (int j=0;j<matriz.length;j++){
                if (matriz[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
