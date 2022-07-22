package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Link: https://www.acmicpc.net/problem/2470
public class Baekjoon_No_2470 {

	// N : 2 이상 100,000
	public static int N;
	
	// 각 수치 : -1,000,000,000 이상 1,000,000,000 이하
	public static long[] arr;
	
	public static long temp = Long.MAX_VALUE;
	
	public static long[] answer = new long[2];
	
	// 두 용액만을 합쳐야 함.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		arr = new long[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 오름차순 정렬 후
		Arrays.sort(arr);
		
		// 양 사이드에서 확인 시작
		int left = 0;
		int right = N-1;
		
		while(left<right) {
			long sum = arr[left] + arr[right];
			
			long absSum = Math.abs(sum);
			
			// 절댓값이 temp보다 작다 = 0에 가깝다
			if(temp > absSum) {
				// 갱신 
				answer[0] = arr[left];
				answer[1] = arr[right];
				temp = absSum;
			}
			
			// 0보다 크면 우측 기준 감소시키기
			if(sum > 0) {
				right--;
			}
			// 아니라면 좌측 기준 증가시키기
			else {
				left++;
			}
		}
		
		System.out.println(answer[0] + " " + answer[1]);
	}

}