package jp.ac.uryukyu.ie.e185743;


public class Board {
    private static Stone[][] stonelist = new Stone[9][9];
    private char[] chars ={'1', '2', '3', '4', '5', '6' ,'7', '8'};
    private int x;
    private int y;
    private static boolean over=false;

    public static Stone getStone(int x,int y){
        return stonelist[x][y];
    }

    public static boolean isOver() {
        return over;
    }

    public static void setStone(int[] pos ,char col) {
        stonelist[pos[0]][pos[1]] = new Stone(col);
    }

    public Board(int x, int y, Stone stones[][]){
        this.x = x;
        this.y = y;
        for(int i=1; i<this.y; i++) {
            for (int j = 1; j < this.x; j++) {
                Stone stone = new Stone('E');//盤上を空の石で埋めます
                stones[j][i] = stone;
                stones[j][0] = new Stone(chars[j-1]);//座標のガイドを表示
            }
            stones[0][i] = new Stone(chars[i-1]);//座標のガイドを表示
        }
        stones[0][0] = new Stone('/');
        stones[4][4] = new Stone('○');//初期配置されたコマ
        stones[4][5] = new Stone('●');
        stones[5][4] = new Stone('●');
        stones[5][5] = new Stone('○');
        this.stonelist =stones;
    }


    public void open(){
        int num_W=0;
        int num_B=0;
        int num_E=0;

        for(int i=0; i<this.y; i++){
            for (int j = 0; j < this.x; j++){
                System.out.print(' ');//見やすさのためのスペース
                System.out.print(Stone.getColor(stonelist[i][j]));
                if(stonelist[i][j].getColor(stonelist[i][j]) =='●'){
                    num_W +=1;
                }
                if(stonelist[i][j].getColor(stonelist[i][j]) =='○'){
                    num_B +=1;
                }
            }
            System.out.println();
        }
        if(num_E == 0){
            over = true;
        }
        System.out.printf("白の個数は%d個\n", num_W);
        System.out.printf("黒の個数は%d個\n", num_B);
    }
}

