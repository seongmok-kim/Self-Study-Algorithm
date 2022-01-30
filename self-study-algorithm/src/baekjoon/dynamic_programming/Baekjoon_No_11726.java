package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_11726 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] d = new int[N+1];
		
		// 2x1 공간은 2x1타일만 들어가므로 1가지 경우임
		d[1] = 1;
		
		// N이 1일 수도 있으니 조건문으로 예외처리
		if(N>1)
			// 2x2 공간은 2x1타일 2개, 1x2타일 2개로 2가지 경우임
			d[2] = 2;
		
		// 점화식 및 메모이제이션을 이용해 풀이
		for(int i=3; i<=N; i++) {
			// Integer의 최대값을 벗어나 배열에 들어갈 수 있기에, 배열 저장 전에 10007을 나누어 대입
			d[i] = (d[i-1] + d[i-2])% 10007;
		}
		
		// 출력
		System.out.println(d[N]%10007);
		
	}

}
