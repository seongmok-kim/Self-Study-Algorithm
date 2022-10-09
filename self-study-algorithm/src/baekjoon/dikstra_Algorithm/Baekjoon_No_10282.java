package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_10282 {

    static int N;
    static final int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int pos;
        int sec;

        public Node(int pos, int sec) {
            this.pos = pos;
            this.sec = sec;
        }

        @Override
        public int compareTo(Node other) {
            if(this.sec < other.sec) {
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

        N = Integer.parseInt(st.nextToken());		// 테스트 케이스의 갯수
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스만큼 반복
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());	// 컴퓨터 갯수
            int m = Integer.parseInt(st.nextToken());	// 의존성(간선) 갯수
            int x = Integer.parseInt(st.nextToken());	// 해킹당한 컴퓨터(시작 위치)

            ArrayList<ArrayList<Node>> map = new ArrayList<>();
            for(int j=0; j<n+1; j++) {
                map.add(new ArrayList<>());
            }

            for(int j=0; j<m; j++) {
                st = new StringTokenizer(br.readLine());

                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int sec = Integer.parseInt(st.nextToken());

                map.get(from).add(new Node(to, sec));
            }

            // 디익스트라 알고리즘 이용
            int[] d = new int[n+1];
            Arrays.fill(d, INF);
            d[x] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(x, 0));

            while(!pq.isEmpty()) {
                Node node = pq.poll();

                if(d[node.pos] < node.sec) {
                    continue;
                }

                for(int j=0; j<map.get(node.pos).size(); j++) {
                    Node next = map.get(node.pos).get(j);
                    int cost = d[node.pos] + next.sec;

                    if(cost < d[next.pos]) {
                        d[next.pos] = cost;
                        pq.offer(new Node(next.pos, cost));
                    }
                }
            }

            int totalTime = 0;
            int totalCnt = 0;

            for(int j=0; j<n+1; j++) {

                if(d[j] != INF) {
                    totalCnt++;
                    if(totalTime < d[j]) {
                        totalTime = d[j];
                    }
                }
            }

            sb.append(totalCnt+" "+totalTime+"\n");
        }			// 테스트 케이스만큼 반복(끝)


        System.out.println(sb.toString());
    }

}