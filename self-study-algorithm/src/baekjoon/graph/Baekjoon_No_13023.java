package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_No_13023 {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        for (int i=0; i<N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            graph.get(p1).add(p2);
            graph.get(p2).add(p1);
        }

        for (int i=0; i<N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, 0);

            if (result == 1) {
                break;
            }
        }

        System.out.println(result);
    }

    static void dfs(int now, int count) {
        List<Integer> nextList = graph.get(now);
        count++;
        if (count == 5) {
            result = 1;
            return;
        }

        for (int i=0; i<nextList.size(); i++) {
            int nextIdx = nextList.get(i);
            if (!visited[nextIdx]) {
                visited[nextIdx] = true;
                dfs(nextIdx, count);
                visited[nextIdx] = false; // 백트래킹
            }
        }
    }
}
