package jp.ac.uryukyu.ie.e185743;


public class Board {
    private int x;
    private int y;
    private int z;
    private static int[][][] cell;
    private static int winner = Main.EMPTY;
    public boolean is_Over = false;

    public Board(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.cell = new int[x][y][z];
    }
    public static void setCell(int[] pos, int col){
        cell[pos[Main.x]][pos[Main.y]][pos[Main.z]] = col;
    }

    public void initialize(int L) {//盤面の初期配置 盤の大きさを引数にとり盤の中央に2*2*2で配置する
        int init_poi1 = L/2 -1;
        int init_poi2 = L/2;
        for(int a = 0; a < y; a++){
            for(int b = 0; b < x; b++){
                cell[b][a][z-z] = Main.WALL;//盤の底面を壁に置き換え
                cell[b][a][z-1] = Main.WALL;//上面を
                cell[b][y - y][a] = Main.WALL;//前面を
                cell[b][y-1][a] = Main.WALL;//背面を
                cell[x-x][b][a] = Main.WALL;//左側面を
                cell[x-1][b][a] = Main.WALL;//右側面を
            }
        }
        cell[init_poi1][init_poi2][init_poi2] =
                cell[init_poi2][init_poi1][init_poi2] =
                        cell[init_poi2][init_poi2][init_poi1] =
                                cell[init_poi1][init_poi1][init_poi1] = Main.WHITE;
        cell[init_poi1][init_poi1][init_poi2] =
                cell[init_poi2][init_poi2][init_poi2] =
                        cell[init_poi1][init_poi2][init_poi1] =
                                cell[init_poi2][init_poi1][init_poi1] = Main.BLACK;
    }

    public void open() {
        //盤の状態をプロンプトにあげ、集計します
        int[] num_Stones = new int[3];

        for (int k = 1; k < z-1; k++) {
            System.out.println(" ");
            System.out.print(k);
            System.out.print(" ");
            for (int i = 1; i < x-1; i++) {
                System.out.print(" ");
                System.out.print(i);

            }
            for (int j = 1; j < y-1; j++) {
                System.out.println(" ");
                System.out.print(j);
                System.out.print(" ");
                for (int l = 1; l < x-1; l++) {
                    System.out.print(" ");
                    switch (cell[l][j][k]) {
                        case Main.EMPTY: {
                            System.out.print(Main.symbols[Main.EMPTY]);
                            num_Stones[Main.EMPTY]++;
                            break;
                        }
                        case Main.WHITE: {
                            System.out.print(Main.symbols[Main.WHITE]);
                            num_Stones[Main.WHITE]++;
                            break;
                        }
                        case Main.BLACK: {
                            System.out.print(Main.symbols[Main.BLACK]);
                            num_Stones[Main.BLACK]++;
                            break;
                        }
                    }
                }
            }
            System.out.println(" ");
        }
        if (num_Stones[Main.EMPTY] == 0 ||
                num_Stones[Main.WHITE] == 0 ||num_Stones[Main.BLACK] == 0) {
            is_Over = true;
        }
        if (is_Over == true) {
            gameSet(num_Stones);
        }
        System.out.printf("%s:%d \n", Main.players[Main.WHITE], num_Stones[Main.WHITE]);
        System.out.printf("%s:%d \n", Main.players[Main.BLACK], num_Stones[Main.BLACK]);
    }

    private void gameSet(int[] num_Stones) {
        if (num_Stones[Main.WHITE] < num_Stones[Main.BLACK]) {
            winner = Main.BLACK;
        } else if (num_Stones[Main.WHITE] > num_Stones[Main.BLACK]) {
            winner = Main.WHITE;
        }
        tell_winner();
    }
    public static void tell_winner(){//勝者を伝えます
        System.out.printf("勝者は...%s！！！\n", Main.players[winner]);
    }

    public boolean gameIs_Over() {//is_Overのゲッター
        return is_Over;
    }

    public static int chk_Cell_Ahead(int[] pos, int[] dir){//bの座標からDir動いた座標のcellを確認
        return cell[pos[Main.x] + dir[Main.x]][pos[Main.y] + dir[Main.y]][pos[Main.z] +dir[Main.z]];
    }

    public static int[] set_Next_Position(int[] pos, int[] dir){//座標からDir動いた座標を得る
        pos[Main.x] = pos[Main.x] + dir[Main.x];
        pos[Main.y] = pos[Main.y] + dir[Main.y];
        pos[Main.z] = pos[Main.z] + dir[Main.z];
        return pos;
    }
}