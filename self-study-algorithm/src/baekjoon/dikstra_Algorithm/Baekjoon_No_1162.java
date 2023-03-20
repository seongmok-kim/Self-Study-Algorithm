package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1162 {
    static class Path implements Comparable<Path>{
        int idx;
        long cost;
        int canCoverCount;

        public Path(int idx, long cost, int canCoverCount){
            this.idx = idx;
            this.cost = cost;
            this.canCoverCount = canCoverCount;
        }

        @Override
        public int compareTo(Path o) {
            return (int) (cost - o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시의 수
        int M = Integer.parseInt(st.nextToken());   // 도로의 수
        int K = Integer.parseInt(st.nextToken());   // 포장할 도로의 수

        ArrayList<ArrayList<Path>> map = new ArrayList<>();
        for(int i=0; i<N+1; i++)    map.add(new ArrayList<>());

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int posA = Integer.parseInt(st.nextToken());
            int posB = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(posA).add(new Path(posB, cost, 0));
            map.get(posB).add(new Path(posA, cost, 0));
        }

        long[][] d = new long[N+1][K+1];
        for(int i=0; i< d.length; i++){
            Arrays.fill(d[i], Long.MAX_VALUE);
        }
        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.offer(new Path(1, 0, K));
        d[1][0] = 0;

        while (!pq.isEmpty()){
            Path road = pq.poll();

            if(d[road.idx][K - road.canCoverCount] < road.cost) continue;

            for(Path cur : map.get(road.idx)){

                if(d[cur.idx][K - road.canCoverCount] > road.cost + cur.cost){
                    d[cur.idx][K - road.canCoverCount] = road.cost + cur.cost;
                    pq.offer(new Path(cur.idx, d[cur.idx][K - road.canCoverCount], road.canCoverCount));
                }
                if(road.canCoverCount > 0 && d[cur.idx][K-road.canCoverCount+1] > road.cost) {
                    d[cur.idx][K-road.canCoverCount+1] = road.cost;
                    pq.offer(new Path(cur.idx, d[cur.idx][K-road.canCoverCount+1], road.canCoverCount - 1));
                }
            }
        }

        long result = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            result = Math.min(d[N][i], result);
        }
        System.out.println(result);
    }

}

