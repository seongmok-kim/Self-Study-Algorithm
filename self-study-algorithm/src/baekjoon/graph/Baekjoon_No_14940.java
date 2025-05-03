package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_14940 {

    static int[][] map;
    static int[][] distance;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        map = new int[n][m];
        distance = new int[n][m];
        Pos startPos = new Pos(0, 0);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                distance[i][j] = -1;
                int type = Integer.parseInt(st.nextToken());
                if (type == 2) {
                    startPos = new Pos(j, i);
                }
                map[i][j] = type;
            }
        }

        bfs(startPos);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dis = distance[i][j];
                if (dis == -1) {
                    dis = map[i][j] == 0 ? 0 : -1;
                }
                if (j + 1 == m) {
                    System.out.print(dis);
                } else {
                    System.out.print(dis + " ");
                }
            }
            System.out.println();
        }
    }

    static void bfs(Pos pos) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(pos);
        distance[pos.y][pos.x] = 0;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = cur.x + dx[k];
                int y = cur.y + dy[k];

                if (x < 0 || y < 0 || x >= m || y >= n) {
                    continue;
                }
                if (map[y][x] == 0 || distance[y][x] != -1) {
                    continue;
                }
                int curDistance = distance[cur.y][cur.x] + map[y][x];
                Pos nextPos = new Pos(x, y);
                distance[y][x] = curDistance;
                queue.add(nextPos);
            }
        }
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
