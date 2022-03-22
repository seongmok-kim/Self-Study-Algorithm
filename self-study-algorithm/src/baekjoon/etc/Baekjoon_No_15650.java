package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_15650 {

	public static int N, M;
	
	public static boolean[] visited;
	
	// 조합 계산
	public static void calcCombi(int num, int count) {
		
		// 선택한 수가 M일 때 출력
		if(count >= M) {
			for(int i=0; i<N; i++) {
				// 고른(true로 설정한) 녀석만 출력
				if(visited[i]) {
					System.out.print((i+1) + " ");
				}
			}
			System.out.println();
			
			// 함수 종료
			return;
		}
		
		// 수 선택하기
		for(int i=num+1; i<N; i++) {
			// 선택하지 않은 수라면
			if(visited[i] == false) {
				
				// 선택
				visited[i] = true;
				calcCombi(i, count+1);
				
				// 선택 취소 (백트래킹)
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		
		calcCombi(-1, 0);
		
	}

}