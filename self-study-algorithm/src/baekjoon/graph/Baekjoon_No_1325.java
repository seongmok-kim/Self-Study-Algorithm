package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/1325
// 참고 : https://velog.io/@yanghl98/백준-1325-효율적인-해킹-JAVA
public class Baekjoon_No_1325 {

    public static int N, M;		// N: 컴퓨터의 수, M : 관계의 수
    public static Computer[] coms;			// 컴퓨터 배열
    public static boolean[] visited;		// 방문 체크 변수
    public static int[] answer;				// 각 컴퓨터에서 접근 가능한 컴퓨터 수


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coms = new Computer[N+1];
        answer= new int[N+1];

        for(int i=0; i<N+1; i++) {
            coms[i] = new Computer(i);
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            coms[p2].list.add(coms[p1]);		// 신뢰하는 컴퓨터를 추가하고
        }

        // DFS를 이용해서 각 컴퓨터에서 몇 대까지 접근가능한 지 확인
        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1];
            visited[i] = true;

            DFS(i, i);
        }

        // 최댓값 확인
        int max = 0;
        for(int i=1; i<N+1; i++) {
            max = Math.max(max, answer[i]);
        }

        StringBuilder sb = new StringBuilder();

        // 해당 최댓값을 가지는 컴퓨터 출력
        for(int i=1; i<N+1;i++) {
            if(answer[i] == max) {
                sb.append(i+" ");
            }
        }

        System.out.println(sb.toString());
    }

    // dfs를 이용
    public static void DFS(int original, int now) {
        for(Computer c : coms[now].list) {
            if(!visited[c.idx]) {
                visited[c.idx] = true;
                DFS(original, c.idx);
                answer[original]++;
            }
        }
    }

    public static class Computer{
        int idx;
        ArrayList<Computer> list;			// 접근 가능한 컴퓨터 리스트

        public Computer(int idx) {
            this.idx = idx;
            list = new ArrayList<>();
        }
    }
}