package jp.ac.uryukyu.ie.e185743;

public class Stone {
    private char color;//"●"=White, "○"=Black, "E"=Empty
    Stone(char color){
        this.color = color;
    }
    static char getColor(Stone s){
        return s.color;
    }
}