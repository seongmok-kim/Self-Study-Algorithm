package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_11724 {

    static int N, M;

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    static int result = 0;

    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        for(int i=0; i<=N; i++) {
            map.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int posA = Integer.parseInt(st.nextToken());
            int posB = Integer.parseInt(st.nextToken());

            // 양방향 이동 가능
            map.get(posA).add(posB);
            map.get(posB).add(posA);
        }

        // 하나씩 확인하되, 가본적 없는 곳만 확인
        for(int i=1; i<=N; i++) {
            if(visited[i] == false) {
                result++;
                DFS(i);			// DFS를 이용해서 풀이
            }
        }

        System.out.println(result);
    }

    static void DFS(int num) {
        for(int i=0; i<map.get(num).size();i++) {
            int next = map.get(num).get(i);

            if(visited[next] == false) {
                visited[next] = true;
                DFS(next);
            }
        }
    }

}