package jp.ac.uryukyu.ie.e185743;

public class Stone {
    private char color;//"●"=White, "○"=Black, "E"=Empty
    private int x_pos;
    private int y_pos;
    Stone(char color, int x,int y){
        this.color = color;
        this.x_pos = x;
        this.y_pos = y;
    }
    static char getColor(Stone s){
        return s.color;
    }
////    int[] getPosition(Stone s){
////        return [s.x_pos, s.y_pos];
////    }
//    public void setColor(Stone s, char col){
//        s.color = col;
//    }
//
}