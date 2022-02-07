package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_1003 {
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 배열
		int[] arr = new int[T];
		
		int max = 0;
		for(int i=0; i<T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			max = Math.max(max, arr[i]);
		}
		
		// 숫자 별 0과 1 등장 횟수 저장하는 배열
		int[][] d = new int[max+1][2];
		
		d[0][0] = 1;
		
		d[1][1] = 1;
		if(max>1) {
			d[2][0] = 1;
			d[2][1] = 1;
		}
		
		// 계산
		for(int i=3; i<=max; i++) {
			d[i][0] = d[i-1][0] + d[i-2][0];
			d[i][1] = d[i-1][1] + d[i-2][1];
		}
		
		// 결과 출력
		for(int i=0; i<T; i++) {
			System.out.println(d[arr[i]][0] + " " + d[arr[i]][1]);
		}
		
	}

}
