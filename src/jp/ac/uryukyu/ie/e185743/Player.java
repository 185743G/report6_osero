package jp.ac.uryukyu.ie.e185743;

public class Player {

    public char put(int[] pos) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Board.getStonelist(pos[0], pos[1]).getColor(Board.getStonelist(pos[0], pos[1])) == 'E') {
                }
            }
        }
        return '●';//仮置き
    }
}

