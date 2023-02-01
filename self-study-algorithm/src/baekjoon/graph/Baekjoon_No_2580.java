package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 참고 : https://st-lab.tistory.com/119
public class Baekjoon_No_2580 {

    public static int[][] map = new int[9][9];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Sudoku(0,0);
    }

    public static void Sudoku(int y, int x) {
        if(x == 9) {
            Sudoku(y+1, 0);
            return;
        }

        if(y == 9) {
            StringBuilder sb = new StringBuilder();

            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
            System.exit(0);			// 시스템을 종료해야함. 안그러면 중복 출력 가능
        }

        if(map[y][x] == 0) {
            for(int i=1; i<=9; i++) {
                if(check(y, x, i)) {
                    map[y][x] = i;
                    Sudoku(y, x+1);
                }
            }

            map[y][x] = 0;		// 백트래킹. 다시 돌려둠.
            return;
        }

        Sudoku(y, x+1);
    }

    // 검사
    public static boolean check(int y, int x, int num) {
        // 1. 가로줄 검사
        for(int i=0; i<9; i++) {
            if(map[y][i] == num) {
                return false;
            }
        }

        // 2. 세로줄 검사
        for(int i=0; i<9; i++) {
            if(map[i][x] == num) {
                return false;
            }
        }

        // 3. 3x3 검사
        int initY = (y / 3) * 3; // value가 속한 3x3의 행의 첫 위치
        int initX = (x / 3) * 3; // value가 속한 3x3의 열의 첫 위치

        for (int i = initY; i < initY + 3; i++) {
            for (int j = initX; j < initX + 3; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

}
