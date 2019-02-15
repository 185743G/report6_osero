package jp.ac.uryukyu.ie.e185743;


public class Board {
    private static Stone[][] stonelist = new Stone[9][9];
    private char[] chars ={'1', '2', '3', '4', '5', '6' ,'7', '8'};
    private int x;
    private int y;

    public Board(int x, int y, Stone stones[][]){
        this.x = x;
        this.y = y;
        for(int i=1; i<this.y; i++) {
            for (int j = 1; j < this.x; j++) {
                Stone stone = new Stone('E', 'j', 'i');
                stones[i][j] = stone;
                stones[j][0] = new Stone(chars[j-1], 'j',0 );
            }
            stones[0][i] = new Stone(chars[i-1], 0,'i' );
        }
        stones[0][0] = new Stone('/', 0,0 );
        this.stonelist =stones;

    }
    public void initialize(){
    }
    private static boolean over=true;
    public static boolean isOver() {
        return over;
    }

    public void open(){
        int num_W=0;
        int num_B=0;
        int num_E=0;

        for(int i=0; i<this.y; i++){
            for (int j = 0; j < this.x; j++){
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
            over = false;
        }
        System.out.printf("白の個数は%d個\n", num_W);
        System.out.printf("黒の個数は%d個\n", num_B);
    }
    public static Stone getStonelist(int x,int y){
        return stonelist[x][y];
    }
}

