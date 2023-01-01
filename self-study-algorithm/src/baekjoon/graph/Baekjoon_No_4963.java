package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_4963 {

    // 상하좌우 대각선 확인하는데 사용하는 배열
    public static int[] xmove = {-1,1,0,0,1,1,-1,-1};
    public static int[] ymove = {0,0,-1,1,1,-1,1,-1};

    public static int W, H;
    public static int[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        // 무한반복
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            // 0 0을 입력하면 반복문 탈출
            if(W == 0 && H == 0)
                break;

            map = new int[H][W];
            visited = new boolean[H][W];

            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;

            // DFS로 섬의 갯수 확인
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {

                    // 방문한적 없고, 섬이면 DFS함수 진행 및 섬 갯수 추가
                    if(visited[i][j] == false && map[i][j] == 1) {
                        visited[i][j] = true;
                        cnt++;
                        DFS(i,j);
                    }
                }
            }

            sb.append(cnt + "\n");
        }

        System.out.println(sb.toString());		// 결과 출력
    }

    // DFS
    public static void DFS(int y, int x) {
        for(int i=0; i<8; i++) {			// 상하좌우대각선 확인
            int tempY = y + ymove[i];
            int tempX = x + xmove[i];

            if(tempY >= 0 && tempY < H && tempX >= 0 && tempX < W) {
                if(visited[tempY][tempX] == false && map[tempY][tempX] == 1) {
                    visited[tempY][tempX] = true;
                    DFS(tempY, tempX);
                }
            }
        }
    }
}