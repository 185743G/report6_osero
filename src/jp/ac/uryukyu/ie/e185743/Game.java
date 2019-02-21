package jp.ac.uryukyu.ie.e185743;

public class Game {
    public static final int[][] dir =
            {{0, 0, 0},//移動なし
                    {-1, 0, -1},//左下
                    {0, -1, -1},//前下
                    {-1, -1, -1},//左前下
                    {-1, 1, -1},//左後下
                    {1, 0, -1},//右下
                    {0, 1, -1},//後下
                    {1, -1, -1},//右前下
                    {1, 1, -1},//右後下
                    {-1, 0, 0},//左中
                    {0, -1, 0},//前中
                    {-1, -1, 0},//左前中
                    {-1, 1, 0},//左後中
                    {1, 0, 0},//右中
                    {0, 1, 0},//後中
                    {1, -1, 0},//右前中
                    {1, 1, 0},//右後中
                    {-1, 0, 1},//左上
                    {0, -1, 1},//前上
                    {-1, -1, 1},//左前上
                    {-1, 1, 1},//左後上
                    {1, 0, 1},//右上
                    {0, 1, 1},//後上
                    {1, -1, 1},//右前上
                    {1, 1, 1}};//右後上
    private static int
            ENEMY = 3 - Main.turn,
            ALLY = Main.turn;

    private static boolean chk_stone(int[] pos) {
        if (Board.chk_Cell_Ahead(pos, dir[0]) != Main.EMPTY) {//置きたいマスが空きマスか確認
            return false;
        }

        boolean result = false;
        int[] p = new int[3];
        out:for (int i = 1; i < dir.length; i++) {
            p[Main.x] = pos[Main.x];
            p[Main.y] = pos[Main.y];
            p[Main.z] = pos[Main.z];
            if (Board.chk_Cell_Ahead(pos, dir[i]) == ENEMY) {//置きたいマスの周囲１マスに敵がいるか確認
                while (true) {
                    Board.set_Next_Position(p, dir[i]);//さらに先の座標を代入
                    if (Board.chk_Cell_Ahead(p, dir[i]) == ALLY) {//味方が敵を挟んでるか確認
                        result = true;
                        break out;

                    } else if (Board.chk_Cell_Ahead(p, dir[i]) == ENEMY) {
                        continue;
                    }//壁まで調べたら別の方向を検討する
                    break;
                }
            }
        }
        return result;
    }
    private static void flip(int[] pos){
        Board.setCell(pos, ALLY);
        for(int i=1;i<dir.length;i++){
            int[] p = new int[3];
            p[Main.x] = pos[Main.x];
            p[Main.y] = pos[Main.y];
            p[Main.z] = pos[Main.z];
            if(Board.chk_Cell_Ahead(pos, dir[i]) == ENEMY){
                out:while(true){
                    Board.set_Next_Position(p, dir[i]);

                    if(Board.chk_Cell_Ahead(p, dir[i]) == ALLY){
                        while(true){
                            p[Main.x] = pos[Main.x]; // p に再びinputされた座標を代入
                            p[Main.y] = pos[Main.y];
                            p[Main.z] = pos[Main.z];
                            Board.set_Next_Position(p, dir[i]);
                            if(Board.chk_Cell_Ahead(p, dir[i]) == ALLY){
                                Board.setCell(p, ALLY);
                                break out;
                            }
                            Board.setCell(p, ALLY);
                        }
                    } else if(Board.chk_Cell_Ahead(p, dir[i]) == ENEMY){
                        continue;
                    }
                    break;
                }
            }
        }
    }

    public static void update(int[] input){
        if(chk_stone(input)){
            flip(input);
            Main.change_turn();
        }else {
            System.out.println("指定した座標には置けませんでした");
            Main.print_Usage();
        }
        ENEMY = 3 - Main.turn;
        ALLY = Main.turn;
    }

}
