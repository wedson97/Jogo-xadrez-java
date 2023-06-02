package BoardGame;

public class Peca {
    protected Posicao position;
    private Board board;

    public Peca(Board board) {
        this.board = board;
    }

   protected Board getBoard() {
        return board;
    }
    
}
