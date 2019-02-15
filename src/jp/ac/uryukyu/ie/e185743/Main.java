package jp.ac.uryukyu.ie.e185743;
import java.util.Scanner;


/**
 * Created by tnal on 2016/11/13.
 */
public class Main {
    public static void main(String[] args){
        int SIDE = 9;//一片の長さは9
        Stone stones[][] = new Stone[SIDE][SIDE];
        Board board = new Board(SIDE, SIDE, stones);
        board.initialize();
        board.open();
        Player player1 = new Player();

        int turn = 0;
        Scanner in = new Scanner(System.in);
        int[] imput={0,0};
        while(Board.isOver() == false) {
            turn++;
            System.out.printf("%dターン目開始！\n", turn);
            System.out.println("置きたい箇所のx座標を入力してください");
            imput[0] = in.nextInt();
            System.out.println("置きたい箇所のy座標を入力してください");
            imput[1] = in.nextInt();
            stones[imput[0]][imput[1]] = new Stone(player1.put(imput), 'j', 'i');
            board.open();
        }
    }
}