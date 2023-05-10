package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_7562 {
    static class Pos {
        int y;
        int x;
        int cnt;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
            this.cnt = 0;
        }

        public Pos(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 체스가 이동하게 될 칸 설정
        int[] ymove = {2, 2, -2, -2, -1, 1, -1, 1};
        int[] xmove = {1, -1, 1, -1, -2, -2, 2, 2};

        int N = Integer.parseInt(br.readLine());

        // 테스트 케이스의 수만큼 반복
        for(int i=0; i<N; i++) {
            int L = Integer.parseInt(br.readLine());
            boolean[][] visited = new boolean[L][L];

            StringTokenizer st = new StringTokenizer(br.readLine());
            Pos start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            st = new StringTokenizer(br.readLine());
            Pos end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            Queue<Pos> q = new LinkedList<>();
            visited[start.y][start.x] = true;
            q.offer(start);

            while(!q.isEmpty()) {           // 큐가 빌 때까지 반복
                Pos now = q.poll();

                // 목적지에 도착 확인
                if(now.y == end.y && now.x == end.x) {
                    sb.append(now.cnt + "\n");
                    break;
                }

                // 현재 위치에서 갈 수 있는 곳 모두 확인
                for(int j=0; j<8; j++) {
                    int tempY = now.y + ymove[j];
                    int tempX = now.x + xmove[j];


                    if(tempY < 0 || tempY >= L || tempX < 0 || tempX >= L) continue;        // 범위를 벗어나면 무시
                    if(visited[tempY][tempX])   continue;       // 방문했던 칸이면 무시

                    visited[tempY][tempX] = true;
                    q.offer(new Pos(tempY, tempX, now.cnt+1));
                }
            }
        }

        System.out.println(sb.toString());
    }
}
