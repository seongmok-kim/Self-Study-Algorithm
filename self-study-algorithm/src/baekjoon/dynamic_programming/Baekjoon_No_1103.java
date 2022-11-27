package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/1103
public class Baekjoon_No_1103 {

    public static int N, M;

    public static char[][] map;
    public static int[][] dp;

    public static boolean[][] visited;

    public static int[] xmove = {-1, 1, 0, 0};
    public static int[] ymove = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            map[i] = s.toCharArray();
        }

        int result = DFS(0,0);			// DFS 함수 호출

        if(result == -1) {
            System.out.println(-1);
        }
        else {
            System.out.println(result+1);		// 시작지점부터 세기 때문에 +1 추가.
        }

    }

    // DFS & 백트래킹 & DP 이용
    public static int DFS(int y, int x) {
        if(dp[y][x] != 0) {
            return dp[y][x];
        }

        int num = map[y][x] - '0';

        for(int i=0; i<4; i++) {
            int tempY = y + (ymove[i]*num);
            int tempX = x + (xmove[i]*num);

            // 범위 밖으로 벗어나는 경우 무시
            if(tempY < 0 || tempY >= N || tempX < 0 || tempX >= M) {
                continue;
            }

            // 구멍인 경우 무시
            if(map[tempY][tempX] == 'H') {
                continue;
            }

            // 방문한 적이 있다면 무한 루프이므로, -1 반환
            if(visited[tempY][tempX] == true) {
                return -1;
            }

            // 위의 조건들을 만족하지 않았다면, 방문 가능
            visited[tempY][tempX] = true;			// 방문 체크 후
            int next = DFS(tempY,tempX);

            // -1이면 -1 그대로 반환
            if(next == -1) {
                return -1;
            }
            if(next + 1 > dp[y][x]) {
                dp[y][x] = next + 1;
            }
            visited[tempY][tempX] = false;			// 백트래킹
        }

        return dp[y][x];
    }

}