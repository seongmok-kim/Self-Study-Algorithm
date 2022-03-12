package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_11399 {

	// N : 사람의 수
	public static int N;
	
	// arr : 각 사람마다 걸리는 시간을 나열한 배열
	public static int[] arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*** 입력받기(시작) ***/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();
		/*** 입력받기(끝) ***/
		
		// 오름차순으로 정렬
		Arrays.sort(arr);
		
		// 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값 구하기
		// ex 1) 첫번째 사람은 자신이 필요한 시간만 기다리면 됨.
		// ex 2) 두번째 사람은 앞사람과 자신이 필요한 시간만큼 기다려야 함.
		int result = 0;
		for(int i=0; i<N; i++) {
			int sum = 0;
			
			for(int j=0; j<=i; j++) {
				sum+= arr[j];
			}
			result += sum;
		}
		
		// 결과 출력
		System.out.println(result);
	}

}
