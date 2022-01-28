package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_1912 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] d = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 첫 번째 수는 첫 번째가 가장 큰 값이다.
		d[0] = arr[0];
		
		// 최댓값 변수 
		int max = arr[0];
		
		// (이전의 연속된 수 + 현재 수 or 현재 수) 중 높은 것을 현재 숫자의 최댓값으로 설정한다.
		for (int i = 1; i < N; i++) {
			d[i] = Math.max(d[i - 1] + arr[i], arr[i]);
			max = Math.max(max, d[i]);
		}
		
		// 최댓값 출력 
		System.out.println(max);
		
	}

}