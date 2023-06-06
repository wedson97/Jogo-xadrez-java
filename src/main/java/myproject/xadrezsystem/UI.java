package myproject.xadrezsystem;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.XadrezPosicao;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    public static void LimparTela(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    public static XadrezPosicao lerPosicao(Scanner c){
        try{
          String s = c.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new XadrezPosicao(coluna,linha);  
        }
        catch(RuntimeException e){
            throw new InputMismatchException("Erro de input");
        }
    }
    public static void printPartida(PartidaDeXadrez partida){
        printBoard(partida.getPecas());
        System.out.println();
        System.out.println("Turno: "+partida.getTurno());
        System.out.println("Esperando o jogador: "+partida.getJogadorDaVez());
        
    }
    public static void printBoard(PecaDeXadrez[][] pecas){
        for(int i =0;i<pecas.length;i++){
            System.out.print((8 - i)+" ");
            for(int j =0;j<pecas.length;j++){
                printPeca(pecas[i][j],false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    public static void printBoard(PecaDeXadrez[][] pecas, boolean[][] possiveis){
        for(int i =0;i<pecas.length;i++){
            System.out.print((8 - i)+" ");
            for(int j =0;j<pecas.length;j++){
                printPeca(pecas[i][j],possiveis[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    private static void printPeca(PecaDeXadrez peca, boolean back){
        if(back){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if(peca == null){
            System.out.print("-"+ ANSI_RESET);
        }else{
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
    
}
