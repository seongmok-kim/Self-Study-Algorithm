package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baekjoon_No_2151 {
    static class Node implements Comparable<Node>{
        int y;
        int x;
        int cnt;        // 거울 설치 횟수
        int direction;  // 방향

        public Node(int y, int x, int cnt, int direction) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.direction = direction;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // *: 벽, #: 문, .: 빈공간, !: 거울 설치 가능 위치
        char[][] map = new char[N][N];

        // 4방향에서 오는 거까지 계산한 방문 체크 변수 사용
        boolean[][][] visited = new boolean[N][N][4];

        // 방향
        // 0: 오른쪽, 1: 아래, 2: 왼쪽, 3: 위
        int[] xmove = {1, 0, -1, 0};
        int[] ymove = {0, 1, 0, -1};

        Node start = new Node(0,0, 0,0), end = new Node(0,0, 0,0);

        boolean flag = false;
        for(int i=0; i<N; i++){
            String s = br.readLine();
            char[] tempArr = s.toCharArray();

            for(int j=0; j<N; j++) {
                map[i][j] = tempArr[j];
                if(tempArr[j] == '#'){
                    if(flag){
                        end = new Node(i, j, 0,0);
                    }
                    else{
                        start = new Node(i, j, 0, 0);
                        flag=true;
                    }
                }
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();

        // 해당 문에서 4 방향을 큐에 추가.
        // 큐에 추가하면서 방문 체크
        for(int i=0; i<4; i++){
            q.offer(new Node(start.y, start.x, 0 ,i));
            visited[start.y][start.x][i] = true;
        }

        int answer = 0;
        while(!q.isEmpty()) {
            Node now = q.poll();
            if(now.y == end.y && now.x == end.x){
                answer = now.cnt;
                break;
            }

            int tempY = now.y + ymove[now.direction];
            int tempX = now.x + xmove[now.direction];

            if(tempY < 0 || tempY >=N || tempX < 0 || tempX >= N) continue;
            if(map[tempY][tempX] == '*' )   continue;

            if(!visited[tempY][tempX][now.direction]) {
                q.offer(new Node(tempY, tempX, now.cnt, now.direction));
                visited[tempY][tempX][now.direction] = true;
            }

            if(map[tempY][tempX] == '!'){
                int d1 = now.direction+1;
                int d2 = now.direction-1;

                if(d1 == 4)     d1 = 0;
                if(d2 == -1)    d2 = 3;

                if(!visited[tempY][tempX][d1]){
                    q.offer(new Node(tempY, tempX, now.cnt+1, d1));
                    visited[tempY][tempX][d1]=true;
                }
                if(!visited[tempY][tempX][d2]){
                    q.offer(new Node(tempY, tempX, now.cnt+1, d2));
                    visited[tempY][tempX][d2]=true;
                }
            }
        }

        System.out.println(answer);
    }
}
