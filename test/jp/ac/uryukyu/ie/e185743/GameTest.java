package jp.ac.uryukyu.ie.e185743;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    static int exL = 6;//4*4*4のオセロ盤
    int[] input = {1,3,2};//
    int[] dir1 = {1, 0, 0};//１マス右
    @Test
    public void update(){
        /**
         *  inputにより{2,3,2}の黒石は白石に裏返るかどうかを見ます
         */
        Board board = new Board(exL, exL, exL);
        board.initialize(exL);
        //input + dir = {2,3,2} ここのcellは黒の予定
        //input + dir*2 = {3,3,2} ここのcellは白の予定

        assertEquals(board.chk_Cell_Ahead(input, dir1), Main.BLACK);//{2,3,2}が黒ならおっけい
        if (board.chk_Cell_Ahead(input, dir1) == Main.BLACK){
            System.out.println("{2,3,2} is BLACK");
        }
        Game.update(input);//input に白石を置きます

        assertEquals(board.chk_Cell_Ahead(input, dir1), Main.WHITE);//{2,3,2}が裏返ったらおっけい
        if (board.chk_Cell_Ahead(input, dir1) == Main.WHITE){
            System.out.println("{2,3,2} changed WHITE");
        }
    }
}

//import org.junit.jupiter.api.Test;
//
//        import static org.junit.jupiter.api.Assertions.*;
//
//public class EnemyTest {
//    /**
//     * 「死んでる状態では攻撃できない」ことを確認。
//     */
//    @Test
//    public void attack() {
//        int heroHP = 10;
//        Hero hero = new Hero("テスト勇者", heroHP, 5);
//        Enemy enemy = new Enemy("テストスライム", 6, 3);
//        enemy.setDead(true);
//        for (int i = 0; i < 10; i++) {
//            enemy.attack(hero); //乱数で0ダメージとなることもあるため、複数回実行してみる。
//        }
//        assertEquals(heroHP, hero.getHitPoint());
//    }
//}
//
