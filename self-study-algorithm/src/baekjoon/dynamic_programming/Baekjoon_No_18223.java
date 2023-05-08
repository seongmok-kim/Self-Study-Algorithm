package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon_No_18223 {
    static class Node {
        int idx;
        int dis;

        public Node(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }
    }

    static final int INF = 9_999_999;
    static int V = 0, E = 0, P = 0;
    static ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());       // 정점의 갯수
        E = Integer.parseInt(st.nextToken());       // 간선의 갯수
        P = Integer.parseInt(st.nextToken());       // 건우의 위치

        // map 세팅
        for(int i=0; i<=V; i++){
            map.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());

            int posA = Integer.parseInt(st.nextToken());
            int posB = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            // 양방향
            map.get(posA).add(new Node(posB, dis));
            map.get(posB).add(new Node(posA, dis));
        }

        // 1노드에서 V노드까지의 최단 거리 == 1노드에서 P노드를 들렸다가 P노드에서 V노드로 가는 거리가 같을 경우 친구를 구하기
        if(dijkstra(1, V) == (dijkstra(1, P) + dijkstra(P, V)))  System.out.println("SAVE HIM");
        else System.out.println("GOOD BYE");
    }

    static int dijkstra(int start, int target) {
        // 출발점 에서 각 정점까지의 최단 거리 배열(d) 설정
        int[] d = new int[V+1];
        Arrays.fill(d, INF);

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        d[start] = 0;

        while(!q.isEmpty()) {
            Node now = q.poll();

            if(now.dis > d[now.idx])    continue;

            for(int i=0; i<map.get(now.idx).size(); i++) {
                Node next = map.get(now.idx).get(i);

                int tempSum = now.dis + next.dis;
                if(d[next.idx] >= tempSum) {

                    d[next.idx] = tempSum;
                    q.offer(new Node(next.idx, tempSum));
                }
            }
        }

        return d[target];
    }
}
