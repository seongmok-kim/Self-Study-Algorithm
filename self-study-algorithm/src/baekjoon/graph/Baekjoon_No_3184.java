package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_3184{
    static int[] xmove = {-1, 1, 0, 0};
    static int[] ymove = {0, 0, -1, 1};

    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    static int sheepCnt = 0;
    static int wolfCnt = 0;

    static class Pos{
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R= Integer.parseInt(st.nextToken());        // 줄
        C= Integer.parseInt(st.nextToken());        // 칸

        // # : 울타리, . : 필드, o : 양, v : 늑대
        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i=0; i<R; i++){
            String s = br.readLine();
            char[] chars = s.toCharArray();

            for(int j=0; j<C; j++){
                map[i][j] = chars[j];
            }
        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(!visited[i][j] && map[i][j] != '#'){
                    BFS(i,j);
                }
            }
        }

        System.out.println(sheepCnt + " " + wolfCnt);
    }

    static void BFS(int y, int x){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(y,x));
        visited[y][x] = true;

        int sheep = 0;
        int wolf = 0;

        while(!q.isEmpty()){
            // BFS 탐색
            Pos now = q.poll();

            // o : 양, v : 늑대 수 체크
            if(map[now.y][now.x] == 'o'){
                sheep+=1;
            }
            if(map[now.y][now.x] == 'v'){
                wolf += 1;
            }

            for(int i=0; i<4; i++){
                int tempY = now.y + ymove[i];
                int tempX = now.x + xmove[i];

                if(tempY < 0 || tempY >= R|| tempX < 0 || tempX >= C){
                    continue;
                }

                // #이 아닐때 탐색 가능.
                if(map[tempY][tempX] != '#' && !visited[tempY][tempX]){
                    visited[tempY][tempX] = true;
                    q.offer(new Pos(tempY, tempX));
                }
            }
        }

        if(sheep > wolf){
            wolf = 0;
        }
        else{
            sheep = 0;
        }

        sheepCnt += sheep;
        wolfCnt += wolf;
    }
}
