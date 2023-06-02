package myproject.xadrezsystem;

import BoardGame.Board;
import BoardGame.Posicao;
import Xadrez.PartidaDeXadrez;

public class XadrezSystem {

    public static void main(String[] args) {
        PartidaDeXadrez partida = new PartidaDeXadrez();
        UI.printBoard(partida.getPecas());
    }
}
