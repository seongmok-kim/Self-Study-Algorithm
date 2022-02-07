package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_10844 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 최대 100까지의 자연수
		// 101이 아닌, N+1로 선언해도 무방
		long[][] d = new long[101][10];
		
		// 일의 자리는 계단 수가 각각 1개
		// 0으로 시작하는 수는 계단 수가 아니라고 명시됨
		for(int j=1;j<=9;j++) {
			d[1][j] = 1;
		}
		
		// i가 2, 즉 10의 자리부터 확인 (Bottom-Up방식)
		// +-1을 해주면 계단 수가 됨. 
		// ex) 10의 자리의 수가 2일 때. 2x 일 때, 계단 수는 21, 23
		// 중요! 0은 1, 9는 8로 고정 (-1, 10이 되기 때문에) 
		for(int i=2; i<=N;i++) {
			d[i][0] = (d[i-1][1])%1000000000;
			for(int j=1;j<=8;j++) {
				d[i][j] = (d[i-1][j-1] + d[i-1][j+1])%1000000000;
			}
			d[i][9]=(d[i-1][8])%1000000000;
		}
		
		// 해당 자릿수의 모든 경우의 수 합계 도출
		long sum = 0;
		for(int i=0;i<10;i++) {
			sum += d[N][i];
		}
		
		System.out.println(sum%1000000000);
		
	}	

}
