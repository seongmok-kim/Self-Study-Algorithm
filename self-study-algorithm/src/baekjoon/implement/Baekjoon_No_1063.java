package baekjoon.implement;

import java.util.Scanner;

public class Baekjoon_No_1063 {
    static int kingX, kingY;
    static int stoneX, stoneY;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String kingPos = input[0];
        String stonePos = input[1];
        int n = Integer.parseInt(input[2]);

        kingX = kingPos.charAt(0) - 'A' + 1;
        kingY = kingPos.charAt(1) - '0';
        stoneX = stonePos.charAt(0) - 'A' + 1;
        stoneY = stonePos.charAt(1) - '0';

        for (int i = 0; i < n; i++) {
            String cmd = sc.nextLine();
            move(cmd);
        }

        System.out.println((char) (kingX + 'A' - 1) + "" + kingY);
        System.out.println((char) (stoneX + 'A' - 1) + "" + stoneY);
    }

    public static void move(String dir) {
        int dx = 0, dy = 0;

        switch (dir) {
            case "R": dx = 1; break;
            case "L": dx = -1; break;
            case "B": dy = -1; break;
            case "T": dy = 1; break;
            case "RT": dx = 1; dy = 1; break;
            case "LT": dx = -1; dy = 1; break;
            case "RB": dx = 1; dy = -1; break;
            case "LB": dx = -1; dy = -1; break;
        }

        int tempKingX = kingX + dx;
        int tempKingY = kingY + dy;
        if (tempKingX < 1 || tempKingY < 1 || tempKingX > 8 || tempKingY > 8) {
            return;
        }

        if (tempKingX == stoneX && tempKingY == stoneY) {
            int tempStoneX = stoneX + dx;
            int tempStoneY = stoneY + dy;

            if (tempStoneX < 1 || tempStoneY < 1 || tempStoneX > 8 || tempStoneY > 8) {
                return;
            }
            stoneX = tempStoneX;
            stoneY = tempStoneY;
        }
        kingX = tempKingX;
        kingY = tempKingY;
    }
}
