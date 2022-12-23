package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_2638 {

    public static int N,M;
    public static int[][] map;				// Map
    public static boolean[][] visited;		// 방문체크 변수

    // 상하좌우 변수에 사용
    public static int[] ymove = {-1, 1, 0, 0};
    public static int[] xmove = {0, 0, -1, 1};

    // Cheese 클래스. 우선순위 큐에서 사용할 것이기에 Comparable 구현
    public static class Cheese implements Comparable<Cheese>{
        int y;
        int x;
        int time;

        public Cheese(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        // 시간이 낮은 치즈가 우선순위를 가지도록
        @Override
        public int compareTo(Cheese o) {
            if(this.time < o.time){
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        PriorityQueue<Cheese> cheeseQueue = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 치즈일 때만, cheeseQueue에 넣기
                if(map[i][j] == 1) {
                    cheeseQueue.offer(new Cheese(i,j,1));
                }

            }
        }

        int time = 1;

        // 외부 공기 찾기. (DFS함수에서 외부 공기는 -1로 초기화)
        visited[0][0] = true;		// 가장자리는 항상 공기라고 했으므로 0,0은 항상 외부 공기임
        DFS(0,0);

        while(!cheeseQueue.isEmpty()) {
            Cheese cheese = cheeseQueue.poll();

            // 시간이 흐른 경우에
            if(time != cheese.time) {
                time = cheese.time;

                // 치즈 녹이기. map Update (2인 치즈를 0으로 수정)
                for(int i=0; i<N; i++) {
                    for(int j=0; j<M; j++) {
                        if(map[i][j] == 2)
                            map[i][j] = 0;
                    }
                }

                // 외부 공기 다시 갱신
                visited = new boolean[N][M];
                visited[0][0] = true;
                DFS(0,0);
            }

            // 현재 치즈가 맞닿아있는 공기 갯수 찾기
            int cnt = 0;
            for(int i=0; i<4; i++) {
                int tempY = cheese.y + ymove[i];
                int tempX = cheese.x + xmove[i];

                if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < M) {
                    if(map[tempY][tempX] == -1) {		// 공기인데 내부 공기가 아닐 경우
                        cnt++;
                    }
                }
            }

            // 인접한 공기가 2군데 이상인 경우, 공기로 바꿀예정
            if(cnt >= 2) {
                map[cheese.y][cheese.x] = 2;
            }
            // 아니라면 치즈가 남아있으니 치즈 큐에 추가
            else {
                cheeseQueue.offer(new Cheese(cheese.y, cheese.x, time+1));
            }
        }

        System.out.println(time);
    }

    // 외부 공기 찾기
    public static void DFS(int y, int x) {
        map[y][x] = -1;

        for(int i=0; i<4 ;i++) {
            int tempY = y + ymove[i];
            int tempX = x + xmove[i];

            if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < M) {
                // -1, 1 둘 다 공기이므로 조건에 != 1이 들어간 것. (어처피 2는 0으로 바뀌고 나머지는 들어올 일 X)
                if(map[tempY][tempX] != 1 && visited[tempY][tempX] == false) {
                    visited[tempY][tempX] = true;
                    DFS(tempY, tempX);
                }
            }
        }

    }

}