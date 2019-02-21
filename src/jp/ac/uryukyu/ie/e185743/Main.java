package jp.ac.uryukyu.ie.e185743;

public class Main {
    public static final int
            L = 8,//立方体オセロ盤の一辺の長さ 周囲１マス分壁で覆っているので実質6 //L = 10 や L = 8　でも動く
            EMPTY=0,// オセロ盤のマスに何も置かれてないこと
            WHITE=1,// オセロ盤のマスに白石が置かれていること
            BLACK=2,// オセロ盤のマスに黒石が置かれていること
            WALL=3,// オセロ盤のマスに壁が置かれている　ただしインタフェースには表示せず
            x=0,//x座標は0番目に格納
            y=1,//y座標は1番目に格納
            z=2;//z座標は2番目に格納

    public static final char[] symbols ={'E', '●', '○'};

    public static final String[]  players ={"いません", "白", "黒"};

    public static void print_Usage(){
        System.out.printf("---    遊び方    ---\n パス: pass \n ゲームの終了 : quit\n 座標を入れる : 1~%d\n", L-2);
    }

    public static int
            turn = WHITE;//白の手番から

    public static final int change_turn(){
        turn = 3 - turn;
        return turn;
    }

    public static void show_turn(){
        System.out.printf("！！%sのターン！！\n", players[turn]);
    }


    public static void main(String[] args){
        Board board = new Board(L, L, L);
        board.initialize(L);
        print_Usage();
        out1:while (board.gameIs_Over() == false && Output.PC_QUIT == false){
            board.open();
            show_turn();
            Output.show();
        }
    }
}