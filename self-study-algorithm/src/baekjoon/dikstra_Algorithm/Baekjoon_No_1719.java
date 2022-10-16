package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_1719 {

    static class Node{
        int pos;
        int dis;

        public Node(int pos, int dis) {
            this.pos = pos;
            this.dis = dis;
        }
    }

    static ArrayList<ArrayList<Node>> map = new ArrayList<>();

    static int N, M;
    static int[][] d;
    static int[][] firstArr;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[N+1][N+1];
        firstArr = new int[N+1][N+1];

        for(int i=0; i<=N; i++) {
            map.add(new ArrayList<Node>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int posA = Integer.parseInt(st.nextToken());
            int posB = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            // 양방향 이동 가능
            map.get(posA).add(new Node(posB, dis));
            map.get(posB).add(new Node(posA, dis));
        }

        // 한 군데씩 다익스트라 확인
        for(int i=1; i<=N; i++) {
            dijkstra(i);
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) {
                    sb.append("- ");
                }
                else {
                    sb.append(firstArr[i][j]+ " ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dijkstra(int START) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(START, 0));

        Arrays.fill(d[START], INF);
        d[START][START] = 0;


        while(!q.isEmpty()) {
            Node node = q.poll();

            if(d[START][node.pos] < node.dis) {
                continue;
            }

            for(int i=0; i<map.get(node.pos).size(); i++) {
                Node next = map.get(node.pos).get(i);

                if(d[START][next.pos] > (d[START][node.pos] + next.dis)) {
                    d[START][next.pos] = d[START][node.pos] + next.dis;
                    q.offer(new Node(next.pos, d[START][node.pos] + next.dis));

                    // A에서 B까지 가는 최단 루트 = B에서 A까지의 최단 루트
                    // 즉, 첫 번째로 거치는 구역을 구하려면 아래와 같이 하면 된다.
                    // 예를 들어, 1번에서 4번까지 1-2-3-4 루트로 갔다면. 4번에서 1번까지의 최단루트도 4-3-2-1이다.
                    // 그러니까, 1번에서 4번까지 가는 최단 루트를 구했을 때, 반대로 4번에서 1번으로 가는 최단 루트 중 첫 번째로 들리는 구역이 아래라는 뜻.
                    firstArr[next.pos][START] = node.pos;
                }
            }

        }
    }

}