package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_2294 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N : 동전의 종류 
		// K : 원하는 가격
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 동전 종류를 모아두는 배열
		int[] money = new int[N];
		
		// 각 가격별로 최소 횟수 구하기
		int[] d = new int[K+1];
		
		for(int i=0; i<N; i++) {
			int temp = Integer.parseInt(br.readLine());
			money[i] = temp;
		}
		br.close();
		
		// 배열은 999999로 초기화
		Arrays.fill(d, 999999);
		
		d[0] = 0;

		// 5원일 경우
		// 1원 5개.
		// 5원 1개. (5원 - 현재가격(5원)) = 0에 + 1
		for(int i=0; i<N; i++) {
			int curMoney = money[i];
			
			for(int j=curMoney; j<=K; j++) {
				d[j] = Math.min(d[j], d[j-curMoney] + 1);
			}
		}
		
		// 못만드는 가격이면 -1출력
		if(d[K] == 999999) {
			System.out.println(-1);
		}
		// 아니면 정상 출력
		else {
			System.out.println(d[K]);
		}
		
	}
}
