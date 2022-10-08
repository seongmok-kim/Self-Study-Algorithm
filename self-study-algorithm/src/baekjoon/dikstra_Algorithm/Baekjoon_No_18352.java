package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_18352 {

    static int N, M, K, X;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int[] d;
    static final int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node>{
        int pos;
        int dis;

        public Node(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node other) {
            if(this.dis < other.dis) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());		// 도시 갯수
        M = Integer.parseInt(st.nextToken());		// 도로 갯수
        K = Integer.parseInt(st.nextToken());		// 거리 정보
        X = Integer.parseInt(st.nextToken());		// 출발 도시 번호

        for(int i=0; i<N+1; i++) {
            map.add(new ArrayList<>());
        }

        // 단방향 그래프 설정
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map.get(from).add(to);
        }

        // 다익스트라 알고리즘 사용
        d = new int[N+1];
        Arrays.fill(d, INF);
        d[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(X, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(d[node.pos] < node.dis) {
                continue;
            }

            for(int i=0; i<map.get(node.pos).size(); i++) {
                int next = map.get(node.pos).get(i);

                int cost = d[node.pos] + 1;

                if(d[next] > cost) {
                    d[next] = cost;
                    pq.offer(new Node(next, cost));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<N+1; i++) {
            if(d[i] == K) {
                sb.append(i+"\n");
            }
        }

        // 갈 수 있는 곳이 없으면 -1 출력
        if("".equals(sb.toString())) {
            System.out.println(-1);
        }
        else {
            System.out.println(sb.toString());
        }
    }

}