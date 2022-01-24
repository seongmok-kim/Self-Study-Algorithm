package backejoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_9095 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 배열
		int[] arr = new int[T];
		int max = 0;
		for(int i=0;i<T;i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(max<arr[i]) {
				max = arr[i];
			}
		}
		
		// 문제의 조건 : n은 양수고 11보다 작음
		int[] d = new int[11];

		d[1] = 1;
		d[2] = 2;
		d[3] = 4;
		
		// 점화식 : d[i] = d[i-1] + d[i-2] + d[i-3];
		for(int i=4; i<=max;i++) {
			d[i] = d[i-1] + d[i-2] + d[i-3];
		}
		
		// 각 테스트 케이스의 결과 출력
		for(int i=0; i<T;i++) {
			System.out.println(d[arr[i]]);
		}
	}

}
