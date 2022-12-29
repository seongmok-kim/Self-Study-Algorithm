package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon_No_2217 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);		// 오름차순으로 정렬

        int max = 0;

        for(int i=0; i<N; i++) {
            int temp = arr[i] * (N - i);		// 현재 무게 * 남은 로프의 수
            max = Math.max(max, temp);			// temp와 max 중 큰 값을 max에 저장
        }

        System.out.println(max);
    }

}
