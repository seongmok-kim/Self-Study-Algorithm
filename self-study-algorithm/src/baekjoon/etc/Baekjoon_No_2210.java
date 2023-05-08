package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon_No_2210 {
    static int[][] map = new int[5][5];

    // 상하좌우 이동
    static int[] xmove = {-1, 1, 0, 0};
    static int[] ymove = {0, 0, -1, 1};

    static Set<String> answer = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<5; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++)
                DFS(i,j, 0, String.valueOf(map[i][j]));
        }

        System.out.println(answer.size());
    }

    static void DFS(int y, int x, int cnt, String str) {
        if(cnt >= 5) {
            answer.add(str);
            return;
        }

        for(int i=0; i<4; i++) {
            int tempY = y + ymove[i];
            int tempX = x + xmove[i];

            if(tempY < 0 || tempY >= 5 || tempX < 0 || tempX >= 5) continue;

            String tempStr = str + map[tempY][tempX];
            DFS(tempY, tempX, cnt+1, tempStr);
        }
    }
}
