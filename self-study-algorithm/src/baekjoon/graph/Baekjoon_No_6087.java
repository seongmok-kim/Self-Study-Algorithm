package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_6087 {

    static int W,H;
    static char[][] map;
    static Pos START;
    static Pos END;
    static int[] xmove = {-1,0,1,0};
    static int[] ymove = {0,1,0,-1};
    static final int INF = Integer.MAX_VALUE;
    static int MIN = INF;

    public static class Pos implements Comparable<Pos>{
        int y;
        int x;
        int mirrors;
        int direction;		// 0:왼쪽, 1:위, 2:오른쪽, 3:아래

        public Pos(int y, int x, int mirrors, int direction) {
            this.y=y;
            this.x=x;
            this.mirrors = mirrors;
            this.direction = direction;
        }

        @Override
        public int compareTo(Pos other) {
            if(this.mirrors < other.mirrors) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        boolean flag = false;

        // .: 빈칸, *: 벽, C: 레이저로 연결해야하는 칸
        for(int i=0; i<H; i++) {
            char[] temp = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                map[i][j] = temp[j];

                if(temp[j] == 'C') {
                    if(flag == false) {
                        START = new Pos(i,j,0,0);		// 시작 지점
                        flag = true;
                    }
                    else {
                        END = new Pos(i,j,0,0);			// 끝 지점
                    }
                }
            }
        }

        System.out.println(BFS());
    }

    public static int BFS() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();		// 우선순위큐 및 BFS이용
        pq.offer(new Pos(START.y, START.x, 0, -1));

        int[][] visited = new int[H][W];
        for(int i=0; i<H; i++) {
            Arrays.fill(visited[i], INF);			// 방문 체크 변수를 INF로 가득 채워둠
        }

        while(!pq.isEmpty()) {
            Pos pos = pq.poll();

            if(pos.y == END.y && pos.x == END.x) {
                return pos.mirrors;		// 우선순위 큐를 사용했기 때문에 여기에 먼저 도착하는 것이 최소 비용
            }

            for(int i=0; i<4; i++) {
                int tempY = pos.y + ymove[i];
                int tempX = pos.x + xmove[i];

                // 범위를 벗어나면 무시
                if(tempY >= H || tempY < 0 || tempX >= W || tempX < 0) {
                    continue;
                }
                // 벽을 만난 경우에도 무시
                if(map[tempY][tempX] == '*') {
                    continue;
                }

                // 필요한 거울의 수
                int mirrors = pos.mirrors;

                // 시작지점이 아니고, 방향 전환이 이루어졌다면 거울의 수 + 1
                if(i != pos.direction && pos.direction != -1) {
                    mirrors++;
                }

                // 이전에 방문한 적이 있던 없던, 필요한 거울의 수가 지금보다 더 많았을 경우 해당 위치 확인
                // 같거나 클 때로 해야함. (클 때로만 하면 안됨)
                if(visited[tempY][tempX] >= mirrors) {
                    visited[tempY][tempX] = mirrors;
                    pq.offer(new Pos(tempY, tempX, mirrors, i));
                }
            }
        }
        return -1;
    }
}