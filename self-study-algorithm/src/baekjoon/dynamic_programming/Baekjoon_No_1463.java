package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_1463 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 각 숫자 별 횟수
		int[] d = new int[N+1];

		// 1이 만드려는 수이기에 횟수 0
		d[1] = 0;
		
		// N이 1일수도 있으니 오류 방지용으로 조건문
		if(N>1) {
			// 2에서 1로 만드는 연산은 1번 필요
			d[2] = 1;
		}
		// N이 1 or 2 일 수도 있으니 오류 방지용으로 조건문
		if(N>2) {
			// 3에서 1로 만드는 연산은 1번 필요
			d[3] = 1;
		}
		
		// 4부터 N까지 반복
		for(int i=4; i<=N; i++) {
			//1을 더해주는 연산
			int min = d[i-1] + 1;
			
			// 2로 나누어 진다면 2로 나눈 연산
			if(i % 2 == 0) {
				min = Math.min(min, d[i/2] + 1);
			}
			
			// 3으로 나누어 진다면 3으로 나눈 연산
			if(i % 3 == 0) {
				min = Math.min(min, d[i/3] + 1);
			}
			
			// 위 세 연산 중 가장 연산 횟수가 적은걸 채택
			d[i] = min;
		}
		
		// 결과 출력
		System.out.println(d[N]);
		
	}

}
