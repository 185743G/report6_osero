package jp.ac.uryukyu.ie.e185743;
import java.util.Scanner;

public class Output {
    public static final int
            QUIT = 0,
            PASS = 1,
            COMMAND = 2,
            NONE = 3;
    public static boolean PC_QUIT = false;


    private static Scanner  sc = new Scanner(System.in);

    public static void show() {
        int[] input = new int[3];
        out:
        for (int i = 0; i < 3; i++) {
            if (i == Main.x) {
                System.out.println("x座標を入力してください");
            } else if (i == Main.y) {
                System.out.println("y座標を入力してください");
            } else if (i == Main.z) {
                System.out.println("z座標を入力してください");
            }
            String str = sc.nextLine();
            switch (input(str)) {
                case QUIT: {
                    Board.tell_winner();
                    System.out.println("Thank you for Playing ");
                    PC_QUIT = true;
                    break out;
                }
                case PASS: {
                    Main.change_turn();
                    break out;
                }
                case COMMAND: {
                    input[i] = Integer.parseInt(str);
                    if (i == 2) {
                        Game.update(input);
                        break out;
                    }
                    break;
                }
                case NONE:{
                    System.out.println("指定した数または文字は不正です。");
                    Main.print_Usage();
                    break out;
                }
            }
        }
    }

    private static int input(String str){
        int quit_or_pass_or_command;
        while(true){
            if(str.equals("quit")){
                quit_or_pass_or_command = QUIT;
                break;
            }else if(str.equals("pass")){
                quit_or_pass_or_command = PASS;
                break;
            }else if(is_Position(str)){
                quit_or_pass_or_command = COMMAND;
                break;
            }else {
                quit_or_pass_or_command = NONE;
                break;
            }
        }
        return quit_or_pass_or_command;
    }

    private static boolean is_Position(String str){//str に　"1"~"4"が入っていないか調べます
        try{
            Integer.parseInt(str);
        }catch(Exception e){
            return false;
        }
        boolean result = false;
        for (int i = 1; i < Main.L -1; i++){
            if(Integer.parseInt(str) == i) {
                result = true;
            }
        }
        return result;
    }
}