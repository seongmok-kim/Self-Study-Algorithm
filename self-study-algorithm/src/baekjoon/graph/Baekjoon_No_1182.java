package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_1182 {

    public static int N, S;
    public static int[] arr;

    public static int cnt = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        DFS(0, 0);

        // 부분수열의 원소가 하나도 없을 때에도 0이기 때문에(공집합), 1개를 빼줘야 한다.
        // 부분수열의 원소가 하나도 없는데 왜 0일까? 위에 DFS(0,0)에서 0으로 시작했기 때문
        if(S == 0) {
            cnt--;
        }
        System.out.println(cnt);
    }

    // depth: 어디까지 탐색했는 지 알려주는 변수
    // num : 현재의 값을 가지는 변수
    public static void DFS(int depth, int num) {
        // 다 탐색했을 때
        if(depth == N) {
            if(num == S)		// num이 S일 때 카운트 증가
                cnt++;
            return;
        }

        DFS(depth+1, num + arr[depth]);			// 현재 인덱스를 값에 추가
        DFS(depth+1, num);						// 현재 인덱스 무시
    }

}