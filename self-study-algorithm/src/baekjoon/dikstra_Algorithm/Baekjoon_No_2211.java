package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_2211 {
    static class Network implements Comparable<Network>{
        int idx;
        int time;

        ArrayList<Integer> path = new ArrayList<>();

        public Network(int idx, int time) {
            this.idx = idx;
            this.time = time;
            path.add(idx);
        }

        @Override
        public int compareTo(Network o) {
            return o.time - this.time;
        }
    }

    public static void main(String[] args) throws IOException {
        final int INF = 20_000_000;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<ArrayList<Network>> map = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken());       // 컴퓨터 갯수
        int M = Integer.parseInt(st.nextToken());       // 회선의 갯수

        for(int i=0; i<=N; i++){
            map.add(new ArrayList<>());
        }

        PriorityQueue<Network> pq = new PriorityQueue<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int posA = Integer.parseInt(st.nextToken());
            int posB = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            // 양방향
            map.get(posA).add(new Network(posB, time));
            map.get(posB).add(new Network(posA, time));
        }

        // 1. 각 컴퓨터마다 최단 경로 구하기
        int[] d = new int[N+1];
        ArrayList<Integer>[] minPathArr = new ArrayList[N+1];

        Arrays.fill(d, INF);

        int START = 1;      // 슈퍼컴퓨터는 1로 고정
        d[START] = 0;

        pq.offer(new Network(START, 0));
        while(!pq.isEmpty()){
            Network now = pq.poll();

            int idx = now.idx;      // 현재 노드
            int time = now.time;    // 현재 노드까지의 시간 합계

            if(d[idx] < time){
                continue;
            }

            for(Network next : map.get(idx)){
                int totalTime = time + next.time;

                if(totalTime < d[next.idx]){
                    d[next.idx] = totalTime;
                    pq.offer(new Network(next.idx, totalTime));

                    ArrayList<Integer> minPath = new ArrayList<>();
                    for(int num : now.path)
                        minPath.add(num);
                    minPath.add(next.idx);

                    minPathArr[next.idx] = minPath;
                }
            }
        }

        // 2. 각 컴퓨터에 대한 최단 경로를 바탕으로 사용된 경로 표시
        boolean[][] resultArr = new boolean[N+1][N+1];
        int resultCnt = 0;
        for(int i=2; i<=N; i++){
            for(int j=0; j < minPathArr[i].size()- 1; j++){
                int from = minPathArr[i].get(j);
                int to = minPathArr[i].get(j+1);

                if(to < from){
                    int temp = to;
                    to = from;
                    from = temp;
                }

                if(resultArr[from][to] == false){
                    resultCnt++;
                    resultArr[from][to] = true;
                }
            }
        }

        // 3. 결과 출력
        System.out.println(resultCnt);
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(resultArr[i][j]){
                    System.out.println(i + " " + j);
                }
            }
        }
    }
}