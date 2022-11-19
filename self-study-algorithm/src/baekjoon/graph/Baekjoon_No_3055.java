package baekjoon.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_3055 {

    public static int R, C;
    public static char[][] map;
    public static boolean[][] visited;

    // 상하좌우에 사용됨
    public static int[] ymove = {-1,1,0,0};
    public static int[] xmove = {0,0,-1,1};

    // 시작, 종료지점에 사용
    public static Pos START = new Pos(0,0,0);
    public static Pos END = new Pos(0,0,0);;

    // 물 퍼트리는 데 사용
    public static ArrayList<Pos> nextWater = new ArrayList<>();
    public static boolean[][] waterVisited;

    // 위치 클래스.
    // Comparable 인터페이스를 구현하여 PriorityQueue 사용함
    public static class Pos implements Comparable<Pos>{
        int y;
        int x;
        int sec;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Pos(int y, int x, int sec) {
            this.y = y;
            this.x = x;
            this.sec = sec;
        }

        @Override
        public int compareTo(Pos other) {
            if(this.sec < other.sec) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // *: 물, X: 벽, D: 목적지, S:출발지
        map = new char[R][C];
        visited = new boolean[R][C];
        waterVisited = new boolean[R][C];

        for(int i=0; i<R; i++) {
            String temp = br.readLine();
            map[i] = temp.toCharArray();

            for(int j=0; j<C; j++) {
                if(map[i][j] == 'D') {
                    END.y=i;
                    END.x=j;
                }
                else if(map[i][j] == 'S') {
                    START.y=i;
                    START.x=j;
                    START.sec=0;
                }
                else if(map[i][j] == '*') {
                    nextWater.add(new Pos(i,j));
                }
            }
        }


        moveWater();		// 물을 한 번 퍼트려준다.		(물을 먼저 퍼트리고, 고슴도치가 이동할 영역을 확인하는 식으로 진행함)

        PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
        pq.offer(START);
        visited[START.y][START.x] = true;

        int sec = 0;		// 현재 시간

        // 우선순위 큐가 빌 때까지 반복
        while(!pq.isEmpty()) {
            Pos now = pq.poll();

            // BFS 및 우선순위 큐를 이용했으므로 가장 먼저 도착하는 경로가 최단 경로임.
            if(now.y == END.y && now.x == END.x) {
                System.out.println(now.sec);
                return;
            }

            // 시간이 지난 경우 물 퍼트리기.
            if(sec < now.sec) {
                sec = now.sec;
                moveWater();
            }

            for(int i=0; i<4; i++) {
                int tempY = now.y + ymove[i];
                int tempX = now.x + xmove[i];

                // 범위를 벗어나면 무시
                if(tempY >= R || tempY < 0 || tempX >= C || tempX < 0) {
                    continue;
                }
                else {
                    // 벽이나 물이면 무시
                    if(map[tempY][tempX] == '*' || map[tempY][tempX] == 'X') {
                        continue;
                    }
                    else {
                        // 방문한적 없는 곳이면 이동
                        if(visited[tempY][tempX] == false) {
                            visited[tempY][tempX] = true;
                            pq.offer(new Pos(tempY, tempX, now.sec+1));
                        }
                    }
                }
            }
        }

        // 비버의 굴로 이동할 수 없는 경우
        System.out.println("KAKTUS");
    }

    // 물 퍼트리기
    public static void moveWater() {
        ArrayList<Pos> tempList = new ArrayList<>();

        // 퍼질 물의 위치 반복
        for(Pos water : nextWater) {
            int y = water.y;
            int x = water.x;

            // 상하좌우로 퍼짐
            for(int i=0; i<4; i++) {
                int tempY = y + ymove[i];
                int tempX = x + xmove[i];

                // 범위를 벗어나면 무시
                if(tempY >= R || tempY < 0 || tempX >= C || tempX < 0) {
                    continue;
                }
                else {
                    // 벽이거나 비버집(도착지)이면 무시
                    if(map[tempY][tempX] == 'X' || map[tempY][tempX] == 'D') {
                        continue;
                    }
                    else {
                        // 물이 퍼지지 않았던 곳인 경우에만 퍼트림
                        if(waterVisited[tempY][tempX] == false) {
                            waterVisited[tempY][tempX] = true;
                            tempList.add(new Pos(tempY, tempX));
                            map[tempY][tempX] = '*';
                        }
                    }
                }
            }
        }

        nextWater = tempList;
    }
}