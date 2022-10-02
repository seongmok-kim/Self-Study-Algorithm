package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_1753_Re {

    public static class Node implements Comparable<Node>{
        int pos;
        int dis;

        public Node(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            if(this.dis < o.dis) {
                return -1;
            }
            else {
                return 1;
            }
        }

    }

    public static int V, E;
    public static int START;
    public static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    public static int INF = Integer.MAX_VALUE;
    public static int[] d;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        START = Integer.parseInt(br.readLine());

        for(int i=0; i<V+1; i++) {
            map.add(new ArrayList<Node>());
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            map.get(from).add(new Node(to, dis));
        }

        d = new int[V+1];
        // 무한으로 초기화
        Arrays.fill(d, INF);

        dijkstra();

        for(int i=1; i<d.length; i++) {
            if(INF == d[i]) {
                System.out.println("INF");
            }
            else {
                System.out.println(d[i]);
            }
        }
    }

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        pq.offer(new Node(START, 0));
        d[START] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.pos;
            int dis = node.dis;

            if(d[now] < dis){
                continue;
            }

            for(int i=0; i<map.get(now).size(); i++) {
                Node nextNode = map.get(now).get(i);
                int nextPos = nextNode.pos;
                int nextDis = nextNode.dis;

                if(d[nextPos] > dis + nextDis) {
                    d[nextPos] = dis + nextDis;
                    pq.offer(new Node(nextPos,  dis + nextDis));
                }
            }
        }
    }

}