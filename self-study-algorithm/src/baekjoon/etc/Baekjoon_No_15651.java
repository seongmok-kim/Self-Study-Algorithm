package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_15651 {

	public static int N, M;
	
	// 출력을 한번에 하기 위해 사용
	public static StringBuilder sb = new StringBuilder();
	
	// 조합 계산
	public static void calc(int num, int count, int[] arr) {
		
		// 선택한 수가 M일 때 출력
		if(count >= M) {
			for(int i=0; i<M; i++) {
				sb.append((arr[i]) + " ");
			}
			sb.append("\n");
			
			// 함수 종료
			return;
		}
		
		// 수 선택하기
		for(int i=0; i<N; i++) {
				arr[count] = i+1;
				calc(i, count+1, arr);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[M];
		calc(0, 0, arr);
		
		System.out.println(sb.toString());
	}

}