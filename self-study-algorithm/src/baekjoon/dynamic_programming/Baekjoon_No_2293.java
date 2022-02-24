package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_2293 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N : 동전 종류의 갯수
		int N = Integer.parseInt(st.nextToken());
		
		// K : 원하는 금액
		int K = Integer.parseInt(st.nextToken());
		
		// dp 배열
		int[] d = new int[K+1];
		
		// 동전 배열
		int[] arr = new int[N];
		
		// 동전 세팅
		for(int i=0;i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// d[0]만 예외적으로 1로 선언 후 시작
		d[0]=1;
		
		// 점화식을 이용하여 풀이(Bottom-Up)
		// 가지고 있는 금액 종류를 기준으로 만들 수 있는 금액을 확인한다
		for(int i=0; i<N; i++) {
			/**
				d[4]의 경우를 예로 들어보자. (동전의 종류 : 1,2,5)
				1) 1원으로 4원을 만드는 경우의 수는 1가지 (1원 x 4) : d[4] = 1;
				2) 2원으로 4원을 만드는 경우의 수는 2가지 ((1원 x 2 + 2원 x 1) or (2원 x 2)) : d[4] = d[2] + d[4-2] = 4;
			**/
			for(int j=arr[i]; j<=K; j++) {
				d[j] = d[j] + d[j-arr[i]];
			}
		}
		
		// K금액을 만들 수 있는 경우의 수 출력
		System.out.println(d[K]);
	}

}
