package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_2579 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] d = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 첫 번째 계단 설정 
		d[1] = arr[1];
		// 계단이 1개만 주어질 수도 있으니 조건문으로 처리 
		if(N>1) {
			d[2] = arr[1] + arr[2];
		}
		
		// 점화식을 이용해 Bottom-Up방식 이용 
		for(int i=3;i<=N;i++) {
			d[i] = Math.max(d[i-3]+arr[i-1] + arr[i], d[i-2] + arr[i]);
		}
		
		System.out.println(d[N]);
	}

}
