package baekjoon.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());       // 집의 갯수
        int C = Integer.parseInt(st.nextToken());       // 공유기의 갯수

        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int min = 1;                    // 공유기 간에 최소 길이
        int max = arr[N-1] - arr[0];    // 공유기 간에 최대 길이
        int answer = 0;

        // 공유기 간에 최소 거리를 이분 탐색으로 확인.
        while(min <= max) {
            int mid = (min + max) / 2;
            int pre = arr[0];
            int cnt = 1;

            for(int i=1; i<N; i++) {
                int distance = arr[i] - pre;
                if(distance >= mid) {
                    cnt++;
                    pre = arr[i];
                }
            }

            if(cnt >= C) {
                answer = mid;
                min = mid+1;
            }
            else {
                max = mid-1;
            }
        }

        System.out.println(answer);
    }
}
