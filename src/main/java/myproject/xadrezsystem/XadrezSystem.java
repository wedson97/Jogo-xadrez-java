package myproject.xadrezsystem;


import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.XadrezException;
import Xadrez.XadrezPosicao;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class XadrezSystem {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();
		while (!partida.getChekMate()) {
                    try{
                        UI.LimparTela();
                        UI.printPartida(partida,capturada);
                        System.out.println();
                        System.out.print("Source: ");
                        XadrezPosicao source = UI.lerPosicao(sc);
                        
                        boolean[][] possiveiMovimentos = partida.movimentoPossiveis(source);
                        UI.LimparTela();
                        UI.printBoard(partida.getPecas(),possiveiMovimentos);
                        
                        System.out.println();
                        System.out.print("Target: ");
                        XadrezPosicao target = UI.lerPosicao(sc);

                        PecaDeXadrez capturedPiece = partida.Movimentacao(source, target);  
                        if(capturedPiece!=null){
                            capturada.add(capturedPiece);
                        }
                        if(partida.getPromoted()!=null){
                            System.out.println("Escolha uma peca (R/B/C/Q)");
                            String tipo = sc.nextLine();
                            partida.replacePromoted(tipo);
                        }
                    }catch(XadrezException e){
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }catch(InputMismatchException e){
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }
                    
		}
        UI.LimparTela();
        UI.printPartida(partida, capturada);
    }
}
