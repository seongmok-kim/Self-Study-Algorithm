package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_2156 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] d = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		d[1] = arr[1];
		// 포도주의 잔 갯수가 1이 될 수도 있기에 에러를 방지 
		if(N>1) {
			d[2] = arr[1] + arr[2];
		}
		
		// 조건 : 연속 3잔만 마시면 되지 않음
		// 아래 3가지 경우 중, 큰 것 선택
		// (현재 잔 안마시기) or (2번째 전의 잔까지 마셨던 거 + 현재 잔 마시기) or (3번째 잔의 전까지 마셨던 거 + 이전 잔 마시기 + 현재 잔 마시기) 
		for(int i=3; i<=N; i++) {
			d[i] = Math.max(d[i-1],Math.max(d[i-2] + arr[i], d[i-3] + arr[i-1] + arr[i]));
		}
		
		System.out.println(d[N]);
	}
}