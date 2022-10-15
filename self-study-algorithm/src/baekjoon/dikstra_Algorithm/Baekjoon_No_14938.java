package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_14938 {

    static class Node {
        int pos;
        int dis;

        public Node(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, M, R;			// N: 지역 갯수, M : 수색 범위, R : 길의 갯수
    static int[] item;			// 각 지역 별 아이템
    static int[] d;
    static ArrayList<ArrayList<Node>> road = new ArrayList<>();			// 길
    static int many = 0;		// 아이템을 가장 많이 얻을 수 있는 방법

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        item = new int[N+1];

        for(int i=0; i<N+1; i++) {
            // 1번부터 아이템 갯수 넣고
            if(i!=0) {
                item[i] = Integer.parseInt(st.nextToken());
            }
            // road 추가
            road.add(new ArrayList<Node>());
        }

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());

            int posA = Integer.parseInt(st.nextToken());
            int posB = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            // 양방향 길이므로
            road.get(posA).add(new Node(posB, dis));
            road.get(posB).add(new Node(posA, dis));
        }

        // 각 지역별로 한번 씩 반복
        for(int i=1; i<N+1; i++) {
            dijkstra(i);
        }

        System.out.println(many);
    }

    public static void dijkstra(int start) {
        Queue<Node> q = new LinkedList<Node>();

        d = new int[N+1];				// 시작지점에서 각 지점까지의 최소 거리
        Arrays.fill(d,  INF);			// 모든 지역을 INF로 채움 (갈 수 없다고 설정해둔것)
        q.offer(new Node(start, 0));	// 시작지점을 큐에 넣고
        d[start] = 0;					// 시작 지점의 최소 거리를 0

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(d[node.pos] < node.dis) {
                continue;
            }

            for(int i=0; i<road.get(node.pos).size(); i++) {
                Node next = road.get(node.pos).get(i);
                int cost = d[node.pos] + next.dis;

                if(cost < d[next.pos]) {
                    d[next.pos] = cost;
                    q.offer(new Node(next.pos, cost));
                }
            }
        }

        int sum = 0;
        for(int i=1; i<N+1; i++) {
            // 예은이의 수색범위 내에 있는 지역이라면 해당 지역의 아이템 갯수만큼 추가
            if(d[i] <= M) {
                sum += item[i];
            }
        }

        if(sum > many) {
            many = sum;
        }

    }

}