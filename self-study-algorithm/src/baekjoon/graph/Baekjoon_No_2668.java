package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon_No_2668 {

    static int[] arr;
    static int N;
    static List<Integer> answer = new ArrayList<>();
    static boolean[] visited;
    static int target = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<N+1; i++)
            arr[i] = Integer.parseInt(br.readLine());

        // 하나씩 확인
        for(int i=1; i<N+1; i++){
            visited[i] = true;
            target = i;
            DFS(i);

            // 백트래킹
            visited[i] = false;
        }

        // 정답 출력
        System.out.println(answer.size());
        answer.stream().sorted().forEach(System.out::println);
    }

    static void DFS(int idx) {
        // 아랫줄 값 인덱스에 접근한 적이 없다면 가서 탐색
        if (!visited[arr[idx]]) {
            visited[arr[idx]] = true;
            DFS(arr[idx]);
            visited[arr[idx]] = false;
        }

        if (arr[idx] == target)
            answer.add(target);

    }
}