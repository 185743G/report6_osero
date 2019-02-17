package jp.ac.uryukyu.ie.e185743;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int SIDE = 9;//一片の長さは9
        Stone stones[][] = new Stone[SIDE][SIDE];
        Board board = new Board(SIDE, SIDE, stones);
        board.open();
        White white = new White('●');
        Black black = new Black('○');
        int turn = 1;
        Player[] players = {black, white};
        Scanner in = new Scanner(System.in);
        int[] imput={0,0};
        while(Board.isOver()) {
            System.out.printf("%dターン目開始！%cのターン\n", turn, players[turn%2].color);
            System.out.println("置きたい箇所のx座標を入力してください");
            imput[0] = in.nextInt();
            System.out.println("置きたい箇所のy座標を入力してください");
            imput[1] = in.nextInt();
            if(players[turn%2].is_room(imput)){
                board.setStone(imput, players[turn%2].color);
                players[turn%2].is_round_enemy(imput, players[(turn+1)%2]);
            }
            board.open();
            turn++;
        }
    }
}