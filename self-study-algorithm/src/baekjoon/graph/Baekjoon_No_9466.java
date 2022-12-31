package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_9466 {

    public static int N;
    public static int[] arr;
    public static boolean[] visited;
    public static int cnt = 0;
    public static boolean[] done;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++) {
            cnt = 0;
            N = Integer.parseInt(br.readLine());		// (2 ≤ N ≤ 100,000)
            arr = new int[N];
            visited = new boolean[N];
            done = new boolean[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[j] = Integer.parseInt(st.nextToken()) - 1;
            }

            for(int j=0; j<N; j++){
                if(!done[j])
                    dfs(j);
            }


            sb.append(N-cnt + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx) {
        if(visited[idx]){           // 방문한 적이 있다면 사이클이 생긴 것이므로 탐색 및 카운트 증가
            done[idx] = true;
            cnt++;
        }
        else{                       // 방문한 적이 없으면 방문 체크
            visited[idx] = true;
        }

        if(!done[arr[idx]]){        // 내가 바라보는 사람이 탐색된 적이 없다면 탐색 진행
            dfs(arr[idx]);
        }

        // 방문 체크 해제하고, 탐색 여부 true로 변경
        visited[idx] = false;
        done[idx] = true;
    }

}