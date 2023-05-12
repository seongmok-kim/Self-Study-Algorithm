package baekjoon.dikstra_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon_No_14284 {

    static class Pos implements Comparable<Pos>{
        int idx;
        int length;

        public Pos(int idx, int length) {
            this.idx = idx;
            this.length = length;
        }

        @Override
        public int compareTo(Pos o) {
            return this.length - o.length;
        }
    }

    static char[][] map;
    static List<List<Pos>> graph = new ArrayList<List<Pos>>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++)  graph.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int posA = Integer.parseInt(st.nextToken());
            int posB = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            // 양방향 통행 가능하도록
            graph.get(posA).add(new Pos(posB, length));
            graph.get(posB).add(new Pos(posA, length));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] d = new int[N+1];
        Arrays.fill(d, 99_000_000);     // 무한으로 초기화
        d[start] = 0;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(start, 0));

        // 이하 일반적인 다익스트라 알고리즘과 동일
        while(!q.isEmpty()) {
            Pos now = q.poll();

            int idx = now.idx;
            int length = now.length;

            if(d[idx] < length) continue;

            for(int i=0; i<graph.get(idx).size(); i++) {
                Pos next = graph.get(idx).get(i);
                int tempLength = next.length + length;

                if(d[next.idx] > tempLength) {
                    d[next.idx] = tempLength;
                    q.offer(new Pos(next.idx, tempLength));
                }
            }
        }

        System.out.println(d[end]);
    }
}
