package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/1937
// 참고한 블로그 : https://steady-coding.tistory.com/39
public class Baekjoon_No_1937 {

    public static int N;
    public static int[][] map;
    public static int[][] dp;

    public static int[] xmove = {-1,1,0,0};
    public static int[] ymove = {0,0,-1,1};

    public static int MAX = 0;

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS 전체탐색
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int cnt = DFS(i,j);
                MAX = Math.max(MAX, cnt);
            }
        }

        System.out.println(MAX);
    }

    // DP와 DFS이용
    // DP를 이용하기 때문에 방문 체크변수는 이용하지 않음
    public static int DFS(int y, int x) {
        if(dp[y][x] != 0) {
            return dp[y][x];
        }

        dp[y][x] = 1;
        for(int i=0; i<4; i++) {
            int tempY = y + ymove[i];
            int tempX = x + xmove[i];

            if(tempY < 0 || tempY >= N || tempX < 0 ||tempX >= N) {
                continue;
            }

            if(map[y][x] < map[tempY][tempX]) {
                dp[y][x] = Math.max(dp[y][x], DFS(tempY, tempX) + 1);
            }
        }

        return dp[y][x];
    }
}