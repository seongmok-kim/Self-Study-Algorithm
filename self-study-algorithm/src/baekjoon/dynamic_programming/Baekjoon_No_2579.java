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
		
		d[1] = arr[1];
		if(N>1) {
			d[2] = arr[1] + arr[2];
		}
		for(int i=3;i<=N;i++) {
			d[i] = Math.max(d[i-3]+arr[i-1], d[i-2]) + arr[i];
		}
		
		System.out.println(d[N]);
	}

}
