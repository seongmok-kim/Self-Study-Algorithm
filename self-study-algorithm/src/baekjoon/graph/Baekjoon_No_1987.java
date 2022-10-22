package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baekjoon_No_1987 {

    static int R, C;
    static char[][] map;

    static int[] xmove = {-1,1,0,0};
    static int[] ymove = {0,0,-1,1};

    static int result = 0;

    static HashMap<Character, Boolean> charMap = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0; i<R; i++) {
            char[] temp = br.readLine().toCharArray();
            map[i] = temp;
        }

        // DFS 및 백트래킹 이용
        charMap.put(map[0][0], true);
        DFS(0,0,1);

        System.out.println(result);
    }

    static void DFS(int y, int x, int cnt) {
        if(cnt > result) {
            result = cnt;
        }

        for(int i=0; i<4; i++) {
            int tempY = y + ymove[i];
            int tempX = x + xmove[i];

            if(tempY < 0 || tempY >= R || tempX < 0 || tempX >= C) {
                continue;
            }

            if(charMap.get(map[tempY][tempX]) == null) {
                charMap.put(map[tempY][tempX], true);
                DFS(tempY, tempX, cnt+1);

                // 백트래킹
                charMap.remove(map[tempY][tempX]);
            }
        }
    }
}