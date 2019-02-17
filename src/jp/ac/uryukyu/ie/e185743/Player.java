package jp.ac.uryukyu.ie.e185743;
import java.lang.Math;

public class Player {
    char color;//"●"=White, "○"=Black, "E"=Empty
    Stone caught_enemy[] = new Stone[18];

    Stone round_enemy[] = new Stone[6];
    public Player(char color){
        this.color = color;
    }
    int[] vector = new int[4];//調べるマスの向きを決定するパラメーター
    boolean judge;

    public Stone[] is_round_enemy(int[] pos1, Player enemy) {
        if (pos1[0] == 1) { vector[0] = 0; } else { vector[0] = -1; }//置いたマスが左端ならそれより左を調べない

        if (pos1[0] == 1 || pos1[0] == 8) { vector[1] = 1; } else { vector[1] = 2; }//置いたマスが右端ならそれより右を調べない

        if (pos1[1] == 1) { vector[2] = 0; } else { vector[2] = -1; }//置いたマスが上端ならそれより上を調べない

        if (pos1[1] == 1 || pos1[1] == 8) { vector[3] = 1; } else { vector[3] = 2; }//置いたマスが下端ならそれより下を調べない

        for (; vector[0] < vector[1]; vector[0]++) {
            for (; vector[2] < vector[3]; vector[2]++) {
                judge = is_enemy(pos1, enemy);
                if (judge) {
                    asf(pos1, enemy);
                }
            }
        }
        return caught_enemy;
    }
    private boolean is_enemy(int[] pos, Player enemy) {//入力されたマスから+(a, c)移動したマスは敵かどうか調べる
        pos[0] = pos[0]+vector[0];
        pos[1] = pos[1]+vector[2];
        if (Board.getStone(pos[0], pos[1]).getColor(Board.getStone(pos[0], pos[1])) == enemy.color) {
            return true;
        } else {
            return false;
        }
    }
    private boolean is_ally(int[] pos) {//入力されたマスから+(a, c)移動したマスは味方かどうか調べる
        pos[0] = pos[0]+vector[0];
        pos[1] = pos[1]+vector[2];
        if (Board.getStone(pos[0], pos[1]).getColor(Board.getStone(pos[0], pos[1])) == color) {
            return true;
        } else {
            return false;
        }
    }
    public boolean is_room(int[] pos){
        if (Board.getStone(pos[0], pos[1]).getColor(Board.getStone(pos[0], pos[1])) == 'E') {
            return true;
        }else {
            return false;
        }
    }
    private boolean top_or_bottom(int[] pos) {
        if (Math.abs(pos[0] - 4) == 4) {
            return false;//画面端まで調べたらfalseに
        } else {
            return true;
        }
    }
    private boolean right_or_left(int[] pos) {
        if (Math.abs(pos[1] - 4) == 4) {
            return false;//画面端まで調べたらfalseに
        } else {
            return true;
        }
    }
    private boolean inside(int[] pos) {
        if (Math.abs(pos[0] - 4) == 4 || Math.abs(pos[1] - 4) == 4) {
            return false;//画面端まで調べたらfalseに
        } else {
            return true;
        }
    }
    public boolean is_pincer(int[] pos, Player enemy){
        if (is_enemy(pos, enemy)) {//敵ならその先を調べる用意をする
             pos[0] = pos[0] + vector[0];
             pos[1] = pos[1] + vector[2];
             return false;
        }else if(is_ally(pos)){
            return true;
        }else{//'E'ならばスルー　この先を調べなくて良い
            judge = false;
            return false;
        }
    }
    public void catch_enemy(int[] pos, Player enemy){
        if (is_enemy(pos, enemy)) {//+(a, c)移動したマスは敵なら味方にする
            pos[0] = pos[0] + vector[0];
            pos[1] = pos[1] + vector[2];
            Board.setStone(pos ,color);
        }else{//味方が現れたら終了
            judge = false;
        }
    }
    public void asf(int[] pos, Player enemy){
        if (vector[0] == 0) {
            while(right_or_left(pos) && judge){
                if (is_pincer(pos, enemy)){
                    catch_enemy(pos, enemy);
                    break;
                }
            }
        }else if (vector[2] == 0){
            while(top_or_bottom(pos) && judge){
                if (is_pincer(pos, enemy)){
                    catch_enemy(pos, enemy);
                    break;
                }
            }
        }else {
            while(inside(pos) && judge){
                if (is_pincer(pos, enemy)){
                    catch_enemy(pos, enemy);
                    break;
                }
            }
        }

    }
    public char put_stone(){
        return color;
    }
}