package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_9465 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// T : 테스크 케이스의 개수
		int T = Integer.parseInt(br.readLine());
		
		// resultArr : 각 테스트케이스의 결과 저장
		int[] resultArr = new int[T];
		
		// 테스트 케이스의 수만큼 반복
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			// 배열의 0은 무시할 예정
			int[][] arr = new int[3][N+1];
			for(int j=1; j<3; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int k=1; k<N+1; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			// d : 스티커의 길이 별 최대 점수
			int[][] d = new int[3][N+1];
			
			// 첫번째 스티커의 열 초기화
			d[1][1] = arr[1][1];
			d[2][1] = arr[2][1];
			
			// 두번째 스티커부터 확인
			for(int g=2; g<=N; g++) {
				
				// 현재 열의 윗 스티커를 골랐을 때
				d[1][g] = Math.max(d[2][g-2], d[2][g-1]) + arr[1][g];
				
				// 현재 열의 아랫 스티커를 골랐을 때
				d[2][g] = Math.max(d[1][g-2], d[1][g-1]) + arr[2][g];
			}
			
			// 큰 값을 결과 리스트에 넣기
			resultArr[i] = Math.max(d[1][N], d[2][N]);
		}
		
		// 결과 출력
		for(int i=0; i<T; i++) {
			System.out.println(resultArr[i]);
		}
	}

}
