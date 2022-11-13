package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/1520
// 도움받은 블로그 : https://velog.io/@mulgyeol/백준-1520-내리막길-Java
public class Baekjoon_No_1520 {
    static int M, N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dp;

    static int[] ymove = {-1, 1, 0, 0};
    static int[] xmove = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        visited = new boolean[M][N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP와 DFS이용
        dp[M-1][N-1] = 1;
        DFS(0, 0);

        System.out.println(dp[0][0]);
    }

    static void DFS(int y, int x) {
        if(y == M-1 && x == N-1) {
            return;
        }

        if(visited[y][x]) {
            return;
        }
        visited[y][x] = true;

        for(int i=0; i<4; i++) {
            int tempY = y + ymove[i];
            int tempX = x + xmove[i];

            if(tempY < 0 || tempY >= M || tempX < 0 || tempX >= N) {
                continue;
            }

            // 높이가 낮은 지점만 갈 수 있음
            if(map[y][x] > map[tempY][tempX]) {
                if(dp[tempY][tempX] == 0) {
                    DFS(tempY, tempX);
                    dp[y][x] += dp[tempY][tempX];
                }
                else if(dp[tempY][tempX] > 0) {
                    dp[y][x] += dp[tempY][tempX];
                }
            }
        }

    }

}