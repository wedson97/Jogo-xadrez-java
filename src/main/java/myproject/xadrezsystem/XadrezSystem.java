package myproject.xadrezsystem;


import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.XadrezException;
import Xadrez.XadrezPosicao;
import java.util.InputMismatchException;
import java.util.Scanner;

public class XadrezSystem {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while (true) {
                    try{
                        UI.LimparTela();
                        UI.printBoard(partida.getPecas());
                        System.out.println();
                        System.out.print("Source: ");
                        XadrezPosicao source = UI.lerPosicao(sc);

                        System.out.println();
                        System.out.print("Target: ");
                        XadrezPosicao target = UI.lerPosicao(sc);

                        PecaDeXadrez capturedPiece = partida.Movimentacao(source, target);  
                    }catch(XadrezException e){
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }catch(InputMismatchException e){
                        System.out.println(e.getMessage());
                        sc.nextLine();
                    }
                    
		}
        
    }
}
